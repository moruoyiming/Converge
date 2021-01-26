package com.example.converge.note.framework.binder.demo.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.example.converge.note.framework.binder.demo.bean.Person;

import java.util.List;

public abstract class Stub extends Binder implements IPersonManager {

    private static final String DESCRIPTOR = "com.enjoy.binder.common.IPersonManager";

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IPersonManager asInterface(IBinder binder) {
        if ((binder == null)) {
            return null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if ((iin != null) && (iin instanceof IPersonManager)) {
            return (IPersonManager) iin;
        }
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSACTION_addPerson:
                Log.e("leo", "Stub,TRANSACTION_addPerson: " + Thread.currentThread());
                data.enforceInterface(DESCRIPTOR);
                Person arg0 = null;
                if ((0 != data.readInt())) {
                    arg0 = Person.CREATOR.createFromParcel(data);
                }
                this.addPerson(arg0);
                reply.writeNoException();
                return true;

            case TRANSACTION_getPersonList:
                data.enforceInterface(DESCRIPTOR);
                List<Person> result = this.getPersonList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    static final int TRANSACTION_addPerson = IBinder.FIRST_CALL_TRANSACTION;
    static final int TRANSACTION_getPersonList = IBinder.FIRST_CALL_TRANSACTION + 1;
}
