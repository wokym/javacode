package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestMain {
	public static void main(String[] args) {
//		cachedThreadPool();
//		fixedThreadPool();
//		scheduledThreadPool();
//		scheduledThreadPoolYan() ;
//		singleThreadExecutor() ;
		ThreadSyc th1=new ThreadSyc("zhangs");
		ThreadSyc th2=new ThreadSyc("zhangs");
		ThreadSyc th4=new ThreadSyc(new String("zhangs"));
		ThreadSyc th5=new ThreadSyc(new String("zhangs"));
		ThreadSyc th3=new ThreadSyc("zhanglis");
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
	}
	
	public static void getstr( String str){
		synchronized (str.intern()) {
			System.out.println("我来了==="+str);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	//线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
	public static void cachedThreadPool() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cachedThreadPool.execute(
					new MyThread()
			);
		}
	}
//	创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//	因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
//	定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
	public static void fixedThreadPool() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("class="+this+"=index:"+index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
//创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
//表示延迟3秒执行。
	public static void scheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
	}
//表示延迟1秒后每3秒执行一次。
	public static void scheduledThreadPoolYan() {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

	//创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
	//结果依次输出，相当于顺序执行各个任务
	public static void singleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors
				.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+";class="+this+"=index:"+index);
						int sleep=index%2==1?2000:500;
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
class MyThread implements Runnable{
	public MyThread(){
		System.out.println("创建"+this);
	}
	@Override
	public void run() {
		System.out.println("class="+this);
	}
}
class ThreadSyc extends Thread{
	String name;
	ThreadSyc(String name){
		this.name=name;
	}
	@Override
	public void run() {
		TestMain.getstr(name);
	}
}
