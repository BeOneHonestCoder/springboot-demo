package com.net.interview;


public class Question622 {

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        circularQueue.enQueue(1);
        circularQueue.enQueue(2);
        circularQueue.enQueue(3);
        circularQueue.enQueue(4);
        circularQueue.rear();
        circularQueue.isFull();
        circularQueue.deQueue();
        circularQueue.enQueue(4);
        circularQueue.rear();
    }

    private static class MyCircularQueue {
        private int head;
        private int tail;
        private int capacity;
        private int[] elements;

        public MyCircularQueue(int k) {
            capacity = k + 1;
            elements = new int[capacity];
            tail = head = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[tail] = value;
            tail = (tail + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[head];
        }

        public int rear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(tail - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return tail == head;
        }

        public boolean isFull() {
            return ((tail + 1) % capacity) == head;
        }
    }

}
