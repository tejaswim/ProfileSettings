/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.profilesettings.sales.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.profilesettings.sales.Year;
import com.profilesettings.sales.service.YearService;
import com.wordnik.swagger.annotations.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

/**
 * Controller object for domain model class Year.
 * @see Year
 */
@RestController("sales.YearController")
@RequestMapping("/sales/Year")
@Api(description = "Exposes APIs to work with Year resource.", value = "YearController")
public class YearController {

    private static final Logger LOGGER = LoggerFactory.getLogger(YearController.class);

    @Autowired
    @Qualifier("sales.YearService")
    private YearService yearService;

    /**
     * @deprecated Use {@link #findYears(String, Pageable)} instead.
     */
    @Deprecated
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of Year instances matching the search criteria.")
    public Page<Year> findYears(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Years list");
        return yearService.findAll(queryFilters, pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of Year instances matching the search criteria.")
    public Page<Year> findYears(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Years list");
        return yearService.findAll(query, pageable);
    }

    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @ApiOperation(value = "Returns downloadable file for the data.")
    public Downloadable exportYears(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return yearService.export(exportType, query, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service YearService instance
	 */
    protected void setYearService(YearService service) {
        this.yearService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new Year instance.")
    public Year createYear(@RequestBody Year year) {
        LOGGER.debug("Create Year with information: {}", year);
        year = yearService.create(year);
        LOGGER.debug("Created Year with information: {}", year);
        return year;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of Year instances.")
    public Long countYears(@RequestParam(value = "q", required = false) @ApiParam(value = "conditions to filter the results") String query) {
        LOGGER.debug("counting Years");
        return yearService.count(query);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the Year instance associated with the given id.")
    public Year getYear(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Year with id: {}", id);
        Year foundYear = yearService.getById(id);
        LOGGER.debug("Year details with id: {}", foundYear);
        return foundYear;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the Year instance associated with the given id.")
    public Year editYear(@PathVariable(value = "id") Integer id, @RequestBody Year year) throws EntityNotFoundException {
        LOGGER.debug("Editing Year with id: {}", year.getId());
        year.setId(id);
        year = yearService.update(year);
        LOGGER.debug("Year details with id: {}", year);
        return year;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the Year instance associated with the given id.")
    public boolean deleteYear(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Year with id: {}", id);
        Year deletedYear = yearService.delete(id);
        return deletedYear != null;
    }
}
