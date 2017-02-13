package threadDuo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ManyThread {
	public static void main(String[] args) {
		getThread();
	}
	public static void ManyThreadPool(){
		final int bast=4;
		final int [] strs=new int[bast];
		final int maxWaitTime=1000;
		
		for(int i=0;i<bast-1;i++){
			Thread run=new MThread(strs,i);
			run.start();
		}
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				
				//回调函数
				while(true){
					for(int i=0;i<bast-1;i++){
						strs[i+1]=strs[i]<strs[i+1]?strs[i+1]:strs[i];
					}
					System.out.println(strs[bast-1]+"sys");
					if(strs[bast-1]>1) break;
					long endTime = System.currentTimeMillis();
					if((endTime-startTime)>maxWaitTime) throw new RuntimeException();
				}
				
			}
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println(strs[bast-1]);
	}
	
	public static void getThread(){
		 // 定义一个缓冲的线程值 线程池的大小根据任务变化
	    ExecutorService threadPool = Executors.newCachedThreadPool();
	    for (int i = 0; i < 10; i++) {

	      threadPool.execute(new Runnable() {
	        public void run() {

	          try {
	            Thread.sleep(2000);
	            // 模拟子线程任务
	          } catch (InterruptedException e) {
	          }
	          System.out.println("子线程" + Thread.currentThread() + "执行完毕");

	        }
	      });

	    }
	    // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
	    threadPool.shutdown();

	    try {
	      // 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行
	      // 设置最长等待10秒
	      threadPool.awaitTermination(1, TimeUnit.SECONDS);
	    } catch (InterruptedException e) {
	      //
	      e.printStackTrace();
	    }

	    System.out.println("主线执行。");
	  }
	}

class MThread extends Thread {
	
	int i;
	
	int [] strs;
	
	MThread(int [] strs,int i){
		this.strs=strs;
		this.i=i;
	}

	@Override
	public void run() {
		if(i==2)
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		strs[i]=i;
	}
	
}
