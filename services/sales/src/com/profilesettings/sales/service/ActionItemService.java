/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.profilesettings.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.profilesettings.sales.ActionItem;

/**
 * Service object for domain model class ActionItem.
 *
 * @see {@link ActionItem}
 */
public interface ActionItemService {

    /**
     * Creates a new ActionItem.
     *
     * @param actionItem The information of the created CompositeTable.
     * @return The created ActionItem.
     */
	ActionItem create(ActionItem actionItem);


	/**
	 * Finds ActionItem by id.
	 *
	 * @param actionItemId The id of the wanted ActionItem.
	 * @return The found ActionItem. If no ActionItem is found, this method returns null.
	 */
	ActionItem getById(Integer actionItemId) throws EntityNotFoundException;


	/**
	 * Updates the information of a ActionItem.
	 *
	 * @param actionItem The information of the updated ActionItem.
	 * @return The updated ActionItem.
     *
	 * @throws EntityNotFoundException if no ActionItem is found with given id.
	 */
	ActionItem update(ActionItem actionItem) throws EntityNotFoundException;

    /**
	 * Deletes a ActionItem.
	 *
	 * @param actionItemId The id of the deleted ActionItem.
	 * @return The deleted ActionItem.
     *
	 * @throws EntityNotFoundException if no ActionItem is found with the given id.
	 */
	ActionItem delete(Integer actionItemId) throws EntityNotFoundException;

	/**
	 * Finds all ActionItems.
	 *
	 * @return A list of ActionItems.
	 */
    @Deprecated
	Page<ActionItem> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Finds all ActionItems.
	 * @return A list of ActionItems.
	 */
    Page<ActionItem> findAll(String query, Pageable pageable);

    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the ActionItems in the repository with matching query.
     *
     * @param query query to filter results.
	 * @return The count of the ActionItem.
	 */
	long count(String query);


}
