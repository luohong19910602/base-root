package com.skg.luohong.base.db.dao.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.IDao;
import com.skg.luohong.base.db.dao.IMapper;
import com.skg.luohong.base.db.dao.IPO;

/**
 * 所有的子类dao直接继承该类，提供mapper即可
 * 这里面使用模板方法设计模式
 * 
 * 
 * @author 骆宏 15013336884
 * @date 2015-08-20 15:53
 * */
public abstract class AbstractDao<PK extends Serializable, PO extends IPO<PK>> implements IDao<PK, PO> {

	@Override
	public abstract IMapper<PK, PO> getMapper();

	@Override
	public void create(PO po) {
	    getMapper().create(po);
	}

	@Override
	public void update(PO po) {
		getMapper().update(po);
	}

	@Override
	public void delete(PK id) {
		getMapper().delete(id);
	}

	@Override
	public void deleteByIds(List<PK> ids) {
		for(PK id: ids){
			getMapper().delete(id);
		}
	}

	@Override
	public PO get(PK id) {
		return getMapper().get(id);
	}

	@Override
	public PO getLast() {
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
	public List<PO> findAll() {
		return getMapper().findAll();
	}

	@Override
	public List<PO> listAll() {
		return getMapper().listAll();
	}

	@Override
	public List<PO> findPaged(IPage page) {
		return getMapper().findPaged(page);
	}

	@Override
	public List<PO> findPaged(Map<String, Object> params, IPage page) {
		return getMapper().findPaged(params, page);
	}

	@Override
	public List<PO> findAll(Map<String, Object> params) {
		return getMapper().findAll(params);
	}
}
