package com.nerdzcore.giskardWS.database.dao;

import java.io.Serializable;

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
 * Abstract DAO interface.
 * @param <E> Entity object
 * @param <I> Id object
 * @author [NrdzCr]Prndndrd
 */
public interface AbstractDao<E, I extends Serializable> {

	/**
	 * Method used to find an object entity by ID.
	 * @param Id of the object
	 * @return Entity object
	 */
    E findById(I id);
    
    /**
     * Method used to save or update an object into database
     * @param entity Entity object
     */
    void saveOrUpdate(E entity);
    
    /**
     * Method used to delete an object from database
     * @param entity Entity object
     */
    void delete(E entity);
}