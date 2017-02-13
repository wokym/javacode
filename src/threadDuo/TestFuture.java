package threadDuo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture {
	static class Job<Object> implements Callable<Object>{
		@Override
		public Object call() throws Exception {
			System.out.println("call()......");
			return (Object) "PP";
		}
		
	}
	public static void main(String[] args) throws Exception, ExecutionException {
		FutureTask future=new FutureTask<>(new Job<>());
		new Thread(future).start();
		System.out.println("do something......");
		Object object = future.get();
	}
}
