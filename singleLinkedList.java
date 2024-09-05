package com.xinhua.user.controller;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Consumer;
// 单向链表
public class SingleLinkedList implements Iterable<Integer> {

    private Node head = null;

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head;

            @Override
            public boolean hasNext() { // 是否又下一个元素
                return p != null;
            }

            @Override
            public Integer next() {// 返回当前值,并指向下一个元素
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    // 节点类
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    // 在特定索引位置插入数据
    public void insert(int index, int value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node preNode = getNodeByIndex(index - 1);
        if (preNode == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        preNode.next = new Node(value, preNode.next);
    }

    // 链表头添加值
    public void addFirst(int value) {
        // 链表为空
//        head = new Node(value, null);
        //链表非空
        head = new Node(value, head);
    }

    // 添加最后的节点
    public void addLast(int value) {
        Node last = getLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        //链表非空
        last.next = new Node(value, null);
    }

    // 根据索引获取值
    public int get(int index) {
        Node node = getNodeByIndex(index);
        if (node == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(String.format("index [%d] 不合法%n", index));
            }
        }
        return node.value;
    }

    // 根据索引获取节点
    public Node getNodeByIndex(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    // 删除首部节点
    public void delFirst(int index) {
        if (head == null) {
            try {
                throw new Exception("没有节点可以删除");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        head = head.next;
    }

    //    根据索引删除
    public void delByIndex(int index) {
        if (index == 0) {
            delFirst(index);
            return;
        }
        Node prev = getNodeByIndex(index - 1);
        if (prev == null) {
            try {
                throw new Exception("有问题");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Node removed = prev.next;
        prev.next = removed.next;
    }

    //获取最后一个节点
    public Node getLast() {
        if (head == null) {
            return null;
        }
        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    // 循环打印节点
    public void loop(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
//            System.out.println(p.value);
            p = p.next;
        }
    }

    // 迭代器循环
    public void loop1(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }
}
