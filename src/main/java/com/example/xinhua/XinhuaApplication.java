package com.example.xinhua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class XinhuaApplication implements ApplicationListener<ApplicationReadyEvent> {

    static ThreadLocal<Object> threadLocal=new ThreadLocal<>();



    public static void main(String[] args) throws Exception {
        Logger Logger = LoggerFactory.getLogger(XinhuaApplication.class);
        Logger.info("AAA-启动前-AAA");
        SpringApplication.run(XinhuaApplication.class, args);

        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(BinSearch(arr, 3));

//        //线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            executorService.execute(()->{
//                Byte[] bytes = new Byte[50240 * 10240];//50m
//                threadLocal.set(bytes);
//                //内存泄漏的处理方法
//                threadLocal.remove();
//            });
//        }
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(1, TimeUnit.MINUTES);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }


        //线程学习
        Thread thread1 = new Thread(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set(111);
            System.out.println(threadLocal.get());
            System.out.println("线程启动1");
        },"thread-1");
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程启动2");
        },"thread-2");

        thread2.start();
        try{
            thread2.join();
        }catch (Exception e){
            System.out.println("线程启动异常");
        }
        thread1.start();
        System.out.println("线程名字是:"+thread1.getName());;



    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("BBB-启动了-BBB");
    }


    //二分查找
    public static int BinSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int min = left + (right - left) / 2;
            if (nums[min] == target) {
                return min;
            } else if (nums[min] < target) {
                left = min + 1;
            } else {
                right = min - 1;
            }
        }
        return -1;
    }

}
