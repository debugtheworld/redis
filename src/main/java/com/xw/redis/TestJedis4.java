package com.xw.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;

public class TestJedis4 {
    public static void main(String[] args) throws IOException {
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("47.101.32.189",7001));
        nodes.add(new HostAndPort("47.101.32.189",7002));
        nodes.add(new HostAndPort("47.101.32.189",7003));
        nodes.add(new HostAndPort("47.101.32.189",7004));
        nodes.add(new HostAndPort("47.101.32.189",7005));
        nodes.add(new HostAndPort("47.101.32.189",7006));

        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("ceshi","测试集群");
        System.out.println(cluster.get("ceshi"));
        cluster.close();

    }
}
