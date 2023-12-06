package com.example.consumer01.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class SentinelFlowConfig implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initFlowRule();
    }

    private static void initFlowRule() {
        ArrayList<FlowRule> rules = new ArrayList<>();
        FlowRule rtRule = getFlowRule();
        rules.add(rtRule);
        FlowRuleManager.loadRules(rules);
    }
    private static FlowRule getFlowRule(){
        FlowRule rule = new FlowRule();
        rule.setResource("departListHandle");
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
        rule.setRefResource("/depart/all");
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        rule.setWarmUpPeriodSec(10);
//       qps 阈值
        rule.setCount(3);
        rule.setLimitApp("default");
        return rule;
    }

}
