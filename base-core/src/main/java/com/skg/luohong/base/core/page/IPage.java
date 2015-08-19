package com.skg.luohong.base.core.page;

/**
 * 翻页接口对象
 * 
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-18 17:06
 * */
public interface IPage {
	/**
	 * 默认每页显示的记录数
	 * */
	int DEFAULT_LIMIT = 20;
	
	/**
	 * 每页最多显示的记录数
	 * @return 每页最多显示记录数
	 * */
	public int getLimit();
	
	/**
	 * 当前的页码
	 * @return 当前页码
	 * */
    public int getPageNumber();
    
    /**
     * 总页数
     * @return 总页数
     * */
    public int getTotalPage();
    
    /**
     * 总记录数
     * @return 总记录数
     * */
    public int getTotalRecords();
    
    /**
     * 从第几条记录开始查询
     * */
    public int getStart();
    
    /**
     * 设置当前的页码数
     * @param pageNumber
     * */
    public void setPageNumber(int pageNumber);
    
    /**
     * 设置每页最多显示的记录数
     * @param limit
     * */
    public void setLimit(int limit);
    
    /**
     * 设置总记录数
     * @param totalRecords
     * */
    public void setTotalRecords(int totalRecords);
}
