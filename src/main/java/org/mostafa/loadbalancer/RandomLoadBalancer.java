package org.mostafa.loadbalancer;

import java.util.List;
import java.util.Random;

public class RandomLoadBalancer extends LoadBalancer {

    public RandomLoadBalancer(List<String> ipList) {
        super(ipList);
    }

    public RandomLoadBalancer() {super();}

    @Override
    public String getIp() {
        Random random = new Random();
        return ipList.get(random.nextInt(ipList.size()));
    }
}
