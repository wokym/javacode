package threadDuoII;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import util.CompanyUtils;

/**
 * 多线程公用一个executor
 * 线程提交本线程任务，将会异步执行，返回结果。
 * 
 * 1，全局任务可由一个线程池控制
 * 2，线程单独获取自己线程提交任务的结果
 * */
public class ThreadLocalExecutorCompletionService {
    private final Executor executor=Executors.newCachedThreadPool();
    private final  Map<String,BlockingQueue<Future>> mapCompletionQueue=new HashMap<>();
    private final static ThreadLocalExecutorCompletionService completionService=new ThreadLocalExecutorCompletionService();
    private ThreadLocalExecutorCompletionService(){}

    //可换成唯一相关
    private class QueueingFuture extends FutureTask<Void> {
        QueueingFuture(RunnableFuture task,String uuid) {
            super(task, null);
            this.task = task;
            this.uuid=uuid;
        }
        protected void done() { 
        	BlockingQueue<Future> blockingQueue = getBlockingQueueByUuid(uuid);
        	if(blockingQueue!=null)
        		blockingQueue.add(task);
        }
        private final Future task;
        private final String uuid;
    }

    private RunnableFuture newTaskFor(Callable task) {
            return new FutureTask(task);
    }

    public Future submit(Callable task,String uuid) {
        if (task == null) throw new NullPointerException();
        RunnableFuture f = newTaskFor(task);
        executor.execute(new QueueingFuture(f,uuid));
        return f;
    }

    public Future take(String uuid) throws InterruptedException {
        return  getBlockingQueueByUuid(uuid).take();
    }
   
    public  BlockingQueue<Future> getBlockingQueueByUuid(String uuid){
    	BlockingQueue<Future> blockingQueue = mapCompletionQueue.get(uuid);
    	return blockingQueue;
    }
    public void remove(String uuid){
    	mapCompletionQueue.remove(uuid);
    }
    public void initUuid(String uuid){
    	BlockingQueue<Future> blockingQueue=new LinkedBlockingQueue<Future>();
		mapCompletionQueue.put(uuid, blockingQueue);
    }
    
    
	/**
	 * 线程池执行任务等结果，每完成一个任务检查一次时间， 超时退出，但已经执行的任务不会中断
	 */
	public static  void submit(int timeOutSecond,FutureData futureData,Callable ... task) {
		long startTime=System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();
		if(task==null) new NullPointerException();
		completionService.initUuid(uuid);
		int tackNum=task.length;
		for(Callable t:task){
			completionService.submit(t,uuid);  
		}
	    for(int i=0; i<tackNum; i++){  
        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
			Object object=null;
			try {
				object = completionService.take(uuid).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			boolean call = futureData.call(object);
			long endTime = System.currentTimeMillis();
			if(!call||(endTime-startTime)>timeOutSecond*1000) {
				break;
			}
        } 
	    completionService.remove(uuid);
	}
	/**
	 * 线程执行等到结果，时间到了后抛出超时异常。但已经执行的任务不会中断
	 * @throws Exception 
	 */
	public static  void submitByThread(int timeOutSecond,final FutureData futureData,final Callable ... task) throws InterruptedException, ExecutionException, TimeoutException {
		if(task==null) new NullPointerException();
		final String uuid = UUID.randomUUID().toString();
			Future<?> submit = ((ExecutorService) completionService.executor).submit(new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					completionService.initUuid(uuid);
					if(task==null) new NullPointerException();
					int tackNum=task.length;
					for(Callable t:task){
						completionService.submit(t,uuid);  
					}
				    for(int i=0; i<tackNum; i++){  
			        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
						Object object=null;
						try {
							object = completionService.take(uuid).get();
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
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
			}finally{
				completionService.remove(uuid);
			}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		completionService.executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					submitByThread(5,new FutureData() {
						@Override
						public boolean call(Object t) {
							System.out.println("线程名称Thread1："+t);
							return true;
						}
					},getTask("Thread1"),getTask("Thread1"),getTask("Thread1"),getTask("Thread1"),getTask("Thread1"));
				} catch (InterruptedException | ExecutionException
						| TimeoutException e) {
					e.printStackTrace();
				}
			}
		});
		
		completionService.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				submit(5,new FutureData() {
					@Override
					public boolean call(Object t) {
						System.out.println("线程名称Thread2："+t);
						return true;
					}
				},getTask("Thread2"),getTask("Thread2"),getTask("Thread2"),getTask("Thread2"),getTask("Thread2"));
			}
		});
		
		
	}
    //得到一个任务  
    public static Callable<String> getTask(final String ul){  
        final Random rand = new Random();  
        Callable<String> task = new Callable<String>(){  
            @Override  
            public String call() throws Exception {  
                int i = rand.nextInt(10);  
//                int j = rand.nextInt(10);  
//                int sum = i*j;  
                Thread.sleep(i*1000);
                return ul+"\t;UUID="+UUID.randomUUID().toString(); 
            }  
        };  
        return task;  
    }  
}
