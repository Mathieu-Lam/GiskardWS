package com.nerdzcore.giskardWS.model;

/**
 * Data Transfer Object for chatbot module
 */
public class TriggerDTO {

	/** Trigger name */
	String triggerName;
	
	/** Trigger values */
	String triggerContent[];
	
	
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	/**
	 * @return the triggerContent
	 */
	public String[] getTriggerContent() {
		return triggerContent;
	}

	/**
	 * @param triggerContent the triggerContent to set
	 */
	public void setTriggerContent(String[] triggerContent) {
		this.triggerContent = triggerContent;
	}

	/** Def. Constructor */
	public TriggerDTO() {
	} 
}