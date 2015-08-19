package com.skg.luohong.base.db.dao;

import java.util.Date;

/**
 * table抽象类
 * 所有的tbl直接继承该类，一个字段对应一个属性
 * 如果需要扩展tbl，直接扩展po类即可
 * @author 骆宏
 * @date 2015-08-19 23:32
 * */
public interface ITbl<PK> {
    /**
     * 代理id的获取方法，子类tbl需要override掉这个方法
     * @return 代理主键
     * */
	PK getId();
    String getCreateBy();
    Date getCreateTime();
    String getUpdateBy();
    Date getUpdateTime();
}
