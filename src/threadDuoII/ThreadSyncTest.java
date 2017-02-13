package threadDuoII;

public class ThreadSyncTest implements Runnable  {
	@Override
	public  void run() {
		methodStatic();
	}
	private synchronized void method(){//锁当前对象
		System.out.println("NAME run="+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private static synchronized void methodStatic(){//锁class文件
		System.out.println("NAME run="+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
