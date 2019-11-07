package com.tcwong.threadpoolutil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池工具类
 */
public class ThreadPoolUtil {
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程池-%d").build();

    private static BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>();

    public static ExecutorService executorService =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()
                    , Runtime.getRuntime().availableProcessors()
                    , 30
                    , TimeUnit.SECONDS
                    , blockingDeque
                    , threadFactory
                    , new ThreadPoolExecutor.AbortPolicy());
}
