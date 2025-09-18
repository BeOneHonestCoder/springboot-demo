package com.net.interview;


import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main1 {
    private static int current = 1;

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Object lock = new Object();
        Thread oddThread = new Thread(() -> {
            synchronized (lock) {
                while (current <= n) {
                    if (current % 2 != 0) {
                        System.out.println(current++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            synchronized (lock) {
                while (current <= n) {
                    if (current % 2 == 0) {
                        System.out.println(current++);
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

        Thread oddThread = new Thread(() -> {
            while (current <= n) {
                try {
                    oddSemaphore.acquire();
                    if (current % 2 != 0) {
                        System.out.println(current++);
                    }
                    evenSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            while (current <= n) {
                try {
                    evenSemaphore.acquire();
                    if (current % 2 == 0) {
                        System.out.println(current++);
                    }
                    oddSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
