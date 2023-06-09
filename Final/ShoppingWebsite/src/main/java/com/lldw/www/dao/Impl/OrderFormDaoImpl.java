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
    public int insertOrderForm(OrderForm of) {
        if (of.getAmount()==null){
            return 0;
        }
        return ju.insert("insert into order_form (user_id,goods_id,amount,status) values (?,?,?,?)"
                , of.getUserId(), of.getGoodsId(), of.getAmount(), OrderFormConstants.STATUS_UNDELIVERED);
    }

    @Override
    public int insertOrderForms(OrderForm[] orderForm) {
        int cnt = 0;
        for (OrderForm of :
                orderForm) {
            if(of.getAmount()==null){
                continue;
            }
            if (ju.insert("insert into order_form (user_id,goods_id,amount,status) values (?,?,?,?)"
                    , of.getUserId(), of.getGoodsId(), of.getAmount(), OrderFormConstants.STATUS_UNDELIVERED) > 0) {
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
        System.out.println("--OrderFormDao.updateOrderForm---");

        //根据orderFormId 查询中修改前的orderForm数据
        OrderForm preOf = this.getOrderFormById(orderForm);
        if(preOf==null){
            return 0;
        }

        System.out.println("preOf:"+preOf);

        if(orderForm.getAmount()!=null){
            preOf.setAmount(orderForm.getAmount());
        }
//        if(orderForm.getUserId()!=null){
//            preOf.setUserId(orderForm.getUserId());
//        }
//        if(orderForm.getShopId()!=null){
//            preOf.setShopId(orderForm.getShopId());
//        }
//        if(orderForm.getGoodsId()!=null){
//            preOf.setGoodsId(orderForm.getGoodsId());
//        }
        if(orderForm.getStatus()!=null){
            preOf.setStatus(orderForm.getStatus());
        }

        System.out.println("afterOf:"+preOf);

        return ju.update("update order_form set amount = ?,status = ? where id = ?",preOf.getAmount(),preOf.getStatus(),preOf.getId());
    }

    @Override
    public ArrayList<OrderForm> getOrderFormByUserId(User user) {
        System.out.println("---OrderFormDao.getOrderFormByUserId---");
        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from order_form where user_id = ?", new Object[]{user.getUserId()});

        //判断集合是否为空 为空则直接返回null
        if (maps == null) {
            return null;
        }
        ArrayList<OrderForm> list = new ArrayList<>();
        for (Map<String, Object> m :
                maps) {
            list.add(getOrderFormFromMap(m));
        }
        return list;
    }

    @Override
    public OrderForm getOrderFormFromMap(Map<String, Object> map) {

        //从map中获取值
        OrderForm orderForm = new OrderForm();
        orderForm.setId((Integer) map.get("id"));
        orderForm.setUserId((Integer) map.get("user_id"));
        orderForm.setGoodsId((Integer) map.get("goods_id"));
        orderForm.setShopId((Integer) map.get("shop_id"));
        orderForm.setAmount((Integer) map.get("amount"));
        orderForm.setStatus((String) map.get("status"));

        return orderForm;
    }

    @Override
    public OrderForm getOrderFormById(OrderForm orderForm) {
        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from order_form where id = ?", new Object[]{orderForm.getId()});
        if(maps==null){
            return null;
        }
        OrderForm of = getOrderFormFromMap(maps.get(0));
        return of;
    }
}
