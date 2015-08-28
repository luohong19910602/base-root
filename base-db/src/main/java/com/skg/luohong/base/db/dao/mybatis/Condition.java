package com.skg.luohong.base.db.dao.mybatis;

import java.util.Date;

import com.skg.luohong.base.db.dao.ICondition;

/**
 * condition的默认实现
 * @author 骆宏
 * @date 2015-08-20 16:26
 * */
public class Condition implements ICondition {
	private String name;
	private String type;
	private String op;
	private Object value;

	public Condition(String name, String op, Object value){
        this(name, op, value, ICondition.DEFAULT_TYPE);
	}

	public Condition(String name, String op, Object value, String type) {
		if(value == null){
			throw new IllegalArgumentException("value can't be null");
		}
		if(name == null){
			throw new IllegalArgumentException("name can't be null");
		}

		//默认为字符串类型
		if(type == null || type.trim().length() == 0){
			type = "string";
		}
        
		if(type.equals(ICondition.DATE_TYPE)){
			this.type = ICondition.DATE_TYPE;
		}else if(type.equals(ICondition.NUMBER_TYPE)){
			this.type = ICondition.NUMBER_TYPE;
		}else{
			this.type = ICondition.DEFAULT_TYPE;
		}
        
		checkOp(op, type);
        
		//type为date类型，op为between类型的值做判断，查看值是否为该格式： '[startTime,endTime]'
		
		
		if(type.equals(ICondition.DATE_TYPE) && op.equals(ICondition.DateOpType.BW)){
			String temp = value.toString();
			if(temp.split(",").length != 2){
				throw new IllegalArgumentException("Please check value argument, it must be '[startTime, endTime]' patter");
			}
		}
		this.op = op; 
		this.name = name;
		this.value = value;
	}


	/**
	 * 检测op的值是否有错
	 * 如果不符合type类型的操作，抛出异常
	 * */
	private void checkOp(String op, String type) {
		if(type.equals(ICondition.NUMBER_TYPE)){
			if(!ICondition.NumberOpType.in(op)){
				throw new IllegalArgumentException("Please check op argument, It must be in [eq,gt,lt,nq]");
			}
		}else if(type.equals(ICondition.DEFAULT_TYPE)){
			if(!ICondition.StringOpType.in(op)){
				throw new IllegalArgumentException("Please check op argument, It must be in [eq,like,nq]");
			}
		}else if(type.equals(ICondition.DATE_TYPE)){
			if(!ICondition.DateOpType.in(op)){
				throw new IllegalArgumentException("Please check op argument, It must be in [eq,gt,lt,bw]");
			}
		}else{
			throw new IllegalArgumentException("Please check type argument, It must be in [string,date,number]");
		}
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String op() {
		return op;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public Object value() {
		return value;
	}

	@Override
	public String toString(){
		return name + " " + op + " " + value + ",type:" + type;
	}

	@Override
	public String getSql() {
		if(type.equals(ICondition.DEFAULT_TYPE)){
			if(op.equals(ICondition.StringOpType.LIKE)){
				return name + " like '%" + value + "%'"; 
			}else if(op.equals(ICondition.StringOpType.EQ)){
				return name + " = '" + value + "'";
			}else if(op.equals(ICondition.StringOpType.NQ)){
				return name + " != '" + value + "'";
			}
		}else if(type.equals(ICondition.NUMBER_TYPE)){
			if(op.equals(ICondition.NumberOpType.EQ)){
				return name + " = " + value;
			}else if(op.equals(ICondition.NumberOpType.GT)){
				return name + " >= " + value;
			}else if(op.equals(ICondition.NumberOpType.LT)){
				return name + " <= " + value;
			}else if(op.equals(ICondition.NumberOpType.NQ)){
				return name + " != " + value;
			}
		}else if(type.equals(ICondition.DATE_TYPE)){
			if(op.equals(ICondition.DateOpType.EQ)){
				return name + " = '" + value + "'";
			}else if(op.equals(ICondition.DateOpType.GT)){
				return name + " >= '" + value + "'";
			}else if(op.equals(ICondition.DateOpType.LT)){
				return name + " <= '" + value + "'";
			}else if(op.equals(ICondition.DateOpType.BW)){
				String temp = ((String)value).replace("[", "");
				temp = temp.replace("]", "");
				String start = temp.split(",")[0].trim();
				String end = temp.split(",")[1].trim();
				return name + " between '" + start + "' and '" + end + "'";
			}
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		Condition eq = new Condition("name", "eq", "骆宏");
		System.out.println(eq);
		System.out.println(eq.getSql());
		
		Condition nq = new Condition("name", "nq", "骆宏");
		System.out.println(nq);
		System.out.println(nq.getSql());
		
		Condition con = new Condition("name", "like", "骆宏");
		System.out.println(con);
		System.out.println(con.getSql());
		
		
		Condition num = new Condition("sort", "gt", "10", ICondition.NUMBER_TYPE);
		System.out.println(num);
		System.out.println(num.getSql());
		
		Condition eqnum = new Condition("sort", "eq", "10", ICondition.NUMBER_TYPE);
		System.out.println(eqnum);
		System.out.println(eqnum.getSql());
		
		Condition nqnum = new Condition("sort", "nq", "10", ICondition.NUMBER_TYPE);
		System.out.println(nqnum);
		System.out.println(nqnum.getSql());
		
		Condition ltnum = new Condition("sort", "lt", "10", ICondition.NUMBER_TYPE);
		System.out.println(ltnum);
		System.out.println(ltnum.getSql());
		
		
		Condition date = new Condition("createTime", "gt", new Date(), "date");
		System.out.println(date);
		System.out.println(date.getSql());
		
		Condition ltdate = new Condition("createTime", "lt", new Date(), "date");
		System.out.println(ltdate);
		System.out.println(ltdate.getSql());
		
		
		Condition eqdate = new Condition("createTime", "eq", new Date(), "date");
		System.out.println(eqdate);
		System.out.println(eqdate.getSql());
		
		
		Condition bw = new Condition("startTime", "bw", "[2015-07-23,2016-06-26]", ICondition.DATE_TYPE);
		System.out.println(bw.getSql());
		System.out.println(bw); 
	}
}
