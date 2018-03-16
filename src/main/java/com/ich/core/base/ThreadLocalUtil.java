package com.ich.core.base;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 多线程独立全局ID
 * @author 霍俊
 *
 */
public class ThreadLocalUtil {
	private final static AtomicInteger num = new AtomicInteger(10000);  
    private final static ThreadLocal<Integer> id = new ThreadLocal<Integer>() {  
        @Override  
        protected Integer initialValue() {  
            return num.incrementAndGet();  
        }  
    };  
    
    /**
     * 请求整个环境中此次线程的唯一ID
     * @return
     */
    public static int getID() {  
        return id.get();  
    }
    
}
