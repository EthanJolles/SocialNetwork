package com.solvd.socialNetwork.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Deadlock {
    final private static Logger LOGGER = LogManager.getLogger(Deadlock.class);

    String s1 = "Ethan";
    String s2 = "Jolles";

    public Thread t1 = new Thread() {
        public void run() {
            while (true) {
                synchronized (s1) {
                    try {
                        LOGGER.info(Thread.currentThread().getName() + " locked " + s1);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOGGER.info(e);
                    }
                    synchronized (s2) {
                        LOGGER.info(Thread.currentThread().getName() + " locked " + s2);
                        LOGGER.info(s1 + s2);
                    }
                }
            }
        }
    };

    public Thread t2 = new Thread() {
        public void run() {
            while (true) {
                synchronized (s2) {
                    LOGGER.info(Thread.currentThread().getName() + " locked " + s2);
                    synchronized (s1) {
                        LOGGER.info(Thread.currentThread().getName() + " locked " + s1);
                        LOGGER.info(s1 + s2);
                    }
                }
            }
        }
    };

}
