package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.OrderFormDaoImpl;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.User;
import com.lldw.www.service.OrderFormService;
import com.lldw.www.service.UserService;

import java.util.ArrayList;

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
        return cnt == orderForms.length;
    }

    @Override
    public boolean purchaseNow(OrderForm orderForm, User user) {
        UserService userService = new UserServiceImpl();

        //查询是否设置了支付密码
        if (userService.checkPayPasswordIsNull(user)) {
            //为空 还未设置支付密码
            return false;
        }

        //核对支付密码
        boolean flag = userService.checkPayPassword(user);
        if (flag) {
            //密码正确
            int i = orderFormDao.insertOrderForm(orderForm);
            return i > 0;
        }

        //密码错误
        return false;
    }


    @Override
    public ArrayList<OrderForm> queryOrderFormByUserId(User user) {
        return orderFormDao.getOrderFormByUserId(user);
    }

    @Override
    public boolean updateOrderForm(OrderForm orderForm) {
        return orderFormDao.updateOrderForm(orderForm) == 1;
    }

    @Override
    public OrderForm queryOrderFormById(OrderForm orderForm) {
        return orderFormDao.getOrderFormById(orderForm);
    }
}
