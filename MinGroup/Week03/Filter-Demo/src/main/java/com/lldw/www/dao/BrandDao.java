package com.lldw.www.dao;

import com.lldw.www.po.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date
 */
public interface BrandDao {

    /**
     * 查询所有
     * @return 返回装有brand的list集合
     */
    List<Brand> selectAll();

    /**
     * 添加brand对象
     * @param brand 传入brand对象
     */
    void add(Brand brand);

    /**
     * 批量删除
     * @param ids
     * @return 返回影响的行
     */
    int deleteByIds(int[] ids);

    /**
     *  分页查询
     * @param begin 开始索引
     * @param size 每次查询的条数
     * @return 返回Brand集合
     */
    List<Brand> selectByPage(int begin,int size);

    /**
     * 查询总记录数
     * @return
     */
    int getTotalCount();


    /**
     * 将查询到的List<Map>转换为List<Brand>
     * @param listMap
     * @return
     */
    List<Brand> mapToList(ArrayList<Map<String,Object>> listMap);
}
