package threadDuoII;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
/**
 * 多线程公用一个executor
 * 线程提交本线程任务，将会异步执行，返回结果。
 * 
 * 1，全局任务可由一个线程池控制
 * 2，线程单独获取自己线程提交任务的结果
 * */
public class CopyOfThreadLocalExecutorCompletionService<V> implements CompletionService<V>{
    private final Executor executor;
    private final static ThreadLocal<BlockingQueue<Future>>  completionQueueLocals=new InheritableThreadLocal<>();
    private final static Map<String,BlockingQueue<Future>> map=new HashMap<>();
    //可换成唯一相关
    private class QueueingFuture extends FutureTask<Void> {
        QueueingFuture(RunnableFuture<V> task) {
            super(task, null);
            this.task = task;
        }
        protected void done() { 
        	BlockingQueue<Future> blockingQueue = completionQueueLocals.get();
        	blockingQueue.add(task);
        }
        private final Future<V> task;
    }

    private RunnableFuture<V> newTaskFor(Callable<V> task) {
            return new FutureTask<V>(task);
    }

    private RunnableFuture<V> newTaskFor(Runnable task, V result) {
            return new FutureTask<V>(task, result);
    }

    public CopyOfThreadLocalExecutorCompletionService(Executor executor) {
        if (executor == null)
            throw new NullPointerException();
        this.executor = executor;
    }

    public CopyOfThreadLocalExecutorCompletionService(Executor executor,
                                     BlockingQueue<Future<V>> completionQueue) {
        if (executor == null || completionQueue == null)
            throw new NullPointerException();
        this.executor = executor;
    }

    public Future<V> submit(Callable<V> task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<V> f = newTaskFor(task);
        executor.execute(new QueueingFuture(f));
        return f;
    }

    public Future<V> submit(Runnable task, V result) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<V> f = newTaskFor(task, result);
        executor.execute(new QueueingFuture(f));
        return f;
    }

    public Future<V> take() throws InterruptedException {
        return  completionQueueLocals.get().take();
    }

    public Future<V> poll() {
        return  completionQueueLocals.get().poll();
    }

    public Future<V> poll(long timeout, TimeUnit unit)
            throws InterruptedException {
        return  completionQueueLocals.get().poll(timeout, unit);
    }
    public static void initThread(){
    	BlockingQueue<Future> blockingQueue = completionQueueLocals.get();
    	if(blockingQueue==null) {
    		completionQueueLocals.set(new LinkedBlockingQueue<Future>());
    	}else
    		throw new RuntimeException("不能重复启动任务模式！");
    }
}
