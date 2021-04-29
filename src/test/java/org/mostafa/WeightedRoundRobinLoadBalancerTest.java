package org.mostafa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mostafa.loadbalancer.LoadBalancer;
import org.mostafa.loadbalancer.RoundRobinBalancer;
import org.mostafa.loadbalancer.WeightedRoundRobinLoadBalancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class WeightedRoundRobinLoadBalancerTest {
    private static final int NUM_OF_REQUESTS = 15;
    private static final int NUMBER_OF_REQUEST_PER_SERVER = 5;
    private static final int NUMBER_OF_SERVERS = 3;

    private static final String REPEATED_IP_3_TIMES = "222.4.5.3";
    private static final String REPEATED_IP_5_TIMES = "222.4.5.5";
    private static final String REPEATED_IP_7_TIMES = "222.4.5.7";
    private static final int IP_3_TIMES = 3;
    private static final int IP_5_TIMES = 5;
    private static final int IP_7_TIMES = 7;
    LoadBalancer balancer = null;
    Map<String,Integer> ipsMap = null;

    @Before
    public void steUp(){
        Map<String,Integer> ipsMap = Map.ofEntries(Map.entry(REPEATED_IP_3_TIMES,IP_3_TIMES),
                Map.entry(REPEATED_IP_5_TIMES,IP_5_TIMES),Map.entry(REPEATED_IP_7_TIMES,IP_7_TIMES));
        balancer = new WeightedRoundRobinLoadBalancer(ipsMap);
    }

    @Test
    public void ipIsNotNull(){
        String ip =  balancer.getIp();
        Assert.assertNotNull(ip);
    }

    @Test
    public void testNumberOfRequestForEachServer(){
        Map<String,Integer> map = new HashMap<>();
        IntStream
                .range(0, NUM_OF_REQUESTS)
                .parallel()
                .forEach(i->{
                    String ip = balancer.getIp();
                    if(map.containsKey(ip)){
                        map.put(ip, map.get(ip) + 1);
                    }else{
                        map.put(ip,1);
                    }
                });
        Assert.assertEquals(map.size(), NUMBER_OF_SERVERS);
        for(Map.Entry<String,Integer> entry : map.entrySet() ){
            Assert.assertNotNull(entry.getValue());
            int value = entry.getValue();
            String key = entry.getKey();
            if(key.equals(REPEATED_IP_3_TIMES)){
                Assert.assertEquals(value, IP_3_TIMES);
            }

            if(key.equals(REPEATED_IP_5_TIMES)){
                Assert.assertEquals(value, IP_5_TIMES);
            }

            if(key.equals(REPEATED_IP_7_TIMES)){
                Assert.assertEquals(value, IP_7_TIMES);
            }

        }

    }
}
