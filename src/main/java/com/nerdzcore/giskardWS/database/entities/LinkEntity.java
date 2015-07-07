package com.nerdzcore.giskardWS.database.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * Class for weblinks entity
 * @author [NrdzCr]Prndndrd
 */
@Entity
@Table(name="linkbot", uniqueConstraints = {@UniqueConstraint(columnNames = "link_url")})
public class LinkEntity implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 4648161080733320493L;

	/** ID */
	private Integer id;
	
	/** Link url */
	private String linkUrl;
	
	/** Link description */
	private String linkDescription;


	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "linkbot_seq_gen")
	@SequenceGenerator(name = "linkbot_seq_gen", sequenceName = "linkbot_id_seq", allocationSize = 1)
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
	 * @return the linkUrl
	 */
	@Column(name = "link_url", unique = true, nullable = false, length = 255)
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	/**
	 * @return the linkDescription
	 */
	@Column(name = "link_description", unique = true, nullable = false, length = 255)
	public String getLinkDescription() {
		return linkDescription;
	}

	/**
	 * @param linkDescription the linkDescription to set
	 */
	public void setLinkDescription(String linkDescription) {
		this.linkDescription = linkDescription;
	}
}