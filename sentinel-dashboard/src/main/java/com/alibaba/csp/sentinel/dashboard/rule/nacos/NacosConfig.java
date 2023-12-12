/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import java.util.List;
import java.util.Properties;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Configuration
public class NacosConfig {

    @Value("${nacos.addr}")
    private String nacosAddr;
    @Value("${nacos.username}")
    private String nacosUsername;
    @Value("${nacos.password}")
    private String nacosPassword;

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }
    //system rule
    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }
//authority rule
    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<AuthorityRuleEntity>> authorityRuleEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }
// degrade rule
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }
//    param
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramRuleEntityEncoder() {
        return JSON::toJSONString;
    }
    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }
    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosAddr);
        properties.put(PropertyKeyConst.USERNAME,nacosUsername);
        properties.put(PropertyKeyConst.PASSWORD,nacosPassword);
        return ConfigFactory.createConfigService(properties);
    }
}
