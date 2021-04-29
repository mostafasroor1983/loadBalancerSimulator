package org.mostafa.loadbalancer;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRobinBalancer extends LoadBalancer {

    private  int counter = 0;
    public RoundRobinBalancer(){
    }
    public RoundRobinBalancer(List<String> ipList) {
        super(ipList);
    }

    @Override
    public  synchronized String getIp() {
            String ip = ipList.get(counter);
            counter +=1;
            if (counter == ipList.size() ) {
                counter = 0;
            }

            return ip;
    }
}
