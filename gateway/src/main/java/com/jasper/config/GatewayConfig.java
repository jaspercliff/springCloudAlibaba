package com.jasper.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the Gateway.
 */
@Configuration
public class GatewayConfig {
    /*
     * before after between cookie  header  host method path query(query param) weight
     * 配置式优先级高于api式
     */

//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
//    }


//    @Bean
//    public RouteLocator filterRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("filterRoute",predicateSpec ->
//                        predicateSpec.path("/**")
//                                .filters(gatewayFilterSpec ->
//                                 gatewayFilterSpec.addRequestHeadersIfNotPresent("X-Request-Color-1:blue")
//                                                  .addRequestHeadersIfNotPresent("X-Request-Color-1:red")
//                                         .addRequestParameter("color","green")
//                                )
//                                .uri("http://127.0.0.1:8081")
//                ).build();
//    }

//    @Bean
//    public RouteLocator weightRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("weightRoute",predicateSpec ->
//                        predicateSpec.weight("providerG",2)
//                                .uri("http://127.0.0.1:8081")
//                ).route("weightRoute1",predicateSpec ->
//                        predicateSpec.weight("providerG",3)
//                                .uri("http://127.0.0.1:8084")
//                )
//                .build();
//    }



    /**
     * Creates a RouteLocator for path routing.
     *
     * @param builder the RouteLocatorBuilder used to build the RouteLocator
     * @return the generated RouteLocator
     */
//    @Bean
//    public RouteLocator pathRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("hostRoute",predicateSpec ->
//                        predicateSpec.path("/provider/**")
//                                     .uri("http://127.0.0.1:8081")
//                ).route("hostRoute1",predicateSpec ->
//                        predicateSpec.path("/consumer/**")
//                                     .uri("http://127.0.0.1:8082")
//                        )
//                .build();
//    }


    /**
     * Creates a route for the specified host names and ports.
     *
     * @param builder the RouteLocatorBuilder used to create the route
     * @return a RouteLocator object representing the configured route
     */
//    @Bean
//    public RouteLocator hostRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("hostRoute",predicateSpec ->
//                        predicateSpec.host("aaa.com:8083","bbb.com:8083")
//                                .uri("http://127.0.0.1:8081")
//                ).build();
//    }

    /**
     * Constructs a new RouteLocator object with the given parameters.
     *
     * @param builder the RouteLocatorBuilder used to build the routes
     * @return a RouteLocator object
     */
//    @Bean
//    public RouteLocator headerRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("headerRoute",predicateSpec ->
//                        predicateSpec.header("X-Request-Id","\\d+")
//                                .or().
//                                header("color","gr.+")
//                                .uri("http://localhost:8081")
//                ).build();
//    }



    /**
     * Creates a route locator that routes requests based on the presence of a cookie.
     *
     * @param builder the builder used to create the route locator
     * @return the created route locator
     */

//    @Bean
//    public RouteLocator cookieRoute(RouteLocatorBuilder builder){
//        return  builder.routes().
//                route("cookieRoute",predicateSpec ->
//                     predicateSpec.cookie("city","shanghai")
//                            .uri("http://localhost:8081")
//                ).build();
//    }


    /**
     * Creates a route locator using the given builder.
     * The route locator will route requests to a specific URI based on a time range predicate.
     *=======
     * before
     * after
     * between
     *=======
     * @param builder the route locator builder
     * @return the created route locator
     */
//    @Bean
//    public RouteLocator betweenRouteLocator(RouteLocatorBuilder builder){
//        ZonedDateTime dateTime1 = LocalDateTime.now().minusDays(3).atZone(ZoneId.systemDefault());
//        ZonedDateTime dateTime2 = LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault());
//        return builder.routes().
//                route("provider",predicateSpec ->
//                        predicateSpec.between(dateTime1,dateTime2)
//                                .uri("http://localhost:8081")
//                ).build();
//    }

//    @Bean
//    public RouteLocator afterRouteLocator(RouteLocatorBuilder builder){
//        ZonedDateTime dateTime = LocalDateTime.now().minusDays(3).atZone(ZoneId.systemDefault());
//        return builder.routes().
//                route("provider",predicateSpec ->
//                        predicateSpec.after(dateTime)
//                                .uri("http://localhost:8081")
//                ).build();
//    }

    /**
     * Creates a route locator using the given builder.
     * The route locator will route requests to specific URIs based on predefined paths.
     *==========
     * path
     * =========
     * @param builder the route locator builder
     * @return the created route locator
     */
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder){
//        return builder.routes().
//                route("taobao",predicateSpec ->
//                                 predicateSpec.path("/taobao")
//                                              .uri("https://taobao.com")
//                ).route("4399",predicateSpec ->
//                                predicateSpec.path("/4399")
//                                        .uri("https://www.4399.com")
//                ).build();
//    }
}
