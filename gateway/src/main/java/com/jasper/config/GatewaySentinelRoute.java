package com.jasper.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;


//@Component
public class GatewaySentinelRoute implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initGatewayFlowRule();
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
                setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID).
                setResource("get").
                setParamItem(item).
                setGrade(RuleConstant.FLOW_GRADE_QPS).
                setCount(2).
                setIntervalSec(1).
                setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT).
                setBurst(1);

        gatewayFlowRules.add(gatewayFlowRule);
        GatewayRuleManager.loadRules(gatewayFlowRules);
    }
}
