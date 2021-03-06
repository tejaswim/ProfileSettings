/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.profilesettings.sales.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.profilesettings.sales.Year;


/**
 * ServiceImpl object for domain model class Year.
 *
 * @see Year
 */
@Service("sales.YearService")
public class YearServiceImpl implements YearService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YearServiceImpl.class);


    @Autowired
    @Qualifier("sales.YearDao")
    private WMGenericDao<Year, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Year, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesTransactionManager")
    @Override
	public Year create(Year year) {
        LOGGER.debug("Creating a new Year with information: {}", year);
        Year yearCreated = this.wmGenericDao.create(year);
        return yearCreated;
    }

	@Transactional(readOnly = true, value = "salesTransactionManager")
	@Override
	public Year getById(Integer yearId) throws EntityNotFoundException {
        LOGGER.debug("Finding Year by id: {}", yearId);
        Year year = this.wmGenericDao.findById(yearId);
        if (year == null){
            LOGGER.debug("No Year found with id: {}", yearId);
            throw new EntityNotFoundException(String.valueOf(yearId));
        }
        return year;
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "salesTransactionManager")
	@Override
	public Year update(Year year) throws EntityNotFoundException {
        LOGGER.debug("Updating Year with information: {}", year);
        this.wmGenericDao.update(year);

        Integer yearId = year.getId();

        return this.wmGenericDao.findById(yearId);
    }

    @Transactional(value = "salesTransactionManager")
	@Override
	public Year delete(Integer yearId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Year with id: {}", yearId);
        Year deleted = this.wmGenericDao.findById(yearId);
        if (deleted == null) {
            LOGGER.debug("No Year found with id: {}", yearId);
            throw new EntityNotFoundException(String.valueOf(yearId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "salesTransactionManager")
	@Override
	public Page<Year> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Years");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Page<Year> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Years");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service sales for table Year to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "salesTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

