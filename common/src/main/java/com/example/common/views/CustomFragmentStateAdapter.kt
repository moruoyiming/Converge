package com.example.common.views

// This class is to handle intermittent error occurred in androidx.viewpager2.FragmentStateAdapter
// where mFragmentMaxLifecycleEnforcer might be null when call updateMaxLifeCycle method
// last version checked is androidx.viewpager2:viewpager2:1.0.0

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.collection.ArraySet
import androidx.collection.LongSparseArray
import androidx.core.util.Preconditions.checkArgument
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID
import androidx.viewpager2.adapter.StatefulAdapter
import androidx.viewpager2.widget.ViewPager2

abstract class CustomFragmentStateAdapter : RecyclerView.Adapter<FragmentViewHolder>, StatefulAdapter {
	@SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
	val mLifecycle: Lifecycle

	@SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
	val mFragmentManager: FragmentManager

	// Fragment bookkeeping
	@SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
	val mFragments = LongSparseArray<Fragment>()
	private val mSavedStates = LongSparseArray<Fragment.SavedState>()
	private val mItemIdToViewHolder = LongSparseArray<Int>()

	private var mFragmentMaxLifecycleEnforcer: FragmentMaxLifecycleEnforcer? = null

	// Fragment GC
	@SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
	var mIsInGracePeriod = false
	private var mHasStaleFragments = false


	/**
	 * @param fragmentActivity if the [ViewPager2] lives directly in a
	 * [FragmentActivity] subclass.
	 *
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 */
	constructor(fragmentActivity: FragmentActivity) : this(fragmentActivity.supportFragmentManager, fragmentActivity.lifecycle)

	/**
	 * @param fragment if the [ViewPager2] lives directly in a [Fragment] subclass.
	 *
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 */
    @Suppress("unused")
    constructor(fragment: Fragment) : this(fragment.childFragmentManager, fragment.lifecycle)

	/**
	 * @param fragmentManager of [ViewPager2]'s host
	 * @param lifecycle of [ViewPager2]'s host
	 *
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 * @see androidx.viewpager2.adapter.FragmentStateAdapter
	 */
	constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) {
		this.mFragmentManager = fragmentManager
		this.mLifecycle = lifecycle
		super.setHasStableIds(true)
	}

	@SuppressLint("RestrictedApi")
    @CallSuper
    @Override
	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        checkArgument(mFragmentMaxLifecycleEnforcer == null)
        mFragmentMaxLifecycleEnforcer = FragmentMaxLifecycleEnforcer()
        mFragmentMaxLifecycleEnforcer?.register(recyclerView)
    }

    @CallSuper
    @Override
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mFragmentMaxLifecycleEnforcer?.unregister(recyclerView)
        mFragmentMaxLifecycleEnforcer = null
    }

    /**
     * Provide a new Fragment associated with the specified position.
     * <p>
     * The adapter will be responsible for the Fragment lifecycle:
     * <ul>
     *     <li>The Fragment will be used to display an item.</li>
     *     <li>The Fragment will be destroyed when it gets too far from the viewport, and its state
     *     will be saved. When the item is close to the viewport again, a new Fragment will be
     *     requested, and a previously saved state will be used to initialize it.
     * </ul>
     * @see ViewPager2#setOffscreenPageLimit
     */
    abstract fun createFragment(position: Int): Fragment

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        return FragmentViewHolder.create(parent)
    }

	override fun onBindViewHolder(holder : FragmentViewHolder, position: Int) {
        val itemId = holder.itemId
	    val viewHolderId = holder.container.id
	    val boundItemId = itemForViewHolder(viewHolderId) // item currently bound to the VH
        if (boundItemId != null && boundItemId != itemId) {
            removeFragment(boundItemId)
            mItemIdToViewHolder.remove(boundItemId)
        }

        mItemIdToViewHolder.put(itemId, viewHolderId) // this might overwrite an existing entry
        ensureFragment(position)

        /** Special case when {@link RecyclerView} decides to keep the {@link container}
         * attached to the window, but not to the view hierarchy (i.e. parent is null) */
        val container: FrameLayout = holder.container
        if (ViewCompat.isAttachedToWindow(container)) {
            if (container.parent != null) {
                throw IllegalStateException("Design assumption violated.")
            }
            container.addOnLayoutChangeListener(object: View.OnLayoutChangeListener {
	            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ) {
		            if (container.parent != null) {
			            container.removeOnLayoutChangeListener(this)
			            placeFragmentInViewHolder(holder)
		            }
	            }
            })
        }

        gcFragments()
    }

    @SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
    fun gcFragments() {
        if (!mHasStaleFragments || shouldDelayFragmentTransactions()) {
            return
        }

        // Remove Fragments for items that are no longer part of the data-set
        val toRemove = ArraySet<Long>()
	    (0..mFragments.size()).forEach { ix ->
		    val itemId = mFragments.keyAt(ix)
		    if (!containsItem(itemId)) {
			    toRemove.add(itemId)
			    mItemIdToViewHolder.remove(itemId) // in case they're still bound
		    }
	    }

        // Remove Fragments that are not bound anywhere -- pending a grace period
        if (!mIsInGracePeriod) {
            mHasStaleFragments = false // we've executed all GC checks
            for (ix in 0..mFragments.size()) {
                val itemId = mFragments.keyAt(ix)
                if (!isFragmentViewBound(itemId)) {
                    toRemove.add(itemId)
                }
            }
        }

        for (itemId in toRemove) {
            removeFragment(itemId)
        }
    }

    private fun isFragmentViewBound(itemId: Long): Boolean {
        if (mItemIdToViewHolder.containsKey(itemId)) {
            return true
        }

        val fragment = mFragments.get(itemId) ?: return false

	    val view = fragment.view ?: return false

	    return view.parent != null
    }

    private fun itemForViewHolder(viewHolderId: Int): Long? {
        var boundItemId: Long? = null
        for (ix in 0..mItemIdToViewHolder.size()) {
            if (mItemIdToViewHolder.valueAt(ix) == viewHolderId) {
                if (boundItemId != null) {
                    throw IllegalStateException("Design assumption violated: "
                            + "a ViewHolder can only be bound to one item at a time.")
                }
                boundItemId = mItemIdToViewHolder.keyAt(ix)
            }
        }
        return boundItemId
    }

    private fun ensureFragment(position: Int) {
        val itemId = getItemId(position)
        if (!mFragments.containsKey(itemId)) {
            // TODO(133419201): check if a Fragment provided here is a new Fragment
            val newFragment = createFragment(position)
            newFragment.setInitialSavedState(mSavedStates.get(itemId))
            mFragments.put(itemId, newFragment)
        }
    }

    override fun onViewAttachedToWindow(holder: FragmentViewHolder) {
        placeFragmentInViewHolder(holder)
        gcFragments()
    }

    /**
     * @param holder that has been bound to a Fragment in the {@link #onBindViewHolder} stage.
     */
    @SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
    fun placeFragmentInViewHolder(holder : FragmentViewHolder ) {
        val fragment = mFragments.get(holder.itemId) ?: throw IllegalStateException("Design assumption violated.")
	    val container = holder.container
        val view = fragment.view

        /*
        possible states:
        - fragment: { added, notAdded }
        - view: { created, notCreated }
        - view: { attached, notAttached }

        combinations:
        - { f:added, v:created, v:attached } -> check if attached to the right container
        - { f:added, v:created, v:notAttached} -> attach view to container
        - { f:added, v:notCreated, v:attached } -> impossible
        - { f:added, v:notCreated, v:notAttached} -> schedule callback for when created
        - { f:notAdded, v:created, v:attached } -> illegal state
        - { f:notAdded, v:created, v:notAttached } -> illegal state
        - { f:notAdded, v:notCreated, v:attached } -> impossible
        - { f:notAdded, v:notCreated, v:notAttached } -> add, create, attach
         */

        // { f:notAdded, v:created, v:attached } -> illegal state
        // { f:notAdded, v:created, v:notAttached } -> illegal state
        if (!fragment.isAdded && view != null) {
            throw IllegalStateException("Design assumption violated.")
        }

        // { f:added, v:notCreated, v:notAttached} -> schedule callback for when created
        if (fragment.isAdded && view == null) {
            scheduleViewAttach(fragment, container)
            return
        }

        // { f:added, v:created, v:attached } -> check if attached to the right container
        if (fragment.isAdded && view?.parent != null) {
            if (view.parent != container) {
                addViewToContainer(view, container)
            }
            return
        }

        // { f:added, v:created, v:notAttached} -> attach view to container
        if (fragment.isAdded && view != null) {
            addViewToContainer(view, container)
            return
        }

        // { f:notAdded, v:notCreated, v:notAttached } -> add, create, attach
        if (!shouldDelayFragmentTransactions()) {
            scheduleViewAttach(fragment, container)
            mFragmentManager.beginTransaction()
                    .add(fragment, "f" + holder.itemId)
                    .setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                    .commitNow()
            mFragmentMaxLifecycleEnforcer?.updateFragmentMaxLifecycle(false)
        } else {
            if (mFragmentManager.isDestroyed) {
                return // nothing we can do
            }
            mLifecycle.addObserver(object: LifecycleEventObserver {
               override fun onStateChanged(source: LifecycleOwner ,event: Lifecycle.Event ) {
                    if (shouldDelayFragmentTransactions()) {
                        return
                    }
                    source.lifecycle.removeObserver(this)
                    if (ViewCompat.isAttachedToWindow(holder.container)) {
                        placeFragmentInViewHolder(holder)
                    }
                }
            })
        }
    }

    private fun scheduleViewAttach(fragment: Fragment , container: FrameLayout ) {
        // After a config change, Fragments that were in FragmentManager will be recreated. Since
        // ViewHolder container ids are dynamically generated, we opted to manually handle
        // attaching Fragment views to containers. For consistency, we use the same mechanism for
        // all Fragment views.
        mFragmentManager.registerFragmentLifecycleCallbacks(object: FragmentManager.FragmentLifecycleCallbacks() {
            // TODO(b/141956012): Suppressed during upgrade to AGP 3.6.
            @SuppressWarnings("ReferenceEquality")
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                if (f == fragment) {
                    fm.unregisterFragmentLifecycleCallbacks(this)
                    addViewToContainer(v, container)
                }
            }
        }, false)
    }

    @SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
    fun addViewToContainer(v: View , container: FrameLayout ) {
        if (container.childCount > 1) {
            throw IllegalStateException("Design assumption violated.")
        }

        if (v.parent == container) {
            return
        }

        if (container.childCount > 0) {
            container.removeAllViews()
        }

        if (v.parent != null) {
            (v.parent as ViewGroup).removeView(v)
        }

        container.addView(v)
    }

    override fun onViewRecycled(holder: FragmentViewHolder ) {
        val viewHolderId = holder.container.id
	    val boundItemId = itemForViewHolder(viewHolderId) // item currently bound to the VH
        if (boundItemId != null) {
            removeFragment(boundItemId)
            mItemIdToViewHolder.remove(boundItemId)
        }
    }

	override fun onFailedToRecycleView(holder: FragmentViewHolder ): Boolean {
        /*
         This happens when a ViewHolder is in a transient state (e.g. during an
         animation).

         Our ViewHolders are effectively just FrameLayout instances in which we put Fragment
         Views, so it's safe to force recycle them. This is because:
         - FrameLayout instances are not to be directly manipulated, so no animations are
         expected to be running directly on them.
         - Fragment Views are not reused between position (one Fragment = one page). Animation
         running in one of the Fragment Views won't affect another Fragment View.
         - If a user chooses to violate these assumptions, they are also in the position to
         correct the state in their code.
        */
        return true
    }

    private fun removeFragment(itemId: Long) {
        val fragment = mFragments.get(itemId) ?: return

	    if (fragment.view != null) {
            val viewParent = fragment.view?.parent
            if (viewParent != null) {
                (viewParent as FrameLayout).removeAllViews()
            }
        }

        if (!containsItem(itemId)) {
            mSavedStates.remove(itemId)
        }

        if (!fragment.isAdded) {
            mFragments.remove(itemId)
            return
        }

        if (shouldDelayFragmentTransactions()) {
            mHasStaleFragments = true
            return
        }

        if (fragment.isAdded && containsItem(itemId)) {
            mSavedStates.put(itemId, mFragmentManager.saveFragmentInstanceState(fragment))
        }
        mFragmentManager.beginTransaction().remove(fragment).commitNow()
        mFragments.remove(itemId)
    }

    @SuppressWarnings("WeakerAccess") // to avoid creation of a synthetic accessor
    fun shouldDelayFragmentTransactions(): Boolean {
        return mFragmentManager.isStateSaved
    }

    /**
     * Default implementation works for collections that don't add, move, remove items.
     * <p>
     * TODO(b/122670460): add lint rule
     * When overriding, also override {@link #containsItem(long)}.
     * <p>
     * If the item is not a part of the collection, return {@link RecyclerView#NO_ID}.
     *
     * @param position Adapter position
     * @return stable item id {@link RecyclerView.Adapter#hasStableIds()}
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * Default implementation works for collections that don't add, move, remove items.
     * <p>
     * TODO(b/122670460): add lint rule
     * When overriding, also override {@link #getItemId(int)}
     */
    private fun containsItem(itemId: Long): Boolean {
        return itemId in 0 until itemCount
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        throw UnsupportedOperationException(
                "Stable Ids are required for the adapter to function properly, and the adapter "
                        + "takes care of setting the flag.")
    }

    override fun saveState(): Parcelable {
        /** TODO(b/122670461): use custom {@link Parcelable} instead of Bundle to save space */
        val savedState = Bundle(mFragments.size() + mSavedStates.size())

        /** save references to active fragments */
        for (ix  in 0..mFragments.size()) {
            val itemId = mFragments.keyAt(ix)
            val fragment = mFragments.get(itemId)
            if (fragment != null && fragment.isAdded) {
                val key = createKey(KEY_PREFIX_FRAGMENT, itemId)
                mFragmentManager.putFragment(savedState, key, fragment)
            }
        }

        /** Write {@link mSavedStates) into a {@link Parcelable} */
        for (ix  in 0..mSavedStates.size()) {
            val itemId = mSavedStates.keyAt(ix)
            if (containsItem(itemId)) {
                val key = createKey(KEY_PREFIX_STATE, itemId)
                savedState.putParcelable(key, mSavedStates.get(itemId))
            }
        }

        return savedState
    }

   override fun restoreState(savedState: Parcelable) {
        if (!mSavedStates.isEmpty || !mFragments.isEmpty) {
            throw IllegalStateException("Expected the adapter to be 'fresh' while restoring state.")
        }

        val bundle = savedState as Bundle
        if (bundle.classLoader == null) {
            /** TODO(b/133752041): pass the class loader from {@link ViewPager2.SavedState } */
	        bundle.classLoader = this.javaClass.classLoader
        }

        for (key in bundle.keySet()) {
            if (isValidKey(key, KEY_PREFIX_FRAGMENT)) {
                val itemId = parseIdFromKey(key, KEY_PREFIX_FRAGMENT)
                val fragment = mFragmentManager.getFragment(bundle, key)
                mFragments.put(itemId, fragment)
                continue
            }

            if (isValidKey(key, KEY_PREFIX_STATE)) {
                val itemId = parseIdFromKey(key, KEY_PREFIX_STATE)
                val state = bundle.getParcelable<Fragment.SavedState>(key)
                if (containsItem(itemId)) {
                    mSavedStates.put(itemId, state)
                }
                continue
            }

            throw IllegalArgumentException("Unexpected key in savedState: $key")
        }

        if (!mFragments.isEmpty) {
            mHasStaleFragments = true
            mIsInGracePeriod = true
            gcFragments()
            scheduleGracePeriodEnd()
        }
    }

    private fun scheduleGracePeriodEnd() {
        val handler = Handler(Looper.getMainLooper())
	    val runnable = Runnable {
		    mIsInGracePeriod = false
		    gcFragments() // good opportunity to GC
        }

        mLifecycle.addObserver(object: LifecycleEventObserver {
           override fun onStateChanged(source: LifecycleOwner , event: Lifecycle.Event ) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    handler.removeCallbacks(runnable)
                    source.lifecycle.removeObserver(this)
                }
            }
        })

        handler.postDelayed(runnable, GRACE_WINDOW_TIME_MS.toLong())
    }

    /**
     * Pauses (STARTED) all Fragments that are attached and not a primary item.
     * Keeps primary item Fragment RESUMED.
     */
    inner class FragmentMaxLifecycleEnforcer {
        private lateinit var mPageChangeCallback: ViewPager2.OnPageChangeCallback
        private lateinit var mDataObserver: RecyclerView.AdapterDataObserver
        private lateinit var mLifecycleObserver: LifecycleEventObserver
        private var mViewPager: ViewPager2? = null

        private var mPrimaryItemId = NO_ID

        @Suppress("ObjectLiteralToLambda")
        fun register(recyclerView: RecyclerView ) {
            mViewPager = inferViewPager(recyclerView)

            // signal 1 of 3: current item has changed
            mPageChangeCallback = object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    updateFragmentMaxLifecycle(false)
                }

	            override fun onPageSelected(position: Int) {
                    updateFragmentMaxLifecycle(false)
                }
            }
            mViewPager?.registerOnPageChangeCallback(mPageChangeCallback)

            // signal 2 of 3: underlying data-set has been updated
            mDataObserver = object: DataSetChangeObserver() {
                override fun onChanged() {
                    updateFragmentMaxLifecycle(true)
                }
            }
            registerAdapterDataObserver(mDataObserver)

            // signal 3 of 3: we may have to catch-up after being in a lifecycle state that
            // prevented us to perform transactions
            mLifecycleObserver = object: LifecycleEventObserver {
	            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event ) {
                    updateFragmentMaxLifecycle(false)
                }
            }
            mLifecycle.addObserver(mLifecycleObserver)
        }

        fun unregister(recyclerView: RecyclerView ) {
            val viewPager = inferViewPager(recyclerView)
            viewPager.unregisterOnPageChangeCallback(mPageChangeCallback)
            unregisterAdapterDataObserver(mDataObserver)
            mLifecycle.removeObserver(mLifecycleObserver)
            mViewPager = null
        }

	    fun updateFragmentMaxLifecycle(dataSetChanged: Boolean) {
            if (shouldDelayFragmentTransactions()) {
                return /** recovery step via {@link #mLifecycleObserver} */
            }

            if (mViewPager?.scrollState != ViewPager2.SCROLL_STATE_IDLE) {
                return // do not update while not idle to avoid jitter
            }

            if (mFragments.isEmpty || itemCount == 0) {
                return // nothing to do
            }

            val currentItem = mViewPager?.currentItem ?: 0
            if (currentItem >= itemCount) {
                /** current item is yet to be updated; it is guaranteed to change, so we will be
                 * notified via {@link ViewPager2.OnPageChangeCallback#onPageSelected(int)}  */
                return
            }

            val currentItemId = getItemId(currentItem)
            if (currentItemId == mPrimaryItemId && !dataSetChanged) {
                return // nothing to do
            }

            val currentItemFragment = mFragments.get(currentItemId)
            if (currentItemFragment == null || !currentItemFragment.isAdded) {
                return
            }

            mPrimaryItemId = currentItemId
            val transaction = mFragmentManager.beginTransaction()

            var toResume: Fragment? = null
            for (ix in 0..mFragments.size()) {
                val itemId      = mFragments.keyAt(ix)
                val fragment    = mFragments.valueAt(ix)

                if (fragment != null) {
                    if (!fragment.isAdded) {
                        continue
                    }

                    if (itemId != mPrimaryItemId) {
                        transaction.setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                    } else {
                        toResume = fragment // itemId map key, so only one can match the predicate
                    }

                    fragment.setMenuVisibility(itemId == mPrimaryItemId)
                }
            }
            if (toResume != null) { // in case the Fragment wasn't added yet
                transaction.setMaxLifecycle(toResume, Lifecycle.State.RESUMED)
            }

            if (!transaction.isEmpty) {
                transaction.commitNow()
            }
        }

        private fun inferViewPager(recyclerView: RecyclerView ): ViewPager2 {
            val parent = recyclerView.parent
            if (parent is ViewPager2) {
                return parent
            }
            throw IllegalStateException("Expected ViewPager2 instance. Got: $parent")
        }
    }

    /**
     * Simplified {@link RecyclerView.AdapterDataObserver} for clients interested in any data-set
     * changes regardless of their nature.
     */
    private abstract class DataSetChangeObserver : RecyclerView.AdapterDataObserver() {
        abstract override fun onChanged()

	    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            onChanged()
        }

	    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            onChanged()
        }

	    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            onChanged()
        }

	    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            onChanged()
        }

	    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            onChanged()
        }
    }


	companion object {
		// State saving config
		private const val KEY_PREFIX_FRAGMENT   = "f#"
		private const val KEY_PREFIX_STATE      = "s#"

		// Fragment GC config
		private const val GRACE_WINDOW_TIME_MS  = 10_000 // 10 seconds

		// Helper function for dealing with save / restore state
		private fun createKey(prefix: String , id: Long ): String {
			return prefix + id
		}

		// Helper function for dealing with save / restore state
		private fun isValidKey(key: String , prefix: String ): Boolean {
			return key.startsWith(prefix) && key.length > prefix.length
		}

		// Helper function for dealing with save / restore state
		private fun parseIdFromKey(key: String , prefix: String ): Long {
			return key.substring(prefix.length).toLong()
		}
	}
}

class FragmentViewHolder private constructor(container: FrameLayout) : RecyclerView.ViewHolder(container) {
	val container: FrameLayout
		get() = itemView as FrameLayout

	companion object {
		fun create(parent: ViewGroup): FragmentViewHolder {
			val container = FrameLayout(parent.context)
			container.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT)
			container.id = ViewCompat.generateViewId()
			container.isSaveEnabled = false
			return FragmentViewHolder(container)
		}
	}
}