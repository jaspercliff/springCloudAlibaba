package com.jasper.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TwoGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            log.info(config.getName()+config.getValue()+"-pre");
//            pre
            return chain.filter(exchange).then(
//post
                    Mono.fromRunnable(
                            ()->{
                                log.info(config.getName()+config.getValue()+"-post");
                            }
                    )
            );
        });
    }
}
