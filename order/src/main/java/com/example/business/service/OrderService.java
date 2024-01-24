package com.example.business.service;

import com.example.business.Bean.Orders;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface OrderService {

    @TwoPhaseBusinessAction(name = "createOrder",commitMethod = "tccCommit",rollbackMethod = "tccRollback")
    Orders createOrder(@BusinessActionContextParameter("orders") Orders orders);

    Boolean tccCommit(BusinessActionContext context);

    Boolean tccRollback(BusinessActionContext context);
}
