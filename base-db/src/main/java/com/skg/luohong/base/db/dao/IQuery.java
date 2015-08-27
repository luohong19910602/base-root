package com.skg.luohong.base.db.dao;

import java.util.List;

/**
 * 查询接口
 * 
 * @author 骆宏
 * @date 2015-08-20 16:10
 * */
public interface IQuery {
	
	public static final String AND = "and";
	public static final String OR = "or";
	
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
    
    /**
     * 多个条件之间的关系，默认为and
     * */
    String getType();
    
    void setType(String type);
}
