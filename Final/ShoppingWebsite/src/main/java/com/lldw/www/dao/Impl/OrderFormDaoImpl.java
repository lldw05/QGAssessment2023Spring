package com.lldw.www.dao.Impl;

import com.lldw.www.constants.OrderFormConstants;
import com.lldw.www.dao.OrderFormDao;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-4-20 23:08:34
 */
public class OrderFormDaoImpl implements OrderFormDao {
    JdbcUtils ju = JdbcUtils.getInstance();
    @Override
    public int insertOrderForm(OrderForm orderForm) {
        return 0;
    }

    @Override
    public int insertOrderForms(OrderForm[] orderForm) {
        int cnt =0;
        for (OrderForm of :
                orderForm) {
            if(ju.insert("insert into order_form (user_id,goods_id,amount,status) values (?,?,?,?)"
                    ,of.getUserId(),of.getGoodsId(),of.getAmount(),OrderFormConstants.STATUS_UNDELIVERED)>0){
                cnt++;
            }
        }

        return cnt;
    }

    @Override
    public int deleteOrderForm(OrderForm orderForm) {
        return 0;
    }

    @Override
    public int updateOrderForm(OrderForm orderForm) {
        return 0;
    }

    @Override
    public ArrayList<OrderForm> getOrderFormByUserId(User user) {
        return null;
    }

    @Override
    public OrderForm getOrderFormFromMap(Map<String, Object> map) {
        return null;
    }
}
