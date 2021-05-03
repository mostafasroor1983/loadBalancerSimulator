package org.mostafa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mostafa.loadbalancer.LoadBalancer;
import org.mostafa.loadbalancer.RoundRobinBalancer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class RoundRobinLoadBalancerTest {
    private static final int NUM_OF_REQUESTS = 15;
    private static final int NUMBER_OF_REQUEST_PER_SERVER = 5;
    private static final int NUMBER_OF_SERVERS = 3;

    LoadBalancer balancer = new RoundRobinBalancer();
    List<String> listOfIps = null;

    @Before
    public void steUp(){
        listOfIps = List.of(new String[] {"222.4.5.1","222.4.5.2","222.4.5.3"});
        balancer.setListIp(listOfIps);
    }

    @Test
    public void ipIsNotNull(){
        String ip =  balancer.getIp();
        Assert.assertNotNull(ip);
    }


    // simulate 15 requests in parallel
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
            Assert.assertEquals(value, NUMBER_OF_REQUEST_PER_SERVER);
        }

    }
}
