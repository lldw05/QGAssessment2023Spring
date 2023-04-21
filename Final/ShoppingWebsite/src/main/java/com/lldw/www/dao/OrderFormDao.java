package com.lldw.www.dao;

import com.lldw.www.po.OrderForm;
import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-4-20 23:08:42
 */
public interface OrderFormDao {

    /**
     *  添加订单
     * @param orderForm orderForm对象
     * @return 添加成功则返回主键id 否则返回0
     */
    int insertOrderForm(OrderForm orderForm);

    /**
     *  批量添加订单
     * @param orderForm orderForm[]
     * @return 返回影响的行数
     */
    int insertOrderForms(OrderForm[] orderForm);

    /**
     *  删除订单
     * @param orderForm orderForm对象
     * @return 返回影响的行数
     */
    int deleteOrderForm(OrderForm orderForm);
    /**
     *  更新订单状态
     * @param orderForm orderForm对象
     * @return 返回影响的行数
     */
    int updateOrderForm(OrderForm orderForm);

    /**
     *  通过用户id查询订单
     * @param user UserId
     * @return 查询到了 返回集合 否则返回null
     */
    ArrayList<OrderForm> getOrderFormByUserId(User user);
    /**
     * 将放到map中的查询结果封装成message对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回orderForm对象
     */
    OrderForm getOrderFormFromMap(Map<String, Object> map);
}
