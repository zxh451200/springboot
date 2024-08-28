package com.example.xinhua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class XinhuaApplication implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        Logger Logger = LoggerFactory.getLogger(XinhuaApplication.class);
        Logger.info("AAA-启动前-AAA");
        SpringApplication.run(XinhuaApplication.class, args);

        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(BinSearch(arr, 3));
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
