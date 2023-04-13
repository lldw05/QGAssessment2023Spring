package com.lldw.www.dao;

import com.lldw.www.po.Brand;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 */
public class BrandDaoImpl implements BrandDao{

    JdbcUtils ju = JdbcUtils.getInstance();

    /**
     * 查询所有
     * @return 返回装有brand的list集合
     */
    @Override
    public List<Brand> selectAll() {
        ArrayList<Map<String,Object>> listMap = ju.execQueryList("select * from tb_brand", null);

        ArrayList<Brand> brandArrayList= mapToList(listMap);

        return brandArrayList;
    }

    @Override
    public void add(Brand brand) {
        ju.update("insert into tb_brand value(?,?,?,?,?,?)",
                null,brand.getBrandName(),brand.getCompanyName(),brand.getOrdered(),brand.getDescription(),brand.getStatus());
    }

    @Override
    public int deleteByIds(int[] ids) {

        //手动写sql语句
        StringBuilder sql = new StringBuilder("(");
        for (int i = 0; i < ids.length; i++) {
            sql.append(ids[i]);
            if(i!= ids.length-1) {
                sql.append(",");
            }
        }
        sql.append(")");
        return ju.update("delete from tb_brand where id in " + sql,null);

    }

    @Override
    public List<Brand> selectByPage(int begin, int size) {
        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from tb_brand limit ?,?", new Object[]{begin, size});
        ArrayList<Brand> brandArrayList = mapToList(maps);
        return brandArrayList;
    }

    @Override
    public int getTotalCount() {
        return ju.getTotalCnt("select count(*) from tb_brand");
    }

    @Override
    public ArrayList<Brand> mapToList(ArrayList<Map<String, Object>> listMap) {

        ArrayList<Brand> brandArrayList= new ArrayList<>();

        for (Map<String, Object> map : listMap) {
            Brand brand = new Brand();
            brand.setId((Integer) map.get("id"));
            brand.setBrandName((String) map.get("brand_name"));
            brand.setCompanyName((String) map.get("company_name"));
            brand.setOrdered((Integer) map.get("ordered"));
            brand.setDescription((String) map.get("description"));
            brand.setStatus((Integer) map.get("status"));
            brandArrayList.add(brand);
        }


        return brandArrayList;
    }
}
