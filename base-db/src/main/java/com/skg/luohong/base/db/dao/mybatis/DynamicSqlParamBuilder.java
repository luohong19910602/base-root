package com.skg.luohong.base.db.dao.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.ICondition;
import com.skg.luohong.base.db.dao.IOrder;
import com.skg.luohong.base.db.dao.ISqlParamBuilder;
import com.skg.luohong.base.db.dao.SqlParam;

/**
 * mybatis的查询构建器，实现IQuery，IOrder，ILimit
 * 在mybatis中，将sql拆分为三个部分，一个是where部分，一个是order by部分，另外一个是limit部分
 * 比如：select * from user where name='luohong' order by create_time limit 0,10
 * 
 * 在Mybatis中，将会使用该类来创建where sql, order by sql, limit sql部分的参数内容
 * */
public class DynamicSqlParamBuilder implements ISqlParamBuilder {
    
	private List<ICondition> conditionList = new ArrayList<ICondition>();
    private Map<String, String> orders = new HashMap<String, String>();
	
    private int start;
	private int limit;
	
	public DynamicSqlParamBuilder(){
		
	}
	
	@Override
	public String getOrderBySql() {
		if(orders.size() == 0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for(String key: orders.keySet()){
			sb.append(key + " " + orders.get(key) + ",");
		}
		
		return sb.substring(0, sb.length() - 1);
	}

	@Override
	public String getWhereSql() {
		if(conditionList.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for(int i=0; i<conditionList.size(); i++){
			if(i == conditionList.size() - 1){				
				sb.append(conditionList.get(i).getSql());
			}else{
				sb.append(conditionList.get(i).getSql() + " and ");
			}
		}
		
		return sb.toString();
	}

	@Override
	public String getLimitSql() {
		if(limit != 0){			
			return " limit " + start + ", " + limit;
		}else{
			return "";
		}
	}

	@Override
	public void addCondition(ICondition condition) {
		if(!conditionList.contains(condition)){
			conditionList.add(condition);
		}
	}

	@Override
	public void addConditions(List<ICondition> conditions) {
		if(conditions != null){
			for(ICondition con: conditions){
				addCondition(con);
			}
		}
	}
	
	@Override
	public void add(String key, String sort) {
		if(key == null) throw new IllegalArgumentException("Key can't be null");
		
		if(sort.equalsIgnoreCase(IOrder.DESC)){
			orders.put(key, IOrder.DESC);
		}else if(sort.equalsIgnoreCase(IOrder.ASC)){
			orders.put(key, IOrder.ASC);
		}else{
			throw new IllegalArgumentException("Please check sort argument, it must be in [asc, desc]");
		}
	}
	

	@Override
	public void setStart(int start) {
		if(start < 0){
			throw new IllegalArgumentException("start must be >= 0");
		}
		
		this.start = start;
	}

	@Override
	public void setLimit(int limit) {
		if(limit < 1){
			throw new IllegalArgumentException("int limit must be >= 1");
		}
		
		this.limit = limit;
	}
	
	@Override
	public RowBounds buildRowBounds() {
		return new RowBounds(start, limit);
	}
	
	@Override
	public void setPage(IPage page) {
		this.limit = page.getLimit();
		this.start = page.getStart();
	}
	

	@Override
	public int getStart() {
		return start;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	@Override
	public SqlParam buildSqlParam() {
		return new SqlParam(getWhereSql(), getOrderBySql());
	}
	
	public static void main(String[] args) {
		DynamicSqlParamBuilder builder = new DynamicSqlParamBuilder();
		Condition eq = new Condition("name", "eq", "骆宏");
		System.out.println(eq);
		System.out.println(eq.getSql());
		builder.addCondition(eq);
		
		Condition nq = new Condition("name", "nq", "骆宏");
		System.out.println(nq);
		System.out.println(nq.getSql());	
		builder.addCondition(nq);
		
		Condition con = new Condition("name", "like", "骆宏");
		System.out.println(con);
		System.out.println(con.getSql());
		builder.addCondition(con);
		
		Condition num = new Condition("sort", "gt", "10", ICondition.NUMBER_TYPE);
		System.out.println(num);
		System.out.println(num.getSql());
		builder.addCondition(num);
		
		Condition eqnum = new Condition("sort", "eq", "10", ICondition.NUMBER_TYPE);
		System.out.println(eqnum);
		System.out.println(eqnum.getSql());
		
		builder.addCondition(eqnum);
		Condition nqnum = new Condition("sort", "nq", "10", ICondition.NUMBER_TYPE);
		System.out.println(nqnum);
		System.out.println(nqnum.getSql());
		
		Condition ltnum = new Condition("sort", "lt", "10", ICondition.NUMBER_TYPE);
		System.out.println(ltnum);
		System.out.println(ltnum.getSql());
		
		builder.addCondition(ltnum);
		
		Condition date = new Condition("createTime", "gt", new Date(), "date");
		System.out.println(date);
		System.out.println(date.getSql());
		builder.addCondition(date);
		Condition ltdate = new Condition("createTime", "lt", new Date(), "date");
		System.out.println(ltdate);
		System.out.println(ltdate.getSql());
		builder.addCondition(ltdate);
		
		Condition eqdate = new Condition("createTime", "eq", new Date(), "date");
		System.out.println(eqdate);
		System.out.println(eqdate.getSql());
		builder.addCondition(eqdate);
		
		builder.add("name", "desc");
		builder.add("age", "DESC");
		builder.add("tel", "Desc");
		builder.add("mobile", "Asc");
		builder.add("email", "asc");
		builder.add("id", "ASC");
		builder.setLimit(10);
		builder.setStart(0);
		
		System.out.println(builder.getWhereSql() + builder.getOrderBySql() + builder.getLimitSql());
		System.out.println(builder.buildSqlParam());
	}
}
