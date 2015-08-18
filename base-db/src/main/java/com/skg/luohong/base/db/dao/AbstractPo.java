package com.skg.luohong.base.db.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * po实体类，所有的Po类继承该类
 * @author 骆宏 15013336884 846705189@qq.com
 * */
public abstract class AbstractPo<PK extends java.io.Serializable> implements PO<PK>, Serializable{
    
	private static final long serialVersionUID = 1151502015415415L;
	
	private PK pk;
	private String name;
	private String createBy;
	private Date createTime; 
	private String updateBy;
	private Date updateTime;
	
	public void setPk(PK pk){
		this.pk = pk;
	}
	public PK getPk(){
		return pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
