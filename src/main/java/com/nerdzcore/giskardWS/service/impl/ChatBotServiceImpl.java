package com.nerdzcore.giskardWS.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerdzcore.giskardWS.Constants;
import com.nerdzcore.giskardWS.database.dao.IChatBotDao;
import com.nerdzcore.giskardWS.database.entities.ChatAnswerEntity;
import com.nerdzcore.giskardWS.database.entities.ChatTriggerEntity;
import com.nerdzcore.giskardWS.service.IChatBotService;

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
 * ChatBot service implementation class
 * @author [NrdzCr]Prndndrd
 */
@Service("chatBotService")
@Transactional(readOnly = true)
public class ChatBotServiceImpl implements IChatBotService {

	/** Logger */
	private static final Logger LOGGER = Logger.getLogger(ChatBotServiceImpl.class.getName());
	
	/** Spring bean for chatbot dao */
    @Autowired
    private IChatBotDao chatBotDao;
    
    
	/**
	 * @param chatBotDao the chatBotDao to set
	 */
	public void setChatBotDao(IChatBotDao chatBotDao) {
		this.chatBotDao = chatBotDao;
	}
	
    @Override
    @Transactional(readOnly = true)
    public Map<String,List<String>> getChatBotData() {
    	Map<String,List<String>> chatbotData = new HashMap<String,List<String>>();
    	
    	for (ChatTriggerEntity triggerEntity : chatBotDao.findAllTriggers()) {
    		List<String> answers = new ArrayList<String>();
    		for (ChatAnswerEntity answerEntity : triggerEntity.getAnswers()) {
    			answers.add(answerEntity.getAnswer());
    		}
    		chatbotData.put(triggerEntity.getTriggerName(), answers);
    	}
    	
    	return chatbotData;
    }
	
	
    @Override
    @Transactional(readOnly = true)
    public List<String> listTrigger(String triggerRegex) {
    	List<String> listTriggers = new ArrayList<String>();
    	
    	// Case we want every triggers
		if (triggerRegex == null) {
			List<ChatTriggerEntity> triggerEntityList = chatBotDao.findAllTriggers();
			if (triggerEntityList != null) {
				for (ChatTriggerEntity triggerEntity : triggerEntityList) {
					listTriggers.add(triggerEntity.getTriggerName());
				}
			} else {
				LOGGER.severe("Unable to get the list of triggers, chatbotDao.findAllTriggers() is null");
			}

		// Case we want only specific triggers
		} else {
			List<ChatTriggerEntity> triggerEntityList = chatBotDao.findTriggers(triggerRegex);
			if (triggerEntityList != null) {
				for (ChatTriggerEntity triggerEntity : triggerEntityList) {
					listTriggers.add(triggerEntity.getTriggerName());
				}
			} else {
				LOGGER.severe("Unable to get the list of triggers, chatbotDao.findTriggers() is null");
			}
		}

    	return listTriggers;
    }
	
    @Override
    @Transactional(readOnly = false)
    public Boolean addTrigger(String triggerName) {
    	try {
    		// Check that the new trigger doesn't exists already
    		if (chatBotDao.loadTriggerFromName(triggerName) == null) {
    			ChatTriggerEntity triggerEntity = new ChatTriggerEntity();
    			triggerEntity.setTriggerName(triggerName.toLowerCase());
		
		        // Saving trigger
		        chatBotDao.saveTrigger(triggerEntity);
		        LOGGER.info("Creating new trigger => " + triggerName);
		        return true;
    		} else {
    			LOGGER.warning("Unable to create new trigger, trigger name already exists into the database => " + triggerName);
    		}
    	} catch (Exception e) {
    		LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
		return false;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Boolean deleteTrigger(String triggerName) {
    	try {
    		// Check that the trigger actually exists
    		ChatTriggerEntity triggerDelete = chatBotDao.loadTriggerFromName(triggerName);
    		if (triggerDelete != null) {
		        chatBotDao.deleteTrigger(triggerDelete);
		        LOGGER.info("Deleting trigger => " + triggerName);
		        return true;
    		} else {
    			LOGGER.warning("Unable to delete trigger, trigger name does not exists into the database => " + triggerName);
    		}
    	} catch (Exception e) {
    		LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
		return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<String> listContent(String triggerName) {
    	List<String> listContents = new ArrayList<String>();
    	
		// Check that the trigger actually exists
		ChatTriggerEntity triggerEntity = chatBotDao.loadTriggerFromName(triggerName);
		if (triggerEntity != null) {
			for (ChatAnswerEntity answerEntity : triggerEntity.getAnswers()) {
				listContents.add(answerEntity.getAnswer());
			}
		} else {
			LOGGER.warning("Unable to list content of the trigger => " + triggerName + ". This trigger does not exists into the database");
		}
		return listContents;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Boolean addContent(String triggerName, String sentence) {
    	try {
    		// Check that the trigger does exists
    		ChatTriggerEntity triggerEntity = chatBotDao.loadTriggerFromName(triggerName);
    		if (triggerEntity != null) {
    			
    			// Check that the new content doesn't exists already
    			Boolean isNewSentence = true;
    			for (ChatAnswerEntity triggerAnswerEntity : triggerEntity.getAnswers()) {
    				if (triggerAnswerEntity.getAnswer().equalsIgnoreCase(sentence)) {
    					isNewSentence = false;
    				}
    			}

    			// Adding answer to trigger's list and persist into database
        		if (isNewSentence) {
        			ChatAnswerEntity answerEntity = new ChatAnswerEntity();
        			answerEntity.setTrigger(triggerEntity);
        			answerEntity.setAnswer(sentence);
        			triggerEntity.getAnswers().add(answerEntity);
    		
        			chatBotDao.saveTrigger(triggerEntity);
    		        LOGGER.info("Answer => " + sentence + " added to the trigger => " + triggerName);
    		        return true;
        		} else {
        			LOGGER.warning("Unable to add new sentence because it already exists into the database => " + sentence);
        		}
    		} else {
    			LOGGER.warning("Unable to add content, the trigger " + triggerName + " does not exists into the database");
    		}
    	} catch (Exception e) {
    		LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
		return false;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Boolean deleteContent(String triggerName, String sentence) {
    	try {
    		// Check that the trigger does exists
    		ChatTriggerEntity triggerEntity = chatBotDao.loadTriggerFromName(triggerName);
    		if (triggerEntity != null) {
    			
    			// Check that the new content does exists
    			for (ChatAnswerEntity triggerAnswerEntity : triggerEntity.getAnswers()) {
    				if (triggerAnswerEntity.getAnswer().equalsIgnoreCase(sentence)) {
    					triggerEntity.getAnswers().remove(triggerAnswerEntity);
            			chatBotDao.saveTrigger(triggerEntity);
        		        LOGGER.info("Answer => " + sentence + " deleted from trigger => " + triggerName);
        		        return true;
    				}
    			}
        		LOGGER.warning("Unable to delete sentence because it does not exists into the database => " + sentence);
    		} else {
    			LOGGER.warning("Unable to delete content, the trigger " + triggerName + " does not exists into the database");
    		}
    	} catch (Exception e) {
    		LOGGER.log(Level.SEVERE, Constants.EXCEPTION_TRACE, e);
    	}
		return false;
    }
}