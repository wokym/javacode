package threadDuo;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class OutTimeThread {  
  
    public static void main(String[] args) throws InterruptedException,  
            ExecutionException {  
    	doFutureTask();
//    	doExecutorService();
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
    
    public static void doExecutorService(){
    	 final ExecutorService exec = Executors.newFixedThreadPool(1);  
         
         Callable<String> call = new Callable<String>() {  
             public String call() throws Exception {  
            	 System.out.println("开始执行任务********doExecutorService()");
                 //开始执行耗时操作  
                 Thread.sleep(1000 * 5);  
                 return "线程执行完成.";  
             }  
         };  
           
         try {  
             Future<String> future = exec.submit(call);  
             String obj = future.get(1000 * 1, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒  
             System.out.println("任务成功返回:" + obj);  
         } catch (TimeoutException ex) {  
             System.out.println("处理超时啦....");  
             ex.printStackTrace();  
         } catch (Exception e) {  
             System.out.println("处理失败.");  
             e.printStackTrace();  
         }  
         // 关闭线程池  
         exec.shutdown();  
    }
}  
