package com.skg.luohong.base.db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;

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
	 * 查询count方法
	 * @return
	 */
	public Integer countAll();

	/**
	 * 查询count方法，传入参数
	 * @param params
	 * @return
	 */
	public Integer count(Map<String, Object> params);

	/**
	 * find all records
	 * @return
	 * */
	public List<PO> findAll();

	/**
	 * find all records
	 * alias of the findAll()
	 * @return
	 * */
	public List<PO> listAll();

	/**
	 * find by page
	 * @param page the page of the records
	 * @return the list of the records
	 * */
	public List<PO> findPaged(IPage page);

	/**
	 * find by page and params
	 * @params the map of the param,there will be use mybatis dynamic sql feature
	 * @return the list of the records
	 * */
	public List<PO> findPaged(Map<String, Object> params,IPage page);

	/**
	 * find all
	 * @param the map of the param, there will be use mybatis dynamic sql feature
	 * @return
	 * */
	public List<PO> findAll(Map<String, Object> params);
}