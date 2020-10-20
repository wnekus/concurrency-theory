package com.company;

class Consumer extends Thread {
    private Buffer buf;

    public Consumer(Buffer buf){
        this.buf = buf;
    }

    public void run() {
        for(int i = 0; i < 100; ++i) {
            System.out.println("Consumer get " + buf.get());
        }
    }
}