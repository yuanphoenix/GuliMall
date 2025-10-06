package com.atguigu.gulimall.search.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ThreadTest {

  public static ExecutorService executorService = Executors.newFixedThreadPool(10);


  public static void main(String[] args) {
    CompletableFuture completableFuture = CompletableFuture.supplyAsync(
        () -> 0, executorService);
  }

  public static void thread1(String[] args) {
//    System.out.println("开始");
//    Thread01 thread01 = new Thread01();
//    thread01.start();
//    System.out.println("结束");
//
//    Runnable01 runnable01 = new Runnable01();
//    new Thread(runnable01).start();
//
//    //====
//    FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable01());
//    new Thread(integerFutureTask).start();
//    try {
////阻塞等待
//      System.out.println(integerFutureTask.get());
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    } catch (ExecutionException e) {
//      throw new RuntimeException(e);
//    }

    //给线程池提交任务即可。为什么用线程池?线程的开销很大，应该将所有的异步任务都交给线程池来做。
//当前系统中，要整一两个线程池，每个异步任务都提交给线程池。
//      executorService.execute(new Runnable01());

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new AbortPolicy());
  }


  public static class Runnable01 implements Runnable {

    @Override
    public void run() {
      System.out.println("当前runnable线程" + Thread.currentThread().getId());
      int i = 10 / 2;
      System.out.println("运行runnable结果" + i);
    }
  }

  public static class Callable01 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      System.out.println("当前线程" + Thread.currentThread().getId());
      int i = 10 / 2;
      System.out.println("运行结果" + i);
      return i;
    }
  }

  public static class Thread01 extends Thread {

    @Override
    public void run() {
      System.out.println("当前线程" + Thread.currentThread().getId());
      int i = 10 / 2;
      System.out.println("运行结果" + i);
    }
  }
}
