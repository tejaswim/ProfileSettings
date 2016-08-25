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
import com.profilesettings.sales.AccountManager;
import com.profilesettings.sales.service.AccountManagerService;
import com.wordnik.swagger.annotations.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

/**
 * Controller object for domain model class AccountManager.
 * @see AccountManager
 */
@RestController("sales.AccountManagerController")
@RequestMapping("/sales/AccountManager")
@Api(description = "Exposes APIs to work with AccountManager resource.", value = "AccountManagerController")
public class AccountManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagerController.class);

    @Autowired
    @Qualifier("sales.AccountManagerService")
    private AccountManagerService accountManagerService;

    /**
     * @deprecated Use {@link #findAccountManagers(String, Pageable)} instead.
     */
    @Deprecated
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of AccountManager instances matching the search criteria.")
    public Page<AccountManager> findAccountManagers(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AccountManagers list");
        return accountManagerService.findAll(queryFilters, pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of AccountManager instances matching the search criteria.")
    public Page<AccountManager> findAccountManagers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AccountManagers list");
        return accountManagerService.findAll(query, pageable);
    }

    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @ApiOperation(value = "Returns downloadable file for the data.")
    public Downloadable exportAccountManagers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return accountManagerService.export(exportType, query, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AccountManagerService instance
	 */
    protected void setAccountManagerService(AccountManagerService service) {
        this.accountManagerService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new AccountManager instance.")
    public AccountManager createAccountManager(@RequestBody AccountManager accountmanager) {
        LOGGER.debug("Create AccountManager with information: {}", accountmanager);
        accountmanager = accountManagerService.create(accountmanager);
        LOGGER.debug("Created AccountManager with information: {}", accountmanager);
        return accountmanager;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of AccountManager instances.")
    public Long countAccountManagers(@RequestParam(value = "q", required = false) @ApiParam(value = "conditions to filter the results") String query) {
        LOGGER.debug("counting AccountManagers");
        return accountManagerService.count(query);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the AccountManager instance associated with the given id.")
    public AccountManager getAccountManager(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting AccountManager with id: {}", id);
        AccountManager foundAccountManager = accountManagerService.getById(id);
        LOGGER.debug("AccountManager details with id: {}", foundAccountManager);
        return foundAccountManager;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the AccountManager instance associated with the given id.")
    public AccountManager editAccountManager(@PathVariable(value = "id") Integer id, @RequestBody AccountManager accountmanager) throws EntityNotFoundException {
        LOGGER.debug("Editing AccountManager with id: {}", accountmanager.getId());
        accountmanager.setId(id);
        accountmanager = accountManagerService.update(accountmanager);
        LOGGER.debug("AccountManager details with id: {}", accountmanager);
        return accountmanager;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the AccountManager instance associated with the given id.")
    public boolean deleteAccountManager(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting AccountManager with id: {}", id);
        AccountManager deletedAccountManager = accountManagerService.delete(id);
        return deletedAccountManager != null;
    }
}