package gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
//3、弱引用（WeakReference）
public class ReferenceTest {  
//	弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，
//	Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();  
  
    public static void checkQueue() {  
        Reference<? extends VeryBig> ref = null;  
        while ((ref = rq.poll()) != null) {  
            if (ref != null) {  
            	//对象已经被回收
                System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).get());  
                System.out.println("In queue: " + ((VeryBigWeakReference) (ref)).id);
            }  
        }  
    }  
  
    public static void main(String args[]) {  
        int size = 3;  
        LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<WeakReference<VeryBig>>();  
        for (int i = 0; i < size; i++) {  
            weakList.add(new VeryBigWeakReference(new VeryBig("Weak " + i), rq));  
            System.out.println("Just created weak: " + weakList.getLast());  
  
        }  
  
        System.gc();   
        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成  
            Thread.currentThread().sleep(6000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        checkQueue();  
    }  
}  
  
class VeryBig {  
    public String id;  
    // 占用空间,让线程进行回收  
    byte[] b = new byte[2 * 1024];  
  
    public VeryBig(String id) {  
        this.id = id;  
    }  
    protected void finalize() {  
        System.out.println("Finalizing VeryBig " + id);  
    }  
}  
  
class VeryBigWeakReference extends WeakReference<VeryBig> {  
    public String id;  
  
    public VeryBigWeakReference(VeryBig big, ReferenceQueue<VeryBig> rq) {  
        super(big, rq);  
        this.id = big.id;  
    }  
  
    protected void finalize() {  
        System.out.println("Finalizing VeryBigWeakReference " + id);  
    }  
}  