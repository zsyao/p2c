package com.zsyao.core.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.zsyao.core.dao.IDao;
import com.zsyao.core.model.BaseEntity;

public class MybatisDao<T extends BaseEntity> implements IDao
{
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MybatisDao()
	{
	}
	
	public int delete(String statementid) throws Exception
	{
		return sqlSessionTemplate.delete(statementid);
	}

	public int delete(String statementid, BaseEntity entity) throws Exception
	{
		return sqlSessionTemplate.delete(statementid, entity);
	}

	public int insert(String statementid) throws Exception
	{
		return sqlSessionTemplate.insert(statementid);
	}

	public int insert(String statementid, BaseEntity entity) throws Exception
	{
		return sqlSessionTemplate.insert(statementid, entity);
	}

	public int update(String statementid) throws Exception
	{
		return sqlSessionTemplate.update(statementid);
	}

	public int update(String statementid, BaseEntity entity) throws Exception
	{
		return sqlSessionTemplate.update(statementid, entity);
	}

	public List<T> selectList(String statementid)
	{
		return sqlSessionTemplate.selectList(statementid);
	}

	public List<T> selectList(String statementid, BaseEntity entity)
	{
		return sqlSessionTemplate.selectList(statementid, entity);
	}

	public List<T> selectPaginatedList(String statementid, BaseEntity entity, int skipResults, int maxResults)
	{
		return sqlSessionTemplate.selectList(statementid, entity, new RowBounds(skipResults, maxResults));
	}

	public Object selectObject(String statementid)
	{
		return sqlSessionTemplate.selectOne(statementid);
	}
	
	public Object selectObject(String statementid, BaseEntity entity)
	{
		return sqlSessionTemplate.selectOne(statementid, entity);
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
	{
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate()
	{
		return sqlSessionTemplate;
	}

}
