package com.jasper.factory;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class OneGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            long start = System.currentTimeMillis();
            log.info(config.getName()+"start time "+start+config.getValue()+"-pre");

            ServerHttpRequest changedRequest = exchange.getRequest().mutate().
                    headers(httpHeaders -> httpHeaders.add(config.getName(), config.getValue())).build();
//            pre
            return chain.filter(exchange.mutate().request(changedRequest).build()).then(
//post
                    Mono.fromRunnable(
                            ()->{
                                long end = System.currentTimeMillis();
                                log.info(config.getName()+start+config.getValue()+"-post");
                                long elapsedTime = end - start;
                                log.info("elapsedTime :{} ",elapsedTime);
                            }
                    )

            );
        });
    }
}
