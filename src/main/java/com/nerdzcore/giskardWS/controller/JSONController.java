package com.nerdzcore.giskardWS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nerdzcore.giskardWS.Constants;
import com.nerdzcore.giskardWS.model.TriggerDTO;
import com.nerdzcore.giskardWS.service.IChatBotService;

@Controller
@RequestMapping("/irc/chatbot")
public class JSONController {
	
	/** Spring bean for chatBot service */
    @Autowired
    protected IChatBotService chatBotService;
    
	/**
	 * @param chatBotService the chatBotService to set
	 */
	public void setChatBotService(IChatBotService chatBotService) {
		this.chatBotService = chatBotService;
	}
	
	/**
	 * Function building a JSON's list mapping trigger's names and their content.
	 * @param name URI parameter related to the name of the trigger
	 */
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	List<TriggerDTO> getTriggerListInJSON(@PathVariable String name) {
		List<TriggerDTO> triggersReturn = new ArrayList<TriggerDTO>();
		if (name.equals(Constants.ALL)) {
			for (String triggerName : chatBotService.listTrigger(null)) {
				triggersReturn.add(addTriggerToList(triggerName));				
			}
		} else {
			triggersReturn.add(addTriggerToList(name));
		}
		return triggersReturn;
	}
	
	/**
	 * Function adding a trigger and its content to the JSON output
	 * @param name Trigger's name
	 * @return True if trigger has been deleted, false otherwise
	 */
	private TriggerDTO addTriggerToList(String name) {
		List<String> contentDTO = chatBotService.listContent(name);
		
		TriggerDTO triggerDTO = new TriggerDTO();
		triggerDTO.setTriggerName(name);
		triggerDTO.setTriggerContent(contentDTO.toArray(new String[contentDTO.size()]));
		
		return triggerDTO;
	}
}