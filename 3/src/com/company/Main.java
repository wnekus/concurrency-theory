package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	Buffer buffer = new Buffer(100);

	Producer producer = new Producer(buffer);
	Consumer consumer = new Consumer(buffer);

	producer.start();
	consumer.start();

	producer.join();
	consumer.join();
    }
}
