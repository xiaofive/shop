package com.shop.common.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:利用ReentrantLock进行抢红包程序
 * Author: wangyang
 * Date: 2021-05-13
 * Time: 0:56
 */
public class ReenTrantLockTest extends Thread {

    private static Integer j = 0; //定义成static相当于公共资源，才有用锁操作进行同步的意义
    private static ReentrantLock reentrantLock = new ReentrantLock(false);//非公平模式

    @Override
    public void run() {

        for (int i = 0; i < 200000; i++) {
            //ReentrantLock reentrantLock = new ReentrantLock(false);//定义在该处JVM会进行锁消除，起不到同步的作用,reentrantLock也不是同一把锁，那如果定义在Rest层呢？
            System.out.println(reentrantLock);
            reentrantLock.lock();
            try {
                j++;
                System.out.println(Thread.currentThread().getId() + "_" + Thread.currentThread().getName() + " 正在占用该同步块：" + j);
                //Thread.sleep(5);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }


        }

    }

    /**
     * Created with IntelliJ IDEA.
     * Description: 两个线程，每个线程对变量j进行20W次自增操作
     * Author: wangyang
     * Date: 2021-05-13
     * Time: 0:47
     */
    public static void main(String[] args) {
        ReenTrantLockTest reenTrantLockTest = new ReenTrantLockTest();
        ReenTrantLockTest reenTrantLockTest1 = new ReenTrantLockTest();
        reenTrantLockTest.start();
        reenTrantLockTest1.start();


        System.out.println("last:" + j);
    }

}
