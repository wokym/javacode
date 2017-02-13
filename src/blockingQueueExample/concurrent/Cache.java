package blockingQueueExample.concurrent;


import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;



/**
 *Cache.java
 *
 * Created on 2014-1-11 上午11:30:36 by sunzhenchao mychaoyue2011@163.com
 */
public class Cache<K, V> {
	//线程安全Map(就是把Map分成了N个Segment，put和get的时候，都是现根据key.hashCode()算出放到哪个Segment中：)
    public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
    public DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();
    ReentrantLock ret=new ReentrantLock();
    public void put(K k,V v,long liveTime){
        V v2 = map.put(k, v);//之前的存值
        System.out.println("");
        DelayedItem<K> tmpItem = new DelayedItem<K>(k, liveTime);
        System.out.println("KKKEY="+k+"liveTime="+liveTime);
        if (v2 != null) {
            queue.remove(tmpItem);
        }
        queue.put(tmpItem);
    }
    
    public Cache(){
        Thread t = new Thread(){
            @Override
            public void run(){
                dameonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }
    
    public void dameonCheckOverdueKey(){
        while (true) {
        	//取到时间的那个
            DelayedItem<K> delayedItem=null;
			try {
//				delayedItem = queue.take();
				delayedItem = queue.poll();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            if (delayedItem != null) {
                map.remove(delayedItem.getT());
                System.out.println(System.nanoTime()+" remove "+delayedItem.getT() +" from cache");
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
    /**
     * TODO
     * @param args
     * 2014-1-11 上午11:30:36
     * @author:孙振超
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int cacheNumber = 10;
        int liveTime = 0;
        Cache<String, Integer> cache = new Cache<String, Integer>();
        
        for (int i = 0; i < cacheNumber; i++) {
            liveTime = random.nextInt(3000);
            System.out.println(i+"  "+liveTime);
            cache.put(i+"", i, random.nextInt(liveTime));
            if (random.nextInt(cacheNumber) > 7) {
                liveTime = random.nextInt(3000);
                System.out.println(i+"  "+liveTime);
                cache.put(i+"", i, random.nextInt(liveTime));
            }
        }

        Thread.sleep(3000);
        System.out.println();
    }

}

class DelayedItem<T> implements Delayed{

    private T t;
    private long liveTime ;
    private long removeTime;
    
    public DelayedItem(T t,long liveTime){
        this.setT(t);
        this.liveTime = liveTime;
        this.removeTime = TimeUnit.NANOSECONDS.convert(liveTime, TimeUnit.NANOSECONDS) + System.nanoTime();
    }
    
    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        if (o == this) return  0;
        if (o instanceof DelayedItem){
            DelayedItem<T> tmpDelayedItem = (DelayedItem<T>)o;
            if (liveTime > tmpDelayedItem.liveTime ) {
                return 1;
            }else if (liveTime == tmpDelayedItem.liveTime) {
                return 0;
            }else {
                return -1;
            }
        }
        long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return diff > 0 ? 1:diff == 0? 0:-1;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(removeTime - System.nanoTime(), unit);
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    @Override
    public int hashCode(){
        return t.hashCode();
    }
    
    @Override
    public boolean equals(Object object){
        if (object instanceof DelayedItem) {
            return object.hashCode() == hashCode() ?true:false;
        }
        return false;
    }
    
}