package com.nerdzcore.giskardWS.database.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
 * Class for chat answer entity
 * @author [NrdzCr]Prndndrd
 */
@Entity
@Table(name="chatbot_answers", uniqueConstraints = {@UniqueConstraint(columnNames = {"trigger_id","answer"})})
public class ChatAnswerEntity implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = -1569030888087880166L;

	/** ID */
	private Integer id;
	
	/** Chatbot trigger */
	private ChatTriggerEntity trigger;
	
	/** Chatbot answer */
	private String answer;


	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatbot_answers_seq_gen")
	@SequenceGenerator(name = "chatbot_answers_seq_gen", sequenceName = "chatbot_answers_id_seq", allocationSize = 1)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the trigger
	 */
	@ManyToOne
	@JoinColumn(name="trigger_id", nullable = false)
	public ChatTriggerEntity getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(ChatTriggerEntity trigger) {
		this.trigger = trigger;
	}
	
	/**
	 * @return the answer
	 */
	@Column(name = "answer", unique = false, nullable = false, length = 255)
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}