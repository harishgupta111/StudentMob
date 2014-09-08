package com.student.app.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

public class BaseDaoHibernateSupport<T, PK extends Serializable> {

	private static final Logger logger = Logger
			.getLogger(BaseDaoHibernateSupport.class);
	private final Class<T> domainClazz;

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoHibernateSupport() {
		domainClazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * @SuppressWarnings("unchecked")
	 * @param anId
	 * @return
	 * @throws ObjectRetrievalFailureException
	 */
	@SuppressWarnings("unchecked")
	public final T byId(final PK anId) throws ObjectRetrievalFailureException {

		T t = (T) currentSession().get(domainClazz, anId);
		final T entity = t;
		if (entity == null) {
			throw new ObjectRetrievalFailureException(domainClazz, anId);
		}
		return entity;
	}

	public final int deleteByPK(final String aQueryString,
			final Map<String, Object> aQueryParameters) {
		final Query query = getQuery(aQueryString, Boolean.FALSE);
		final int results = bindParametersAndExecuteUpdate(query,
				aQueryParameters);
		return results;
	}

	/**
	 * 
	 * @param anEntity
	 * @return
	 * @throws HibernateException
	 */
	public final T insert(final T anEntity) throws HibernateException {
		return insert(anEntity, false);
	}

	/**
	 * 
	 * @param anEntity
	 * @param immediately
	 * @return
	 * @throws HibernateException
	 */
	public final T insert(final T anEntity, final boolean immediately)
			throws HibernateException {
		currentSession().saveOrUpdate(anEntity);
		if (immediately) {
			currentSession().flush();
		}
		return anEntity;
	}

	/**
	 * 
	 * @param anEntity
	 * @return
	 */
	public final T update(final T anEntity) {
		return update(anEntity, false);
	}

	/**
	 * 
	 * @param anEntity
	 * @param aImmediately
	 * @return
	 */
	public final T update(final T anEntity, final boolean aImmediately) {
		currentSession().saveOrUpdate(anEntity);
		if (aImmediately) {
			currentSession().flush();
		}
		return anEntity;
	}

	/**
	 * 
	 * @param aQueryString
	 * @return
	 * @throws HibernateException
	 */
	public final List<?> executeQuery(final String aQueryString)
			throws HibernateException {
		return executeQuery(aQueryString, null);
	}

	/**
	 * 
	 * @param string
	 * @param map
	 * @return
	 */
	public List<?> executeNamedQuery(String queryName,
			Map<String, Object> aQueryParameters) {
		final Query query = getQuery(queryName, Boolean.TRUE);
		query.setCacheable(true);
		logger.debug("Before executing the query " + query.getQueryString());
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * 
	 * @param aQueryString
	 * @param aQueryParameters
	 * @return
	 * @throws HibernateException
	 */
	public final List<?> executeQuery(final String aQueryString,
			final Map<String, Object> aQueryParameters)
			throws HibernateException {
		final Query query = getQuery(aQueryString, Boolean.FALSE);
		query.setCacheable(true);
		logger.debug("Before executing the query " + query.getQueryString());
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * 
	 * @param aQueryString
	 * @param aQueryParameters
	 * @param aStartFrom
	 * @param aMaxResults
	 * @return
	 */
	public List<?> executeQueryForPaginatedResults(final String aQueryString,
			final Map<String, Object> aQueryParameters, final int aStartFrom,
			final int aMaxResults) {
		final Query query = getQuery(aQueryString, false);
		query.setFirstResult(aStartFrom);
		query.setMaxResults(aMaxResults);
		query.setCacheable(true);
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * 
	 * @param query
	 * @return
	 * @throws HibernateException
	 */
	private Query getQuery(final String query, Boolean isNamedQuery)
			throws HibernateException {
		Query q = null;
		if (isNamedQuery == Boolean.TRUE) {
			q = currentSession().getNamedQuery(query);
		} else {
			q = currentSession().createQuery(query);
		}
		return q;
	}

	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * @throws HibernateException
	 */
	private List<?> bindParametersAndExecuteQuery(final Query query,
			final Map<String, Object> params) throws HibernateException {
		if (params != null && !params.isEmpty()) {
			final Set<Map.Entry<String, Object>> parameters = params.entrySet();

			for (final Map.Entry<String, Object> parameter : parameters) {
				final Object value = parameter.getValue();
				final Class<?> type = value.getClass();

				if (type.isArray()) {
					query.setParameter(parameter.getKey(), value);
				} else if (Collection.class.isInstance(value)) {
					query.setParameterList(parameter.getKey(),
							(Collection<?>) parameter.getValue());
				} else {
					query.setParameter(parameter.getKey(), parameter.getValue());
				}
			}
		}

		List<?> results = null;

		try {
			results = query.list();
		} catch (final Exception e) {
			logger.error("****** PRINTING STACK TRACE ************ ");
			logger.error(String.format(
					"Error in executing query. Query[%s]Params[%s]Entity[%s]",
					query.toString(), params, domainClazz.getName()), e);
			logger.error("****** END OF STACK TRACE ************ ");
			logger.error(String.format(e.getCause().getLocalizedMessage()));
			throw new HibernateException(String.format(e.getCause()
					.getLocalizedMessage()));
		}

		return results;
	}

	private int bindParametersAndExecuteUpdate(final Query query,
			final Map<String, Object> params) throws HibernateException {
		if (params != null && !params.isEmpty()) {
			final Set<Map.Entry<String, Object>> parameters = params.entrySet();

			for (final Map.Entry<String, Object> parameter : parameters) {
				final Object value = parameter.getValue();
				final Class<?> type = value.getClass();

				if (type.isArray()) {
					query.setParameter(parameter.getKey(), value);
				} else if (Collection.class.isInstance(value)) {
					query.setParameterList(parameter.getKey(),
							(Collection<?>) parameter.getValue());
				} else {
					query.setParameter(parameter.getKey(), parameter.getValue());
				}
			}
		}

		int result;

		try {
			result = query.executeUpdate();
		} catch (final Exception e) {
			logger.error("****** PRINTING STACK TRACE ************ ");
			logger.error(String.format(
					"Error in executing query. Query[%s]Params[%s]Entity[%s]",
					query.toString(), params, domainClazz.getName()), e);
			logger.error("****** END OF STACK TRACE ************ ");
			logger.error(String.format(e.getCause().getLocalizedMessage()));
			throw new HibernateException(String.format(e.getCause()
					.getLocalizedMessage()));
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	private Session currentSession() {
		return this.sessionFactory.getCurrentSession();
	}

}