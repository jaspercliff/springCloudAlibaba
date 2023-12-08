package com.jasper.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;

@Component
public class GatewaySentinelBlockConfig implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initBlockHandler();
    }

    private static void initBlockHandler() {
//        重定向
//        GatewayCallbackManager.setBlockHandler(new RedirectBlockRequestHandler("https://baidu.com"));

        GatewayCallbackManager.setBlockHandler(((exchange, t) -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("uri",exchange.getRequest().getURI());
            map.put("message","too many request");
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS).
            contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(map));
        }));
    }
}
