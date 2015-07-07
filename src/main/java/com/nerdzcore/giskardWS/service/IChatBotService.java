package com.nerdzcore.giskardWS.service;

import java.util.List;
import java.util.Map;

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
 * ChatBot service interface.
 * @author [NrdzCr]Prndndrd
 */
public interface IChatBotService {

	/**
	 * Function returning a map associating every trigger with its answers
	 * @return Maps of triggers and answers
	 */
    Map<String,List<String>> getChatBotData();
    
	/**
	 * Function returning a list of triggers
	 * @param triggerRegex We search for triggers with name matching this regex
	 * @return List of triggers matching 'triggerRegex' parameter
	 */
    List<String> listTrigger(String triggerRegex);

	/**
	 * Function adding a trigger to database
	 * @param triggerName Trigger to add
	 * @return True if trigger has been added, false otherwise
	 */
    Boolean addTrigger(String triggerName);

	/**
	 * Function deleting a trigger from database
	 * @param triggerName Trigger to delete
	 * @return True if trigger has been deleted, false otherwise
	 */
    Boolean deleteTrigger(String triggerName);
    
	/**
	 * Function returning a list of answers linked to a trigger
	 * @param triggerName Trigger we want to list answers
	 * @return List of answers
	 */
    List<String> listContent(String triggerName);

	/**
	 * Function adding an answer to a trigger
	 * @param triggerName Trigger we want to add an answer
	 * @param sentence Sentence to be add to the trigger
	 * @return True if content has been added, false otherwise
	 */
    Boolean addContent(String triggerName, String sentence);

	/**
	 * Function deleting an answer from a trigger
	 * @param triggerName Trigger we want to delete an answer
	 * @param sentence Sentence to be removed from the trigger
	 * @return True if content has been removed, false otherwise
	 */
    Boolean deleteContent(String triggerName, String sentence);
}