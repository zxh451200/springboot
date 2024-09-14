package com.example.xinhua.controller;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;

// 双向链表
public class DoubleLinkedList implements Iterable {
    @NotNull
    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Object next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node {
        int value;
        Node prev;
        Node next;

        public Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head; // 头哨兵

    private Node tail; // 尾哨兵

    public DoubleLinkedList() {
        head = new Node(0, null, null);
        tail = new Node(0, null, null);
        head.next = tail;
        tail.next = head;
    }

    // 根据索引获取node
    public Node getByIndex(int index) {
        int i = 0;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    // 插入末尾
    public void addLast(int value) {
        Node prev = tail.prev;
        Node node = new Node(value, prev, tail);
        prev.next = node;
        tail.prev = node;
    }

    // 删除末尾
    public void removeLast() {
        Node remove = tail.prev;
        if (remove == head) {
            return;
        }
        Node prev = remove.prev;
        prev.next = tail;
        tail.prev = prev;
    }


    // 根据索引插入
    public void insert(int index, int value) {
        Node pre = getByIndex(index - 1);
        Node next = pre.next;
        Node insert = new Node(value, pre, next);
        pre.next = insert;
        next.prev = insert;
    }
}
