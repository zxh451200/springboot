package com.xinhua.user.controller;


@RestController
@RequestMapping("/user")
public class UserController {
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    @GetMapping("/fanoutSendMsg")
    public void fanoutSendMsg() {
        init(3);
        print();
        move(3,a,b,c);
    }

    private static void print() {
        System.out.println("--------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("--------");
    }

    /**
     *
     * @param n 盘子数
     * @param a 源盘
     * @param b 中间盘
     * @param c 最终位置盘
     */
    public  void move(int n,
                      LinkedList<Integer> a,
                      LinkedList<Integer> b,
                      LinkedList<Integer> c
                      ){
        if (n==0){
            return;
        }
        move(n-1,a,c,b);
        c.addLast(a.removeLast());
        print();
        move(n-1,b,a,c);
    }

    
    // 递归优化  记忆法也叫备忘录
    static void init(int n) {
        for (int i = n; i >0 ; i-- ){
            a.addLast(i);
        }
    }

}
