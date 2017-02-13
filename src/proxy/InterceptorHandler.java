package proxy;

import java.lang.reflect.Method;

/**
 * 拦截器接口，用户使用com.cuishen.myAop.MyProxyImpl动态代理前，请先实现本接口，
 * 在执行动态代理对象的方法时会自动反射到invoke方法，被代理的对象、方法和参数将做为
 * 参数传递给invoke方法
 * @author cuishen
 * @version 1.0
 * @see ProxyMy
 */
public interface InterceptorHandler {

    /**
     * 调用动态代理对象的方法将反射本方法，可在本方法实现中添加类似AOP的事前事后操作，只有在本方法体中加入如下代码
     * <br>
     * <code>
     * Object returnObj = method.invoke(obj, args);
     * <br>
     * ...
     * <br>
     * return returnObj;
     * </code>
     * <br>
     * 被代理的方法才会被执行，返回值将返回给代理最后返回给程序
     * @param obj Object 被代理的对象
     * @param method Method 被代理对象的方法
     * @param args Object[] 被代理对象的方法的参数
     * @return Object 被代理对象的方法执行后的返回值
     * @throws Throwable
     */
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable;
}
