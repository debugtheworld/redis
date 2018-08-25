package com.xw.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class TestJedis2 {
    /**
     * 当对某个key进行watch时，如果其他的客户端对key进行了更改，事务可以做到取消事务操作，但是管道不可以
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            Jedis jedis = new Jedis("47.101.32.189", 6379);
            String watch = jedis.watch("ceshi");
            Pipeline pipelined = jedis.pipelined();
            pipelined.set("ceshi", "111");
            pipelined.get("ceshi");
            List<Object> objects = pipelined.syncAndReturnAll();
            System.out.println(objects + "--------" + Thread.currentThread().getName());
            jedis.unwatch();
            jedis.close();
        });

        Thread thread2 = new Thread(() -> {
            Jedis jedis = new Jedis("47.101.32.189", 6379);
            String watch = jedis.watch("ceshi");
            Pipeline pipelined = jedis.pipelined();
            pipelined.set("ceshi", "222");
            pipelined.get("ceshi");
            List<Object> objects = pipelined.syncAndReturnAll();
            System.out.println(objects + "--------" + Thread.currentThread().getName());
            jedis.unwatch();
            jedis.close();
        });
        thread1.start();
        thread2.start();
    }
}
