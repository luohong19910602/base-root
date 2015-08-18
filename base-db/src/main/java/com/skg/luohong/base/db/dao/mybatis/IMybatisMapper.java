package com.skg.luohong.base.db.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;


/**
 * 标记类，所有的mapper都必须继承该接口
 * 改类会在MybatisAbstactDao中使用到，这里面使用了策略模式
 * 
 * @author 骆宏
 * @date 2015-08-18 18:12
 * */
public interface IMybatisMapper<PK, P> {
	/**
	 * 创建一个实体
	 * @param po 
	 * */
	public void create(P po);
	
	/**
	 * 更新一个实体
	 * @param po 实体对象
	 * */
	public void update(P po);
	
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
	public P get(PK id);
	
	/**
	 * 查询最新的PO
	 * @return
	 */
	public P getLast();
	
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
	public List<P> findAll();
	
	/**
	 * find all records
	 * alias of the findAll()
	 * @return
	 * */
	public List<P> listAll();
	
	/**
	 * find by page
	 * @param page the page of the records
	 * @return the list of the records
	 * */
	public List<P> findPaged(IPage page);
	
	/**
	 * find by page and params
	 * @params the map of the param,there will be use mybatis dynamic sql feature
	 * @return the list of the records
	 * */
	public List<P> findPaged(Map<String, Object> params,IPage page);
	
    /**
     * find all
     * @param the map of the param, there will be use mybatis dynamic sql feature
     * @return
     * */
	public List<P> findAll(Map<String, Object> params);
}
