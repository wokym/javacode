package threadDuoII;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import threadDuo.ThreadLocalExecutorCompletionService;
import util.CompanyUtils;

public class ThreadLocalExecutor {
	private static final ExecutorService exec = Executors.newCachedThreadPool();  //异步请求解析层线程池
	private static final CompletionService execcomp = new ThreadLocalExecutorCompletionService(exec);//线程返回处理结果 
	/**
	 * 线程池执行任务等结果，没完成一个任务检查一次时间， 超时退出，但线程逻辑不中断
	 */
	public static  void submit(int timeOutSecond,FutureData futureData,Callable ... task) {
		long startTime=System.currentTimeMillis();
		ThreadLocalExecutorCompletionService.initThread();
		if(task==null) new NullPointerException();
		int tackNum=task.length;
		for(Callable t:task){
			execcomp.submit(t);  
		}
	    for(int i=0; i<tackNum; i++){  
        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
            try {
				Object object = execcomp.take().get();
				boolean call = futureData.call(object);
				if(!call) {
					return;
				}
				long endTime = System.currentTimeMillis();
				if((endTime-startTime)>timeOutSecond*1000) return;
			} catch (Exception e) {
				e.printStackTrace();
			}  
        } 
	    
	}
	/**
	 * 线程执行等到结果，时间到了后，抛出超时异常，但线程逻辑不中断
	 * @throws Exception 
	 */
	public static  void submitByThread(int timeOutSecond,final FutureData futureData,final Callable ... task) throws InterruptedException, ExecutionException, TimeoutException {
			Future<?> submit = exec.submit(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					ThreadLocalExecutorCompletionService.initThread();
					if(task==null) new NullPointerException();
					int tackNum=task.length;
					for(Callable t:task){
						execcomp.submit(t);  
					}
				    for(int i=0; i<tackNum; i++){  
			        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
						Object object = execcomp.take().get();
						boolean call = futureData.call(object);
						if(!call) {
							return false;
						}
			        } 
					return true;
				}
			});
			try {//时间到停止future线程
				submit.get(timeOutSecond, TimeUnit.SECONDS);
			} catch (InterruptedException | ExecutionException
					| TimeoutException e) {
				submit.cancel(true);
				throw e;
			}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		submitByThread(5,new FutureData() {
			@Override
			public boolean call(Object t) {
				System.out.println("线程名称Thread："+t);
				return true;
			}
		},getTask(),getTask(),getTask(),getTask(),getTask());
		
//		submit(3,new FutureData() {
//			@Override
//			public boolean call(Object t) {
//				System.out.println("线程名称Thread："+t);
//				return true;
//			}
//		},getTask(),getTask(),getTask(),getTask(),getTask());
		
	}
	
    //得到一个任务  
    public static Callable<String> getTask(){  
        final Random rand = new Random();  
        Callable<String> task = new Callable<String>(){  
            @Override  
            public String call() throws Exception {  
                int i = rand.nextInt(10);  
//                int j = rand.nextInt(10);  
//                int sum = i*j;  
                Thread.sleep(i*1000);
                return i+"\t;UUID="+CompanyUtils.getUUID();  
            }  
        };  
        return task;  
    }  
}
