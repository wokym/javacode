package threadDuo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import util.CompanyUtils;
//两个线程同时提交任务，获取自己的任务
public class FinalThreadPool {
	static final ExecutorService exec = Executors.newCachedThreadPool();  
	static final CompletionService<String> execcomp = new ThreadLocalExecutorCompletionService<String>(exec);  
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadLocalExecutorCompletionService.initThread();
				//添加线程任务
				  for(int i=0; i<10; i++){  
			            execcomp.submit(getTask("Thread1"));  
			        }  
			        for(int i=0; i<10; i++){  
			        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
			            try {
							Future<String> take = execcomp.take();
							String string = take.get();
							System.out.println("线程名称Thread1："+string);
						} catch (Exception e) {
							e.printStackTrace();
						}  
			        }  
//			        exec.shutdown();  
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadLocalExecutorCompletionService.initThread();
				//添加线程任务
				  for(int i=0; i<10; i++){  
			            execcomp.submit(getTask("Thread2"));  
			        }  
			        for(int i=0; i<10; i++){  
			        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
			            try {
							Future<String> take = execcomp.take();
							String string = take.get();
							System.out.println("线程名称Thread2："+string);
						} catch (Exception e) {
							e.printStackTrace();
						}  
			        }  
			}
		}).start();
	}
	 
    //得到一个任务  
    public static Callable<String> getTask(final String name){  
        final Random rand = new Random();  
        Callable<String> task = new Callable<String>(){  
            @Override  
            public String call() throws Exception {  
                int i = rand.nextInt(10);  
//                int j = rand.nextInt(10);  
//                int sum = i*j;  
                Thread.sleep(i*100);
                return i+"\t"+name+";UUID="+CompanyUtils.getUUID();  
            }  
        };  
        return task;  
    }  
}
