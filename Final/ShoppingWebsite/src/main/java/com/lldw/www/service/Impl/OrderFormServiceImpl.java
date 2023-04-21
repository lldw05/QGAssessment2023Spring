package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.OrderFormDaoImpl;
import com.lldw.www.po.OrderForm;
import com.lldw.www.service.OrderFormService;

/**
 * @author lldw
 * @date 2023-4-21 13:43:40
 */
public class OrderFormServiceImpl implements OrderFormService {

    OrderFormDaoImpl orderFormDao = new OrderFormDaoImpl();
    @Override
    public boolean addOrderInBulk(OrderForm[] orderForms) {

        //调用dao完成add订单 返回影响的行数
        int cnt = orderFormDao.insertOrderForms(orderForms);

        //判断影响的行数是否等于数组的长度
        return cnt==orderForms.length;
    }
}
