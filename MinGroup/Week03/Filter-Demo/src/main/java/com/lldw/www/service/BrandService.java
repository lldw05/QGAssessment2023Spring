package com.lldw.www.service;

import com.lldw.www.po.Brand;
import com.lldw.www.po.PageBean;

import java.util.List;

/**
 * @author
 * @date
 */
public interface BrandService {
    /**
     *  查询所有
     * @return 返回装有brand对象的list集合
     */
    public List<Brand> selectAll();

    /**
     * 添加brand
     * @param brand 传入brand对象
     */
    public void add(Brand brand);

    /**
     * 批量删除
     * @param ids 存有id的数组
     * @return 返回删除的行数
     */
    public int deleteByIds(int ids[]);

    /**
     * 分页查询
     * @param currentage 当前页码
     * @param pageSize 每页条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentage,int pageSize);
}
