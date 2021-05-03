package org.mostafa.loadbalancer;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRobinBalancer extends LoadBalancer {

    private int counter = 0;
    private final ReentrantLock lock;

    public RoundRobinBalancer() {
        this.lock = new ReentrantLock();
    }

    public RoundRobinBalancer(List<String> ipList) {
        super(ipList);
        this.lock = new ReentrantLock();

    }

    // round robin distribute requests evenly between all servers
    @Override
    public String getIp() {
        String ip = "";
        try {
            lock.lock();
            ip = ipList.get(counter);
            counter += 1;

            if (counter == ipList.size()) {
                counter = 0;
            }
            lock.unlock();
        } finally {
            return ip;
        }


    }
}
