package com.example.consumer01.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class SentinelParamConfig implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initParamRule();
    }

    private static void initParamRule() {
        ArrayList<ParamFlowRule> rules = new ArrayList<>();
        ParamFlowRule rtRule = getAuthorityRule();
        rules.add(rtRule);
        ParamFlowRuleManager.loadRules(rules);
    }
    private static ParamFlowRule getAuthorityRule(){
        ParamFlowRule rule = new ParamFlowRule();
        rule.setResource("paramHandle");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rule.setParamIdx(1);
        rule.setDurationInSec(5);
        ArrayList<ParamFlowItem> paramFlowItems = new ArrayList<>();

        ParamFlowItem paramFlowItem = new ParamFlowItem();
        paramFlowItem.setClassType("String");
        paramFlowItem.setCount(200);
        paramFlowItem.setObject("admins");

        paramFlowItems.add(paramFlowItem);
        rule.setParamFlowItemList(paramFlowItems);
        return rule;
    }
}
