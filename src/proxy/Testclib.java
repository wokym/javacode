package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by kuangya on 2017/2/13.
 */
public class Testclib {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestUser.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
                System.out.println(method.getName());
                Object o1 = methodProxy.invokeSuper(o, args);
                System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
                return o1;
            }
        });
        TestUser file = (TestUser)enhancer.create();
        TestUser testUser=new TestUser();
        file.getUser();
    }
}
class TestUser{
    public  void getUser() {
        System.out.println("getUser...");
    }
}
