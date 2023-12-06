package com.example.consumer01.contorller;

import com.example.consumer01.bean.Depart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@RestController
@RequestMapping("/depart")
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
@RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
public class DepartController {
    private final RestTemplate restTemplate;
    private final DiscoveryClient client;

    @Value("${names}")
    private String names;

    private final String BASE_URL = "http://provide-service/depart/";
    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart){
        return restTemplate.postForObject(BASE_URL, depart, Boolean.class);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable Integer id){
        restTemplate.delete(BASE_URL  + id);
    }

    @PutMapping("/")
    private void update(@RequestBody Depart depart){
        restTemplate.put(BASE_URL,depart);
    }

    @GetMapping("/{id}")
    private Depart getById(@PathVariable Integer id){
        return restTemplate.getForObject(BASE_URL+id,Depart.class);
    }
    @GetMapping("/")
    private List<Depart> getList(){
        return restTemplate.getForObject(BASE_URL,List.class);
    }

    /**
     * 服务在启动后回自动从nacos注册中心下载注册表缓存到本地
     */

    @GetMapping("/client")
    private List<String> getServiceList(){
        List<String> services = client.getServices();
        services.forEach(
                service ->{
//                    获取该服务的所有实例
                    List<ServiceInstance> instances = client.getInstances(service);
                    instances.forEach(instance ->{
//                        serviceName = serviceId
                        System.err.println(service);
                        System.err.println(instance.getServiceId());
                        System.err.println(instance.getHost());
                        System.err.println(instance.getPort());
                        System.err.println(instance.getUri());
                    });
                }
        );
        return services;
    }

    @GetMapping("/getValue")
    private String getValue(){
        return names;
    }

}
