package com.jasper.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GatewaySentinelApi implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initCustomizedApis();
        initGatewayFlowRule();
    }

    private void initCustomizedApis() {
        ApiDefinition api1 = new ApiDefinition("some_customized_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/consumer/depart/")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_EXACT));
                    add(new ApiPathPredicateItem().setPattern("/consumer/depart/.*")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_REGEX))

                    ;
                }});
        HashSet<ApiDefinition> definitions = new HashSet<ApiDefinition>(){{
            add(api1);
        }};
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    private static void initGatewayFlowRule() {
        HashSet<GatewayFlowRule> gatewayFlowRules = new HashSet<>();
        GatewayFlowRule gatewayFlowRule = new GatewayFlowRule();

        GatewayParamFlowItem item = new GatewayParamFlowItem();
        item.setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER);
        item.setFieldName("color");
        item.setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT);
        item.setPattern("green");

        gatewayFlowRule.
                setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME).
                setResource("some_customized_api").
                setParamItem(item).
                setGrade(RuleConstant.FLOW_GRADE_QPS).
                setCount(1).
                setIntervalSec(1).
                setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT).
                setBurst(1);

        gatewayFlowRules.add(gatewayFlowRule);
        GatewayRuleManager.loadRules(gatewayFlowRules);
    }
}
