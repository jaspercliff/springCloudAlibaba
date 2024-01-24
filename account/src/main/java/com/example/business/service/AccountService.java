package com.example.business.service;

import com.example.business.Bean.Account;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface AccountService {

    @TwoPhaseBusinessAction(name = "debitAccount",commitMethod = "tccCommit",rollbackMethod = "tccRollback")
    Account debitAccount(
            @BusinessActionContextParameter("userId") Integer userId,
            @BusinessActionContextParameter("amount") double amount
    );

    Boolean tccCommit(BusinessActionContext context);

    Boolean tccRollback(BusinessActionContext context);

}
