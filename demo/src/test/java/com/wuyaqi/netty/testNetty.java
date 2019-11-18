package com.wuyaqi.netty;

import com.google.common.util.concurrent.*;
import com.wuyaqi.netty.client.Client;
import com.wuyaqi.netty.server.Server;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author yaqi.wu
 * 2019-11-17 13:47
 */
public class testNetty {

    @Test
    public void testServClientNetty() throws InterruptedException {

        //定义两个线程，同时执行，一个线程Server start，一个线程client start
        ListeningExecutorService threadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        Callable<String> serverCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Server server = new Server(8080);
                server.start();
//                System.out.println("server start");
                return "server start";
            }
        };

        Callable<String> clientCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Client client = new Client("127.0.0.1", 8080);
                client.init();
//                System.out.println("client start");
                return "client start";
            }
        };

        ListenableFuture<String> serverFuture = threadPool.submit(serverCallable);
        ListenableFuture<String> clientFuture = threadPool.submit(clientCallable);

        FutureCallback<String> futureCallback = new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.fillInStackTrace());
            }
        };
        Futures.addCallback(serverFuture, futureCallback);
        Futures.addCallback(clientFuture, futureCallback);


        Thread.sleep(10000);

    }


}
