package threadPool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest2 {  
    public static void main(String[] args) {  
        final Business business = new Business();  
        new Thread(new Runnable() {  
            @Override  
            public void run() {  
                threadExecute(business, "sub");  
            }  
        }).start();  
        threadExecute(business, "main");  
    }     
    public static void threadExecute(Business business, String threadType) {  
        for(int i = 0; i < 100; i++) {  
            try {  
                if("main".equals(threadType)) {  
                    business.main(i);  
                } else {  
                    business.sub(i);  
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  
class Business {  
    private boolean bool = true;  
    private Lock lock = new ReentrantLock();  
    private Condition condition = lock.newCondition();   
    public /*synchronized*/ void main(int loop) throws InterruptedException {  
        lock.lock();  
        try {  
            while(bool) {                 
                condition.await();//this.wait();  
            }  
            for(int i = 0; i < 100; i++) {  
                System.out.println("main thread seq of " + i + ", loop of " + loop);  
            }  
            bool = true;  
            condition.signal();//this.notify();  
        } finally {  
            lock.unlock();  
        }  
    }     
    public /*synchronized*/ void sub(int loop) throws InterruptedException {  
        lock.lock();  
        try {  
            while(!bool) {  
                condition.await();//this.wait();  
            }  
            for(int i = 0; i < 10; i++) {  
                System.out.println("sub thread seq of " + i + ", loop of " + loop);  
            }  
            bool = false;  
            condition.signal();//this.notify();  
        } finally {  
            lock.unlock();  
        }  
    }  
}  

//这就是多个Condition的强大之处，假设缓存队列中已经存满，那么阻塞的肯定是写线程，唤醒的肯定是读线程，相反，阻塞的肯定是读线程，
//唤醒的肯定是写线程，那么假设只有一个Condition会有什么效果呢，缓存队列中已经存满，
//这个Lock不知道唤醒的是读线程还是写线程了，如果唤醒的是读线程，皆大欢喜，如果唤醒的是写线程，那么线程刚被唤醒，又被阻塞了，这时又去唤醒，这样就浪费了很多时间。

class BoundedBuffer {  
	   final Lock lock = new ReentrantLock();//锁对象  
	   final Condition notFull  = lock.newCondition();//写线程条件   
	   final Condition notEmpty = lock.newCondition();//读线程条件   
	  
	   final Object[] items = new Object[100];//缓存队列  
	   int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;  
	  
	   public void put(Object x) throws InterruptedException {  
	     lock.lock();  
	     try {  
	       while (count == items.length)//如果队列满了   
	         notFull.await();//阻塞写线程  
	       items[putptr] = x;//赋值   
	       if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0  
	       ++count;//个数++  
	       notEmpty.signal();//唤醒读线程  
	     } finally {  
	       lock.unlock();  
	     }  
	   }  
	  
	   public Object take() throws InterruptedException {  
	     lock.lock();  
	     try {  
	       while (count == 0)//如果队列为空  
	         notEmpty.await();//阻塞读线程  
	       Object x = items[takeptr];//取值   
	       if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0  
	       --count;//个数--  
	       notFull.signal();//唤醒写线程  
	       return x;  
	     } finally {  
	       lock.unlock();  
	     }  
	   }   
	 }  