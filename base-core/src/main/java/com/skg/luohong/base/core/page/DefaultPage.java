package com.skg.luohong.base.core.page;


/**
 * 翻页的默认实现对象
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-18 17:22
 * */
public class DefaultPage implements IPage{

	private int limit = DEFAULT_LIMIT;
	private int totalRecords; 
	private int pageNumber = 1;  //default page is 1
	
	public DefaultPage(){
		
	}
	
	public DefaultPage(int totalRecords){
		setTotalRecords(totalRecords);
	}
	
	public DefaultPage(int totalRecords, int limit, int pageNumber){
		setTotalRecords(totalRecords);
		setLimit(limit);
		setPageNumber(pageNumber);
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	public int getTotalPage() {
	    if(totalRecords % limit == 0){
	    	return totalRecords / limit;
	    }else{
	    	return totalRecords / limit + 1;
	    }
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	@Override
	public int getOffset(){
		return (pageNumber - 1) * limit;
	}

	public void setPageNumber(int pageNumber) {
		if(pageNumber < 0){
			throw new IllegalArgumentException("pageNumber must be > 0");
		}
		this.pageNumber = pageNumber;
	}

	public void setLimit(int limit) {
		if(limit < 0){
			throw new IllegalArgumentException("limit must be > 0");
		}
		
		this.limit = limit;
	}
	
	@Override
	public int getLimit() {
		return limit;
	}

	public void setTotalRecords(int totalRecords) {
		if(totalRecords < 0){
			throw new IllegalArgumentException("totalRecords must be > 0");
		}
		
		this.totalRecords = totalRecords;
	}
	
	@Override
	public String toString(){
		return "totalRecords:" + totalRecords + ", totalPage:" + getTotalPage() + ",offset:" + getOffset() + ",limit:" + limit + ",pageNumber:" + getPageNumber();
	}
	
	public static void main(String[] args) {
		IPage page = new DefaultPage(100);
		System.out.println(page);
	}
}
