package org.mostafa.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeightedRoundRobinLoadBalancer extends RoundRobinBalancer {

   public WeightedRoundRobinLoadBalancer(Map <String, Integer> ipMap){
       super();
       List<String> listOfIps = initializeList(ipMap);
       setListIp(listOfIps);
   }

   private List<String> initializeList(Map <String, Integer> ipMap){
       List<String> listOfIps = new ArrayList<>();

       for(Map.Entry<String,Integer> entry : ipMap.entrySet()){
         for(int i = 0; i < entry.getValue(); i++){
             listOfIps.add(entry.getKey());
         }
       }

       return listOfIps;
   }

}
