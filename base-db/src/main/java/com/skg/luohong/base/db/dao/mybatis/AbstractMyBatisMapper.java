package com.skg.luohong.base.db.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.AbstractPo;

/**
 * 提供一个通用Mapper实现
 * 所有之类直接继承该类即可
 * @author 骆宏 846705189@qq.com
 * */
public abstract class AbstractMyBatisMapper<PK extends java.io.Serializable, T extends AbstractPo<PK>> implements IMybatisMapper<PK, T> {

	@Override
	public void create(T po) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(T po) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(PK id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByIds(List<PK> ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(PK id) {
		return null;
	}

	@Override
	public T getLast() {
		return null;
	}

	@Override
	public Integer countAll() {
		return null;
	}

	@Override
	public Integer count(Map<String, Object> params) {
		return null;
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public List<T> listAll() {
		return null;
	}

	@Override
	public List<T> findPaged(IPage page) {
		return null;
	}

	@Override
	public List<T> findPaged(Map<String, Object> params, IPage page) {
		return null;
	}

	@Override
	public List<T> findAll(Map<String, Object> params) {
		return null;
	}
}
