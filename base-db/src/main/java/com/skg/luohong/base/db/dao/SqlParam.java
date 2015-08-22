package com.skg.luohong.base.db.dao;


/**
 * sql语句的包装器，用与封装IQuery，IOrder，ILimit创建的sql，然后传递给dao层，用于查询
 * 
 * 该类的创建需要由其它类创建，目前框架中使用DynamicQueryBuilder来创建
 * 
 * @author 骆宏
 * @date 2015-08-22 19:12
 * */
public class SqlParam {
	private String whereSql;
	private String orderSql;
	private String limitSql;

	public SqlParam(){

	}

	public SqlParam(String whereSql, String orderSql, String limitSql){
		if(whereSql != null && !"".equals(whereSql)){
			this.whereSql = whereSql;
		}
		if(orderSql != null && !"".equals(orderSql)){
		    this.orderSql = orderSql;
		}
		if(limitSql != null && !"".equals(limitSql)){
			this.limitSql = limitSql;
		}
	}

	public String getWhereSql() {
		return whereSql;
	}
	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}
	public String getOrderSql() {
		return orderSql;
	}
	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}
	public String getLimitSql() {
		return limitSql;
	}
	public void setLimitSql(String limitSql) {
		this.limitSql = limitSql;
	}

	@Override
	public String toString(){
		return whereSql + " " + orderSql + " " + limitSql;
	}
}
