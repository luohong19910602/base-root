package com.skg.luohong.base.db.dao;

/**
 * 排序接口
 * 
 * @author 骆宏
 * @date 2015-08-20 16:14
 * */
public interface IOrder {
	
    String ASC = "asc";
    String DESC = "desc";
    
	String getOrderBySql();
    
    /**
     * 添加排序字段
     * @param key
     * @param sort 降序或者升序，sort的值为desc,asc中的一种，如果不符合，抛出异常IllegalArgumentException
     * */
    void addOrder(String key, String sort);
}
