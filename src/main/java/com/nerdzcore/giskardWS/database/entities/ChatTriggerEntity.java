package com.nerdzcore.giskardWS.database.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * Class for chatbot word entity
 * @author [NrdzCr]Prndndrd
 */
@Entity
@Table(name="chatbot_words", uniqueConstraints = {@UniqueConstraint(columnNames = "trigger_word")})
public class ChatTriggerEntity implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 9137220762871703401L;

	/** ID */
	private Integer id;
	
	/** Trigger name */
	private String triggerName;
	
	/** Set of answers for this trigger */
	private Set<ChatAnswerEntity> answers;


	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatbot_words_seq_gen")
	@SequenceGenerator(name = "chatbot_words_seq_gen", sequenceName = "chatbot_words_id_seq", allocationSize = 1)
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
	 * @return the triggerName
	 */
	@Column(name = "trigger_word", unique = true, nullable = false, length = 30)
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
	 * @return the answers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trigger", orphanRemoval = true, cascade = CascadeType.ALL)
	public Set<ChatAnswerEntity> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<ChatAnswerEntity> answers) {
		this.answers = answers;
	}
}