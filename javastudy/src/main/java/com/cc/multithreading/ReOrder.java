package com.cc.multithreading;

public class ReOrder {
    int a = 0;
    boolean flag = false;

    public void write() {
        a = 1;
        flag = true;
    }

    public void review() {
        a = 0;
        flag = false;
    }

    public void read() {
        if (flag) {
            int result = a * a;
            System.out.println(result);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReOrder reOrder = new ReOrder();
        class Writer implements Runnable {

            private final ReOrder reOrder;

            public Writer(ReOrder reOrder) {
                this.reOrder = reOrder;
            }

            public void run() {
                for(int i = 0; i < 10000; i++) {
                    reOrder.write();
                    reOrder.review();ke
                }
            }
        }

        class Reader implements Runnable {

            private final ReOrder reOrder;

            public Reader(ReOrder reOrder) {
                this.reOrder = reOrder;
            }

            public void run() {
                for(int i = 0; i < 10000; i++) {
                    reOrder.read();
                }
            }
        }

        Writer writer = new Writer(reOrder);
        Reader reader = new Reader(reOrder);
        Thread writerThread = new Thread(writer);
        writerThread.start();
        Thread readerThread = new Thread(reader);
        readerThread.start();

        writerThread.join();
        readerThread.join();

    }
}
