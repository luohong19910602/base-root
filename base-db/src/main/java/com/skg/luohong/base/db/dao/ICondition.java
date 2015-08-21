package com.skg.luohong.base.db.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件的封装
 * 
 * 在这里面，如果涉及到多个表的关联查询，那么需要在key前面加入表名，比如查询订单与客户：
 * 
 * select * from order,customer where order.customer_id = '1234'
 * 这里面，key变成了order.customer_id
 * 
 * @author 骆宏
 * @date 2015-08-20 16:21
 * */
public interface ICondition {
	/**
	 * 数据类型，默认为字符串
	 * number为数字类型，比如int，long，float等
	 * date为日期类型，比如date，datestamp
	 * */
	public static final String DEFAULT_TYPE = "string";
	public static final String NUMBER_TYPE = "number";
	public static final String DATE_TYPE = "date";

	/**
	 * 字符串比较类型
	 * */
	public static class StringOpType{
		/**
		 * ==
		 * */
		public static final String EQ = "eq";

		/**
		 * like
		 * */
		public static final String LIKE = "like";
		
		/**
		 * !=
		 * */
		public static final String NQ = "nq";
		
		public static List<String> types = new ArrayList<String>();
		
		public static List<String> getOpTypes(){
			if(types.size() == 0){
				types.add(EQ);
				types.add(LIKE);
				types.add(NQ);
				return types;
			}else{
				return types;
			}
		}
		
		public static boolean in(String type){
			return getOpTypes().contains(type);
		}
		
	}

	/**
	 * 日期比较类型
	 * */
	public static class DateOpType{
		public static List<String> types = new ArrayList<String>();
		/**
		 * ==
		 * */
		public static final String EQ = "eq";
		/**
		 * >=
		 * */
		public static final String GT = "gt"; 
		/**
		 * <=
		 * */
		public static final String LT = "lt";
		
		public static List<String> getOpTypes(){
			if(types.size() == 0){
				types.add(EQ);
				types.add(GT);
				types.add(LT);
				return types;
			}else{
				return types;
			}
		}
		
		public static boolean in(String type){
			return getOpTypes().contains(type);
		}
	}

	/**
	 * 数字的比较类型
	 * */
	public static class NumberOpType{
		public static List<String> types = new ArrayList<String>();

		/**
		 * ==
		 * */
		public static final String EQ = "eq";
		/**
		 * >=
		 * */
		public static final String GT = "gt"; 
		/**
		 * <=
		 * */
		public static final String LT = "lt";
		/**
		 * !=
		 * */
		public static final String NQ = "nq";

		public static List<String> getOpTypes(){
			if(types.size() == 0){
				types.add(EQ);
				types.add(GT);
				types.add(LT);
				types.add(NQ);
				return types;
			}else{
				return types;
			}
		}
		
		/**
		 * 判断某个op
		 * */
		public static boolean in(String type){
			return getOpTypes().contains(type);
		}
	}

	/**
	 * 查询条件名称
	 * */
	String key(); 
	/**
	 * 类型
	 * */
	String type();

	/**
	 * 查询条件的关系，比如like，>，==，!=
	 * */
	String op();
	/**
	 * 值
	 * */
	Object value();
	
	/**
	 * 获取该条件对应的sql
	 * 比如: 
	 * name like %骆宏%
	 * sort=10
	 * */
	String getSql();
}
