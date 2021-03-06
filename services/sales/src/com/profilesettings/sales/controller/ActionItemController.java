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
import com.profilesettings.sales.ActionItem;
import com.profilesettings.sales.service.ActionItemService;
import com.wordnik.swagger.annotations.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

/**
 * Controller object for domain model class ActionItem.
 * @see ActionItem
 */
@RestController("sales.ActionItemController")
@RequestMapping("/sales/ActionItem")
@Api(description = "Exposes APIs to work with ActionItem resource.", value = "ActionItemController")
public class ActionItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionItemController.class);

    @Autowired
    @Qualifier("sales.ActionItemService")
    private ActionItemService actionItemService;

    /**
     * @deprecated Use {@link #findActionItems(String, Pageable)} instead.
     */
    @Deprecated
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of ActionItem instances matching the search criteria.")
    public Page<ActionItem> findActionItems(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering ActionItems list");
        return actionItemService.findAll(queryFilters, pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of ActionItem instances matching the search criteria.")
    public Page<ActionItem> findActionItems(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ActionItems list");
        return actionItemService.findAll(query, pageable);
    }

    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @ApiOperation(value = "Returns downloadable file for the data.")
    public Downloadable exportActionItems(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return actionItemService.export(exportType, query, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ActionItemService instance
	 */
    protected void setActionItemService(ActionItemService service) {
        this.actionItemService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new ActionItem instance.")
    public ActionItem createActionItem(@RequestBody ActionItem actionitem) {
        LOGGER.debug("Create ActionItem with information: {}", actionitem);
        actionitem = actionItemService.create(actionitem);
        LOGGER.debug("Created ActionItem with information: {}", actionitem);
        return actionitem;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of ActionItem instances.")
    public Long countActionItems(@RequestParam(value = "q", required = false) @ApiParam(value = "conditions to filter the results") String query) {
        LOGGER.debug("counting ActionItems");
        return actionItemService.count(query);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the ActionItem instance associated with the given id.")
    public ActionItem getActionItem(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting ActionItem with id: {}", id);
        ActionItem foundActionItem = actionItemService.getById(id);
        LOGGER.debug("ActionItem details with id: {}", foundActionItem);
        return foundActionItem;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the ActionItem instance associated with the given id.")
    public ActionItem editActionItem(@PathVariable(value = "id") Integer id, @RequestBody ActionItem actionitem) throws EntityNotFoundException {
        LOGGER.debug("Editing ActionItem with id: {}", actionitem.getId());
        actionitem.setId(id);
        actionitem = actionItemService.update(actionitem);
        LOGGER.debug("ActionItem details with id: {}", actionitem);
        return actionitem;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the ActionItem instance associated with the given id.")
    public boolean deleteActionItem(@PathVariable(value = "id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting ActionItem with id: {}", id);
        ActionItem deletedActionItem = actionItemService.delete(id);
        return deletedActionItem != null;
    }
}
