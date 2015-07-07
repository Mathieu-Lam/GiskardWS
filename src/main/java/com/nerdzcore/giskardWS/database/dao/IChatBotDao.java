package com.nerdzcore.giskardWS.database.dao;

import java.util.List;

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
 * ChatBot DAO interface.
 * @author [NrdzCr]Prndndrd
 */
public interface IChatBotDao extends AbstractDao<ChatTriggerEntity, String> {

	/**
	 * Function adding a trigger into database
	 * @param trigger Trigger to add into database
	 */
    void saveTrigger(ChatTriggerEntity trigger);
    
	/**
	 * Function deleting a trigger from database
	 * @param trigger Trigger to delete
	 */
    void deleteTrigger(ChatTriggerEntity trigger);
    
    /**
     * Function loading a trigger from its name
     * @param name Name of the trigger to be loaded
     * @return The trigger to be loaded or null if not found
     */
    ChatTriggerEntity loadTriggerFromName(String name);
    
    /**
     * Function getting all triggers of the database
     * @return list of all triggers
     */
    List<ChatTriggerEntity> findAllTriggers();
    
    /**
     * Function searching triggers matching specific name pattern
     * @param namePattern Name pattern regex of triggers we are searching for
     * @return list of triggers matching above criterias
     */
    List<ChatTriggerEntity> findTriggers(String namePattern);
}