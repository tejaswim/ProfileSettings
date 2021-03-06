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

import com.profilesettings.sales.Sale;

/**
 * Service object for domain model class Sale.
 *
 * @see {@link Sale}
 */
public interface SaleService {

    /**
     * Creates a new Sale.
     *
     * @param sale The information of the created CompositeTable.
     * @return The created Sale.
     */
	Sale create(Sale sale);


	/**
	 * Finds Sale by id.
	 *
	 * @param saleId The id of the wanted Sale.
	 * @return The found Sale. If no Sale is found, this method returns null.
	 */
	Sale getById(Integer saleId) throws EntityNotFoundException;


	/**
	 * Updates the information of a Sale.
	 *
	 * @param sale The information of the updated Sale.
	 * @return The updated Sale.
     *
	 * @throws EntityNotFoundException if no Sale is found with given id.
	 */
	Sale update(Sale sale) throws EntityNotFoundException;

    /**
	 * Deletes a Sale.
	 *
	 * @param saleId The id of the deleted Sale.
	 * @return The deleted Sale.
     *
	 * @throws EntityNotFoundException if no Sale is found with the given id.
	 */
	Sale delete(Integer saleId) throws EntityNotFoundException;

	/**
	 * Finds all Sales.
	 *
	 * @return A list of Sales.
	 */
    @Deprecated
	Page<Sale> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Finds all Sales.
	 * @return A list of Sales.
	 */
    Page<Sale> findAll(String query, Pageable pageable);

    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the Sales in the repository with matching query.
     *
     * @param query query to filter results.
	 * @return The count of the Sale.
	 */
	long count(String query);


}

