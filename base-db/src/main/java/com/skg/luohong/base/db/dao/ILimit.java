package com.skg.luohong.base.db.dao;

import com.skg.luohong.base.core.page.IPage;

/**
 * 翻页查询接口
 * 在这里面，如果调用了setPage，那么会忽重写掉start,limit方法设置的值，所以这两个方法，调用其中一种即可
 * @author 骆宏 
 * @date 2015-08-20 16:17
 * */
public interface ILimit {
    String getLimitSql();
    
    int getStart();
    
    int getLimit();
    
    /**
     * 查询开始下标
     * @param start
     * */
    void setStart(int start);
    
    /**
     * 查询记录数
     * @param limit
     * */
    void setLimit(int limit);
    
    /**
     * 设置翻页对象
     * 
     * */
    void setPage(IPage page);
}
