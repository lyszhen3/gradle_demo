package test.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pc on 2017-06-01.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @description
 * @since 3.0.0-SNAPSHOT
 */
public class TestReentrantLock {
    public static ReentrantLock lock=new ReentrantLock();
      public   void test1(){

          try {
              if(lock.tryLock(6,TimeUnit.SECONDS)){
                  System.out.println(Thread.currentThread().getName()+"老子获取到锁了感谢天");
                    Thread.sleep(5000);
                  System.out.println(System.currentTimeMillis());
                  System.out.println("卧槽");
              }else{
                  System.out.println(Thread.currentThread().getName()+"卧槽等死了都没锁");
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }finally {


                  int holdCount = lock.getHoldCount();
                  System.out.println("释放锁前" + holdCount);
                  if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                   }
                  System.out.println("释放锁后" + lock.getHoldCount());

          }
      }

    public static void main(String[] args) {
        TestReentrantLock test=new TestReentrantLock();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        },"啊狗");

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        },"啊猫");

        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        },"啊猪");
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
