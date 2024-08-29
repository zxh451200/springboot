package com.example.xinhua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
public class XinhuaApplication implements ApplicationListener<ApplicationReadyEvent> {

    static ThreadLocal<Object> threadLocal=new ThreadLocal<>();

    static  ThreadLocal inheritableThreadLocal=new InheritableThreadLocal<>();



    public static void main(String[] args) throws Exception {
        Logger Logger = LoggerFactory.getLogger(XinhuaApplication.class);
        Logger.info("AAA-启动前-AAA");
        SpringApplication.run(XinhuaApplication.class, args);

        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        HashMap<String, Object> hashmap1 = new HashMap<>();
        hashmap1.put("name","xin");
        hashmap1.put("age",18);
        hashmap1.put("sex","男");
        hashMaps.add(hashmap1);
        HashMap<String, Object> hashmap2 = new HashMap<>();
        hashmap2.put("name","hua");
        hashmap2.put("age",19);
        hashmap2.put("sex","男");
        hashMaps.add(hashmap2);
        System.out.println(hashMaps.stream().map(map->map.get("name")).collect(Collectors.toSet()));


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

        inheritableThreadLocal.set("我是父线程数据");

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
            System.out.println("子线程拿到复父线程数据:"+inheritableThreadLocal.get());
            System.out.println("---------------------");
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
