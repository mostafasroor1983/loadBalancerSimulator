package org.mostafa.loadbalancer;

import java.util.Collections;
import java.util.List;


public abstract class LoadBalancer {
    List<String> ipList;
    public LoadBalancer() {}
    public LoadBalancer(List<String> ipList) {
        this.ipList = Collections.unmodifiableList(ipList);
    }

    public  void setListIp(List<String> ipList) {
        this.ipList = ipList;
    }

    public abstract String getIp();
}
