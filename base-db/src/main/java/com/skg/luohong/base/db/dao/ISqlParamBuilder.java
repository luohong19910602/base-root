package com.skg.luohong.base.db.dao;


/**
 * 创造whereSql, orderSql, limitSql
 * @author 骆宏
 * @date 2015-08-22 19:17
 * */
public interface ISqlParamBuilder extends IQuery, IOrder, ILimit {
    SqlParam buildSqlParam();
}
