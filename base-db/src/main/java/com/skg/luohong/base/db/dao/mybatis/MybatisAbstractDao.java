package com.skg.luohong.base.db.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.AbstractPo;
import com.skg.luohong.base.db.dao.IDao;

/**
 * mybatis通用dao实现类
 * 这里面每个具体的dao都需要提供一个mapper来完成任务
 * 
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-18 17:44
 * */
public abstract class MybatisAbstractDao<PK extends java.io.Serializable, T extends AbstractPo<PK>> implements IDao<PK, T> {
	
	public abstract String getNamespace();
	
	public abstract IMybatisMapper<PK, T> getMapper();
	
	@Override
	public void create(T po) {
		getMapper().create(po);
	}
	@Override
	public void update(T po) {
		getMapper().update(po);
	}
	@Override
	public void delete(PK id) {
		getMapper().delete(id);
	}
	@Override
	public void deleteByIds(List<PK> ids) {
		getMapper().deleteByIds(ids);
	}
	@Override
	public T get(PK id) {
		return (T) getMapper().get(id);
	}
	
	@Override
	public T getLast() {
		return getMapper().getLast();
	}
	@Override
	public Integer countAll() {
		return getMapper().countAll();
	}
	@Override
	public Integer count(Map<String, Object> params) {
		return getMapper().count(params);
	}
	@Override
	public List<T> findAll() {
		return getMapper().findAll();
	}
	@Override
	public List<T> listAll() {
		return getMapper().listAll();
	}
	@Override
	public List<T> findPaged(IPage page) {
		return getMapper().findPaged(page);
	}
	@Override
	public List<T> findPaged(Map<String, Object> params, IPage page) {
		return getMapper().findPaged(params, page);
	}
	@Override
	public List<T> findAll(Map<String, Object> params) {
		return getMapper().findAll(params);
	}
}
