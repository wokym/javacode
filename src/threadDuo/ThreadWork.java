package threadDuo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadWork {
    public static void main(String[] args) throws Exception {  
    	ThreadWork t = new ThreadWork();  
        t.timeOutFrom();  
        
    }
//使用CompletionService(完成服务)保持Executor处理的结果  
    public void timeOutFrom() throws InterruptedException, ExecutionException{  
        ExecutorService exec = Executors.newCachedThreadPool();  
        CompletionService<Integer> execcomp = new ExecutorCompletionService<Integer>(exec);  
        for(int i=0; i<10; i++){  
            execcomp.submit(getTask());  
        }  
        int sum = 0;  
        for(int i=0; i<10; i++){  
        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
            Future<Integer> future = execcomp.take();  
            Integer integer = future.get();
            //有执行结束的结果，检查结果
            if(i==1){
            	System.out.println("shutdown");
            	 exec.shutdownNow();  //立即结束线程工作
//            	 exec.shutdown();//不接收新任务，结束当前任务后退出
            	 break;
            }
            //线程处理结果
            
            //线程结束，检测检测结果
        }  
        System.out.println("总数为："+sum);  
        exec.shutdown();  
    } 
    
    public static void doFutureTask(){
    	Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				 //开始执行耗时操作  
				System.out.println("开始执行任务********");
                try {
					Thread.sleep(1000 * 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
                System.out.println("执行操作完成*********");
				return "天王";
			}
		};
		FutureTask<String> future=new FutureTask<String>(callable);
    	new Thread(future).start();
    	try {
			String obj = future.get(1000 *3, TimeUnit.MILLISECONDS);
			System.out.println("返回结果="+obj);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			  System.out.println("处理超时啦....");  
			e.printStackTrace();
		} 
    }
    
    //得到一个任务  
    public Callable<Integer> getTask(){  
        final Random rand = new Random();  
        Callable<Integer> task = new Callable<Integer>(){  
            @Override  
            public Integer call() throws Exception {  
                int i = rand.nextInt(10);  
                int j = rand.nextInt(10);  
                int sum = i*j;  
//                Thread.sleep(sum*100);
                for(int f=0;f<sum*10;f++){
                	System.out.println(Thread.currentThread().getName()+ ";call=="+f);
                }
                System.out.print(sum+"\t");  
                return sum;  
            }  
        };  
        return task;  
    }  
}
