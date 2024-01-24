package com.example.business.service;

import com.example.business.Bean.Stock;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface StockService {
    @TwoPhaseBusinessAction(name = "deductStock",commitMethod = "tccCommit",rollbackMethod = "tccRollback")
    Stock deduct(@BusinessActionContextParameter("goodsId") Integer goodsId,
                 @BusinessActionContextParameter("count") Integer count);

    Boolean tccCommit(BusinessActionContext context);

    Boolean tccRollback(BusinessActionContext context);
}
