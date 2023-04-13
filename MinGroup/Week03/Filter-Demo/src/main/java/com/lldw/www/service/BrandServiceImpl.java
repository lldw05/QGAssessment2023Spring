package com.lldw.www.service;

import com.lldw.www.dao.BrandDaoImpl;
import com.lldw.www.po.Brand;
import com.lldw.www.po.PageBean;

import java.util.List;

/**
 * @author lldw
 * @date 2023-04-13 11:22:24
 */
public class BrandServiceImpl implements BrandService{
    BrandDaoImpl brandDao = new BrandDaoImpl();
    @Override
    public List<Brand> selectAll() {
        return brandDao.selectAll();
    }

    @Override
    public void add(Brand brand) {
        brandDao.add(brand);
    }

    @Override
    public int deleteByIds(int[] ids) {
        return brandDao.deleteByIds(ids);
    }

    @Override
    public PageBean<Brand> selectByPage(int currentage, int pageSize) {

        //计算开始索引
        int begin =(currentage-1)*pageSize;

        //查询当前页记录数据
        List<Brand> rows = brandDao.selectByPage(begin, pageSize);

        //查询总记录数
        int totalCount = brandDao.getTotalCount();

        //封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }
}
