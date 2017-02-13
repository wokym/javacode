package threadDuo;

public class ThreadLocalAdd {
	private static final ThreadLocal<String> thd=new ThreadLocal<>();
	public static void main(String[] args) {
		thd.set("hahah");
		System.out.println(thd.get());
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thr===="+thd.get());
			}
		}).start();
	}
}
