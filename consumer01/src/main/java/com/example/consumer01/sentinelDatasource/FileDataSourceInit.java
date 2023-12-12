package com.example.consumer01.sentinelDatasource;

import com.alibaba.csp.sentinel.cluster.server.command.handler.ModifyClusterParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDataSourceInit implements InitFunc {

    @Override
    public void init() throws Exception {
        File ruleFileDir = new File(System.getProperty("user.dir") + "/rules");
        if (!ruleFileDir.exists()) {
            ruleFileDir.mkdirs();
        }
//
//        readWriteRuleFlow(ruleFileDir.getPath());
//        readWriteRuleDegrade(ruleFileDir.getPath());
//        readWriteRuleAuthority(ruleFileDir.getPath());
//        readWriteRuleSystem(ruleFileDir.getPath());
//        readWriteRulePara(ruleFileDir.getPath());

    }
//flow
    private void readWriteRuleFlow(String ruleFilepath) throws IOException {

        String ruleFile = ruleFilepath + "/flow-rule.json";
        createRuleFile(ruleFile);

        ReadableDataSource<String, List<FlowRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 Writab leDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }
//Degrade
    private void readWriteRuleDegrade(String ruleFilepath) throws IOException {

        String ruleFile = ruleFilepath + "/degrade-rule.json";
        createRuleFile(ruleFile);

        ReadableDataSource<String, List<DegradeRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {})
        );
        DegradeRuleManager.register2Property(ds.getProperty());
        WritableDataSource<List<DegradeRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        WritableDataSourceRegistry.registerDegradeDataSource(wds);
    }
//Authority
    private void readWriteRuleAuthority(String ruleFilepath) throws IOException {

        String ruleFile = ruleFilepath + "/authority-rule.json";
        createRuleFile(ruleFile);

        ReadableDataSource<String, List<AuthorityRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {})
        );
        AuthorityRuleManager.register2Property(ds.getProperty());
        WritableDataSource<List<AuthorityRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        WritableDataSourceRegistry.registerAuthorityDataSource(wds);
    }
//system
    private void readWriteRuleSystem(String ruleFilepath) throws IOException {

        String ruleFile = ruleFilepath + "/system-rule.json";
        createRuleFile(ruleFile);

        ReadableDataSource<String, List<SystemRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {})
        );
        SystemRuleManager.register2Property(ds.getProperty());
        WritableDataSource<List<SystemRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        WritableDataSourceRegistry.registerSystemDataSource(wds);
    }
//param
    private void readWriteRulePara(String ruleFilepath) throws IOException {

        String ruleFile = ruleFilepath + "/para-rule.json";
        createRuleFile(ruleFile);

        ReadableDataSource<String, List<ParamFlowRule>> ds = new FileRefreshableDataSource<>(
                ruleFile, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {})
        );
        ParamFlowRuleManager.register2Property(ds.getProperty());
        WritableDataSource<List<ParamFlowRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        ModifyParamFlowRulesCommandHandler.setWritableDataSource(wds);
    }

    private void createRuleFile(String ruleFile) throws IOException {
        File file = new File(ruleFile);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }
}