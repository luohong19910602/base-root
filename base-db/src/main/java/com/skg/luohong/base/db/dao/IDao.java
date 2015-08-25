package com.skg.luohong.base.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * dao最大接口，用于定义dao的通用操作
 * 这里面将查询，新增，修改，翻页等通用操作都放在一个dao中
 * 
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-18 17:06
 * */
public interface IDao<PK extends Serializable, PO extends IPO<PK>> {
    
	public IMapper<PK, PO> getMapper();
	
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
	 * 查询count方法
	 * @return
	 */
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
