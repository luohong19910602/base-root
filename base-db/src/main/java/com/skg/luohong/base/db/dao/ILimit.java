package com.skg.luohong.base.db.dao;

import com.skg.luohong.base.core.page.IPage;

/**
 * 翻页查询接口
 * 在这里面，如果调用了setPage，那么会忽重写掉start,limit方法设置的值，所以这两个方法，调用其中一种即可
 * 
 * @author 骆宏 
 * @date 2015-08-20 16:17
 * */
public interface ILimit {
    
	/**
	 * 获取查询limit sql
	 * */
	String getLimitSql();
    

    /**
     * 设置查询开始下标
     * @param start
     * */
    void setOffset(int offset);
    
    /**
     * 获取查询下表
     * */
    int getOffset();
    
    /**
     * 获取查询记录数
     * */
    int getLimit();
    
    /**
     * 设置查询记录数
     * @param limit
     * */
    void setLimit(int limit);
    
    /**
     * 设置翻页对象
     * 
     * */
    void setPage(IPage page);
}
