package com.skg.luohong.base.db.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有的po继承该类，目的是提供一些通用属性
 * */
public abstract class AbstractPo<PK extends Serializable> implements IPO<PK>  {
    private PK id;
    
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    
    @Override
    public PK getId() {
		return id;
	}
	public void setId(PK id) {
		this.id = id;
	}
	@Override
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Override
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	@Override
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
