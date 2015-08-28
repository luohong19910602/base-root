package com.skg.luohong.base.db.dao.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.ICondition;
import com.skg.luohong.base.db.dao.IOrder;
import com.skg.luohong.base.db.dao.IQuery;
import com.skg.luohong.base.db.dao.ISqlParamBuilder;
import com.skg.luohong.base.db.dao.SqlParam;

/**
 * mybatis的查询构建器，实现IQuery，IOrder，ILimit
 * 在mybatis中，将sql拆分为三个部分，一个是where部分，一个是order by部分，另外一个是limit部分
 * 比如：select * from user where name='luohong' order by create_time limit 0,10
 * 
 * 在Mybatis中，将会使用该类来创建where sql, order by sql, limit sql部分的参数内容
 * */
public class DefaultSqlParamBuilder implements ISqlParamBuilder {

	private List<ICondition> conditionList = new ArrayList<ICondition>();
	private Map<String, String> orders = new HashMap<String, String>();

	private int offset;
	private int limit;
	
	private String type = IQuery.AND;

	public DefaultSqlParamBuilder(){

	}

	@Override
	public String getOrderBySql() {
		if(orders.size() == 0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(" order by ");
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
				sb.append(conditionList.get(i).getSql() + " " + getType() + " ");
			}
		}

		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	public String getLimitSql() {
		if(limit != 0){			
			return " limit " + offset + ", " + limit;
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
	public void addOrder(String key, String sort) {
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
	public void setLimit(int limit) {
		if(limit < 1){
			throw new IllegalArgumentException("int limit must be >= 1");
		}

		this.limit = limit;
	}

	@Override
	public void setPage(IPage page) {
		this.limit = page.getLimit();
		this.offset = page.getOffset();
	}


	public void setOffset(int offset){
        if(offset < 0){
        	throw new IllegalArgumentException("Please check offset argument, it must be bigger than 0");
        }
		this.offset = offset;
	}

	@Override
	public int getOffset(){
		return offset;
	}

	@Override
	public int getLimit() {
		return limit;
	}


	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		if(type.equals(IQuery.AND)){
			this.type = type;
		}else if(type.equals(IQuery.OR)){
			this.type = type;
		}else{
			throw new IllegalArgumentException("Please check type argument, it must be in [and, or]");
		}
	}
	
	@Override
	public SqlParam buildSqlParam() {
		return new SqlParam(getWhereSql(), getOrderBySql(), getLimitSql());
	}

	public static void main(String[] args) {
		DefaultSqlParamBuilder builder = new DefaultSqlParamBuilder();
		Condition eq = new Condition("name", "eq", "骆宏");
		System.out.println(eq.getSql());
		builder.addCondition(eq);

		Condition nq = new Condition("name", "nq", "骆宏");
		
		System.out.println(nq.getSql());	
		builder.addCondition(nq);

		Condition con = new Condition("name", "like", "骆宏");
		System.out.println(con.getSql());
		builder.addCondition(con);

		Condition num = new Condition("sort", "gt", "10", ICondition.NUMBER_TYPE);
		System.out.println(num.getSql());
		builder.addCondition(num);

		Condition eqnum = new Condition("sort", "eq", "10", ICondition.NUMBER_TYPE);
		System.out.println(eqnum.getSql());

		builder.addCondition(eqnum);
		Condition nqnum = new Condition("sort", "nq", "10", ICondition.NUMBER_TYPE);
		System.out.println(nqnum.getSql());

		Condition ltnum = new Condition("sort", "lt", "10", ICondition.NUMBER_TYPE);
		System.out.println(ltnum.getSql());

		builder.addCondition(ltnum);

		Condition date = new Condition("createTime", "gt", new Date(), "date");
		System.out.println(date.getSql());
		builder.addCondition(date);
		Condition ltdate = new Condition("createTime", "lt", new Date(), "date");
		System.out.println(ltdate.getSql());
		builder.addCondition(ltdate);

		Condition eqdate = new Condition("createTime", "eq", new Date(), "date");
		System.out.println(eqdate.getSql());
		builder.addCondition(eqdate);

		builder.addOrder("name", "desc");
		builder.addOrder("age", "DESC");
		builder.addOrder("tel", "Desc");
		builder.addOrder("mobile", "Asc");
		builder.addOrder("email", "asc");
		builder.addOrder("id", "ASC");
		builder.setLimit(10);
		
		Condition bw = new Condition("startTime", "bw", "[2015-07-23,2016-06-26]", ICondition.DATE_TYPE);
		builder.addCondition(bw);
		builder.setType("or");

		System.out.println(builder.getWhereSql() + builder.getOrderBySql() + builder.getLimitSql());
		System.out.println(builder.buildSqlParam());
	}
}
