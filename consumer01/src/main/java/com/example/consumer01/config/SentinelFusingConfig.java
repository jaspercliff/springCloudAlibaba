package com.example.consumer01.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class SentinelFusingConfig implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDegradeRule();
    }

    private static void initDegradeRule() {
        ArrayList<DegradeRule> rules = new ArrayList<>();
        DegradeRule rtRule = getRtRule();
        DegradeRule exceptionRule = getExceptionRule();
        DegradeRule exceptionNumRule = getExceptionNumRule();
        rules.add(rtRule);
        rules.add(exceptionRule);
        rules.add(exceptionNumRule);
        DegradeRuleManager.loadRules(rules);
    }

    private static DegradeRule getRtRule(){
        DegradeRule rule = new DegradeRule();
        rule.setResource("GET:http://provide-service/provider/depart/");
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
//        最大rt
        rule.setCount(2);
//        比例阈值
        rule.setSlowRatioThreshold(0.2);
//        熔断时长
        rule.setTimeWindow(5);
        rule.setStatIntervalMs(1000);
        rule.setMinRequestAmount(2);
        return rule;
    }

    private static DegradeRule getExceptionRule(){
        DegradeRule rule = new DegradeRule();
        rule.setResource("GET:http://provide-service/provider/depart/");
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
//        比例阈值
        rule.setCount(0.2);
//        熔断时长
        rule.setTimeWindow(5);
        rule.setStatIntervalMs(1000);
        rule.setMinRequestAmount(2);
        return rule;
    }

    private static DegradeRule getExceptionNumRule(){
        DegradeRule rule = new DegradeRule();
        rule.setResource("GET:http://provide-service/provider/depart/");
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
//        比例阈值
        rule.setCount(30);
//        熔断时长
        rule.setTimeWindow(5);
        rule.setStatIntervalMs(1000);
        rule.setMinRequestAmount(2);
        return rule;
    }
}
