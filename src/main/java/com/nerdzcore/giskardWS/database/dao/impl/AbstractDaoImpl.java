package com.nerdzcore.giskardWS.database.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nerdzcore.giskardWS.database.dao.AbstractDao;

/**
 *  Copyright (C) 2014-2999 Lammer Mathieu

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/**
 * Abstract DAO class.
 * @param <E> Entity object
 * @param <I> Id object
 * @author [NrdzCr]Prndndrd
 */
public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E,I> {

	/** Entity object class */
    private Class<E> entityClass;
    
    /** Session */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Set up entityClass
     * @param entityClass class type
     */
    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the currentSession
	 */
	public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
	@SuppressWarnings("unchecked")
    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }

    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }
}