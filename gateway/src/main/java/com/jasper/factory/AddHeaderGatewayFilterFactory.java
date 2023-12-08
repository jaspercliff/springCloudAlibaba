package com.jasper.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Map;

//@Component
public class AddHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return ((exchange, chain) -> {
            Map<String, String> uriVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);
            String bean = uriVariables.get("bean");
            String id = uriVariables.get("id");

            System.err.println("bean-id"+bean+"-"+id);

            ServerHttpRequest changedRequest = exchange.getRequest().mutate().
                    headers(httpHeaders -> httpHeaders.add(config.getName(), config.getValue())).build();
            return chain.filter(exchange.mutate().request(changedRequest).build()).then();
        });
    }
}
