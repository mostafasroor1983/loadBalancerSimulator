package org.mostafa;

import org.mostafa.loadbalancer.LoadBalancer;
import org.mostafa.loadbalancer.RandomLoadBalancer;
import org.mostafa.loadbalancer.RoundRobinBalancer;
import org.mostafa.loadbalancer.WeightedRoundRobinLoadBalancer;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App
{
    private static final int NUM_OF_REQUESTS = 15;
    public static void main( String[] args )
    {
        List<String> listOfIps = List.of(new String[] {"222.4.5.1","222.4.5.2","222.4.5.3"});

        printNextTurn("Random Simulation");
        LoadBalancer randomBalancer = new RandomLoadBalancer(listOfIps);
        simulateConcurrentClientRequest(randomBalancer, NUM_OF_REQUESTS);

        printNextTurn("RoundRobin Simulation");
        LoadBalancer roundRobinBalancer = new RoundRobinBalancer(listOfIps);
        simulateConcurrentClientRequest(roundRobinBalancer, NUM_OF_REQUESTS);

        printNextTurn("Weighted RoundRobin Simulation");
        Map<String,Integer> ipsMap = Map.ofEntries(Map.entry("222.4.5.3",3),
                Map.entry("255.71.81.5",5),Map.entry("254.29.20.7",7));
        LoadBalancer weightedRoundRobinBalancer = new WeightedRoundRobinLoadBalancer(ipsMap);
        simulateConcurrentClientRequest(weightedRoundRobinBalancer, NUM_OF_REQUESTS);
    }


    private static void printNextTurn(String name) {
        System.out.println("---");
        System.out.println("Clients starts to send requests to " + name + " Load Balancer");
        System.out.println("---");
    }

    private static void simulateConcurrentClientRequest(LoadBalancer loadBalancer, int numOfCalls) {

        IntStream
                .range(0, numOfCalls)
                .parallel()
                .forEach(i ->
                        System.out.println(
                                "IP: " + loadBalancer.getIp()
                                        + " --- Request from Client: " + i
                                        + " --- [Thread: " + Thread.currentThread().getName() + "]")
                );
    }

}
