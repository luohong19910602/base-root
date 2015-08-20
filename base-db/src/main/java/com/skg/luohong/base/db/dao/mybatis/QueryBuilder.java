package com.skg.luohong.base.db.dao.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.skg.luohong.base.db.dao.ICondition;
import com.skg.luohong.base.db.dao.ILimit;
import com.skg.luohong.base.db.dao.IOrder;
import com.skg.luohong.base.db.dao.IQuery;

/**
 * mybatis的查询构建器，实现IQuery，IOrder，ILimit
 * 在mybatis中，将sql拆分为三个部分，一个是where部分，一个是order by部分，另外一个是limit部分
 * 比如：select * from user where name='luohong' order by create_time limit 0,10
 * */
public class QueryBuilder implements IQuery, IOrder, ILimit {
    
	List<ICondition> conditionList = new ArrayList<ICondition>();

	public QueryBuilder(){
		
	}
	
	@Override
	public String getOrderBySql() {
		return null;
	}

	@Override
	public String getWhereSql() {
		if(conditionList.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" where ");
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
		return null;
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
	
	public static void main(String[] args) {
		QueryBuilder builder = new QueryBuilder();
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
		
		System.out.println(builder.getWhereSql());
	}
}
