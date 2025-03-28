package com.example.product;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void nacosServiceTest() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            System.out.println("service:" + service);
            nacosServiceDiscovery.getInstances(service).forEach(instance -> {
                System.out.println("ip=" + instance.getHost() + ", port=" + instance.getPort() + ", url=" + instance.getUri());
            });
        }

    }

    @Test
    public void test() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("service:" + service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            instances.forEach(instance -> {
                System.out.println("ip="+instance.getHost() + ", port=" + instance.getPort() + ", url=" + instance.getUri());
            });
        }
    }
}
