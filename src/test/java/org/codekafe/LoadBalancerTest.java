package org.codekafe;

import org.junit.jupiter.api.Test;

import java.util.*;

class LoadBalancerTest {

    @Test
    void getNextServer() {

        ServerRegistry registry = new ServerRegistry();
        registry.registerServer(new Server("S101"));
        registry.registerServer(new Server("S102"));
        registry.registerServer(new Server("S103"));

        // Initialized the load balancer
        LoadBalancer loadBalancer = new LoadBalancer(registry);
        Map<String, Integer> requstMap = getRequstMap(loadBalancer);
        requstMap.entrySet().forEach(v -> System.out.println(v.getKey() +"-> requests: "+ v.getValue()));

    }

    private static Map<String, Integer> getRequstMap(LoadBalancer loadBalancer) {
        Map<String, Integer> requstMap = new HashMap<>();
        for( int i = 0; i < 5; i++) {
            Optional<Server> server = loadBalancer.getNextServer();
            Integer reqIndex = i;

            server.ifPresent(v ->{
                       requstMap.put(v.getName(),
                                requstMap.getOrDefault(v.getName(), 0) + 1);
                    }

            );
        }
        return requstMap;
    }

    @Test
    void testNextAvailableServer(){

        ServerRegistry registry = new ServerRegistry();
        Server server1 = new Server("S101");
        Server server2 = new Server("S102");
        Server server3 = new Server("S103");

        registry.registerServer(server1);
        registry.registerServer(server2);
        registry.registerServer(server3);

        // Initialized the load balancer
        LoadBalancer loadBalancer = new LoadBalancer(registry);

        server1.setStatus(false);
        //registry.deRegisterServer(server2);

        for (int i = 0; i < 5; i++) {
            loadBalancer.getNextServer();
        }

        //registry.deRegisterServer(server1);
        registry.deRegisterServer(server3);

        Optional<Server> server = loadBalancer.getNextServer();
        server.ifPresent(s -> System.out.println(s.getName()));
    }
}