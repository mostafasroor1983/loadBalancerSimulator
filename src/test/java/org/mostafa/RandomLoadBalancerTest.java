package org.mostafa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mostafa.loadbalancer.RandomLoadBalancer;

import java.util.ArrayList;
import java.util.List;

public class RandomLoadBalancerTest {
    RandomLoadBalancer balancer = new RandomLoadBalancer();
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


}
