package com.example.wrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class WrapperFactory {
    @SuppressWarnings("unchecked")
    public static <T> T wrap(T target, Class<T> iface, Object wrapper) {
        return (T) Proxy.newProxyInstance(
            iface.getClassLoader(),
            new Class<?>[]{iface},
            new DelegatingHandler(target, wrapper)
        );
    }

    private static class DelegatingHandler implements InvocationHandler {
        private final Object target;
        private final Object wrapper;

        DelegatingHandler(Object target, Object wrapper) {
            this.target = target;
            this.wrapper = wrapper;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                Method m = wrapper.getClass().getMethod(method.getName(), method.getParameterTypes());
                return m.invoke(wrapper, args);
            } catch (NoSuchMethodException e) {
                return method.invoke(target, args);
            }
        }
    }
}
