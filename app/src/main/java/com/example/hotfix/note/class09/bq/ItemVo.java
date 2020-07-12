package com.example.hotfix.note.class09.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *类说明：存放的队列的元素，
 */
public class ItemVo<T> implements Delayed {

	//到期时间,但传入的数值代表过期的时长，传入单位毫秒
	private long activeTime;
	private T data;//业务数据，泛型

	//传入过期时长,单位秒，内部转换
	public ItemVo(long expirationTime, T data) {
		this.activeTime = expirationTime*1000+System.currentTimeMillis();
		this.data = data;
	}

	public long getActiveTime() {
		return activeTime;
	}

	public T getData() {
		return data;
	}

	/*
	 * 这个方法返回到激活日期的剩余时间，时间单位由单位参数指定。
	 */
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(this.activeTime
				-System.currentTimeMillis(),unit);
		return d;
	}

	/*
	 *Delayed接口继承了Comparable接口，按剩余时间排序，实际计算考虑精度为纳秒数
	 */
	public int compareTo(Delayed o) {
		long d = (getDelay(TimeUnit.MILLISECONDS)
				- o.getDelay(TimeUnit.MILLISECONDS));
		if (d==0){
			return 0;
		}else{
			if (d<0){
				return -1;
			}else{
				return  1;
			}
		}
	}

}
