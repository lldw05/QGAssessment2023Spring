package com.lldw.www.service;

import com.lldw.www.po.Message;
import com.lldw.www.po.OrderForm;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public interface OrderFormService {
     /**
      *  批量添加订单
      * @param orderForms 订单数组
      * @return 是否完全添加(就算添加了一部分 但没完全添加 也返回false)
      */
    boolean addOrderInBulk(OrderForm[] orderForms);
}
