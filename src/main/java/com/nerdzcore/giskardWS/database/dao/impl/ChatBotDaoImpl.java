package com.nerdzcore.giskardWS.database.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nerdzcore.giskardWS.Constants;
import com.nerdzcore.giskardWS.database.dao.IChatBotDao;
import com.nerdzcore.giskardWS.database.entities.ChatTriggerEntity;

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
 * ChatBot DAO implementation class
 * @author [NrdzCr]Prndndrd
 */
@Repository
public class ChatBotDaoImpl extends AbstractDaoImpl<ChatTriggerEntity, String> implements IChatBotDao {

	/** Logger */
	private static final Logger LOGGER = Logger.getLogger(ChatBotDaoImpl.class.getName());
	

	/** 
	 * Constructor 
	 */
    protected ChatBotDaoImpl() {
        super(ChatTriggerEntity.class);
    }

    @Override
    public void saveTrigger(ChatTriggerEntity trigger) {
    	try {
    		saveOrUpdate(trigger);
    	} catch (Exception e) {
			LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
    }
    
    @Override
    public void deleteTrigger(ChatTriggerEntity trigger) {
    	try {
    		delete(trigger);
    	} catch (Exception e) {
			LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
    }
    
    @Override
    public ChatTriggerEntity loadTriggerFromName(String name) {
    	try {
    		String sql = "SELECT t FROM ChatTriggerEntity t WHERE lower(t.triggerName) = lower(:name)";
    		Query query = getCurrentSession().createQuery(sql).setParameter("name", name);
    		if  (query.list() != null && !query.list().isEmpty()) {
    			return (ChatTriggerEntity) query.list().get(0);
    		} else {
    			return null;
    		}
    	} catch (Exception e) {
			LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
			return null;
    	}
    }
    
    @Override
    public List<ChatTriggerEntity> findAllTriggers() {
    	try {
	    	Criteria criteria = getCurrentSession().createCriteria(ChatTriggerEntity.class, "chatTriggerEntity");
	    	return criteria.list();
    	} catch (Exception e) {
			LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
			return null;
    	}
    }
    
    @Override
    public List<ChatTriggerEntity> findTriggers(String namePattern) {
    	try {
	    	Criteria criteria = getCurrentSession().createCriteria(ChatTriggerEntity.class, "chatTriggerEntity");
			criteria.add(Restrictions.like("triggerName", namePattern, MatchMode.START).ignoreCase());
	    	return criteria.list();
    	} catch (Exception e) {
			LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
			return null;
    	}
    }
}