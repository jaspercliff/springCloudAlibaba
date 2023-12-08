package com.jasper.factory;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    public AuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("username","passwd");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange ->{
            HttpHeaders headers = exchange.getRequest().getHeaders();
            List<String> pwd = headers.get(config.getUsername());
            assert pwd != null;
            String[] split = pwd.get(0).split(",");
            for (String s : split) {
                if (s.equals(config.getPasswd())){
                    return true;
                }
            }
            return false;
        };
    }

    @Data
    public static class Config {
        @NotNull
        private String username;
        @NotNull
        private String passwd;
    }
}
