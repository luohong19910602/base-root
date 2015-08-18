package com.skg.luohong.base.db.dao;

import java.util.Date;

/**
 * 所有的表实体继承这个类
 * @author 骆宏 846705189@qq.com 15013336884
 * @date 2015-08-18 17:58
 * */
public interface Tbl<PK> {
	public PK getId();
	public String getName();
	public String getCreateBy();
	public Date getCreateTime();
	public String getUpdateBy();
	public Date getUpdateTime();
}