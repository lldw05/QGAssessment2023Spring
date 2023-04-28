package com.lldw.www.service;

import com.lldw.www.po.Message;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.User;

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
    /**
     *  立即购买 验证密码 以及 添加订单
     * @param orderForm 订单
     * @param user userId和payPassword
     * @return 是否添加成功
     */
    boolean purchaseNow(OrderForm orderForm,User user);

    /**
     *  通过userId查询订单
     * @param user userId
     * @return 查询到了则返回orderForm集合  否则返回null
     */
    ArrayList<OrderForm> queryOrderFormByUserId(User user);

    /**
     *  更新订单 状态 数量 等等
     * @param orderForm 订单id以及要修改的信息 不修改的设为null
     * @return  返回是否更新成功
     */
    boolean updateOrderForm(OrderForm orderForm);

    /**
     * 通过订单id查询订单
     * @param orderForm id
     * @return 查询到了 返回订单对象 否则返回null
     */
    OrderForm queryOrderFormById(OrderForm orderForm);
}
