package com.example.consumer01.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class SentinelAuthorityConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAuthorityRule();
    }

    private static void initAuthorityRule() {
        ArrayList<AuthorityRule> rules = new ArrayList<>();
        AuthorityRule rtRule = getAuthorityRule();
        rules.add(rtRule);
        AuthorityRuleManager.loadRules(rules);
    }
    private static AuthorityRule getAuthorityRule(){
        AuthorityRule rule = new AuthorityRule();
        rule.setResource("getHandle");
        rule.setStrategy(RuleConstant.AUTHORITY_BLACK);
        rule.setLimitApp("sb,sc");
        return rule;
    }
}
