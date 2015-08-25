package com.skg.luohong.base.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * mapper接口定义
 * 由于mybatis会自动生成mapper，所以所以子类的mapper接口直接继承该类即可
 * 
 * 在这里面，mapper默认的方法是非常丰富的，包含了增删改查的功能
 * @author 骆宏
 * @date 2015-08-19 23:02
 * */
public interface IMapper<PK extends Serializable, PO extends IPO<PK>> {
	/**
	 * 创建一个实体
	 * @param po 
	 * */
	public void create(PO po);

	/**
	 * 更新一个实体
	 * @param po 实体对象
	 * */
	public void update(PO po);

	/**
	 * 根据id删除实体
	 * @param id 实体id
	 * */
	public void delete(PK id);

	/**
	 * 根据id列表删除实体
	 * @param ids id列表
	 * */
	public void deleteByIds(List<PK> ids);

	/**
	 * 查询单个PO
	 * @param id
	 * @return
	 */
	public PO get(PK id);

	/**
	 * 查询最新的PO
	 * @return
	 */
	public PO getLast();

	/**
	 * 根据sql参数来获取记录数
	 * @param param sql参数
	 * */
	public Integer countAll();	
	
	/**
	 * find all records
	 * @return
	 * */
	public List<PO> findAll();
	
	/**
	 * 根据sql参数来获取记录数
	 * @param param sql参数
	 * */
	public Integer countAll(SqlParam params);
	
	/**
     * 根据sql参数查询，然后返回查询到的对象集合
     * @param params 查询的sql参数
     * @return
     * */
	public List<PO> findAll(SqlParam params);
}
