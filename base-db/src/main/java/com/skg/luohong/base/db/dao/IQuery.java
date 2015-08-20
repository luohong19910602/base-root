package com.skg.luohong.base.db.dao;

import java.util.List;

/**
 * 查询接口
 * 
 * @author 骆宏
 * @date 2015-08-20 16:10
 * */
public interface IQuery {
	
	/**
	 * 获取查询sql
	 * 这里面不包含排序 order by部分
	 * @return
	 * */
    String getWhereSql();
    
    /**
     * 添加查询条件
     * @param condition
     * */
    void addCondition(ICondition condition);
    
    /**
     * 添加多个查询条件
     * @param conditions
     * */
    void addConditions(List<ICondition> conditions);
}
