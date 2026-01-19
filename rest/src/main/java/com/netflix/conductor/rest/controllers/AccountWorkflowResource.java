/*
 * Copyright 2020 Conductor Authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.rest.controllers;

import java.util.Map;

import com.netflix.conductor.rest.dto.GlobalRequestModelDTO;
import org.springframework.web.bind.annotation.*;

import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import com.netflix.conductor.rest.dto.AccountDTO;
import com.netflix.conductor.rest.dto.DeleteManyDTO;
import com.netflix.conductor.rest.dto.PromoteDTO;
import com.netflix.conductor.service.WorkflowService;

import io.swagger.v3.oas.annotations.Operation;

import static com.netflix.conductor.rest.config.RequestMappingConstants.CUSTOMER_MANAGER_WORKFLOW;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(CUSTOMER_MANAGER_WORKFLOW)
public class AccountWorkflowResource {

    private final WorkflowService workflowService;

    public AccountWorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/Account")
    @Operation(
            summary =
                    "Start a create customer flow that allows users to create new customer account.")
    public Map<String, String> startCreateAccountWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody AccountDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("CreateCustomer");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "customer", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/Account/Promotes")
    @Operation(
            summary =
                    "Start a promote customer flow that allows users to promote a customer account to a new status.")
    public Map<String, String> startPromoteAccountWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody PromoteDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("PromoteCustomer");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "promoteCustomer", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/Account")
    @Operation(
            summary =
                    "Start a update customer flow that allows users to update a customer account.")
    public Map<String, String> startUpdateAccountWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody AccountDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("UpdateCustomer");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "customer", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/Account/{accountId}")
    @Operation(
            summary =
                    "Start a delete customer flow that allows users to remove a customer account.")
    public Map<String, String> startDeleteAccountWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @PathVariable("accountId") String accountId) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteCustomer");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "customer", accountId,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/Account/Delete")
    @Operation(
            summary =
                    "Start a delete customers flow that allows users to remove customers account.")
    public Map<String, String> startDeleteManyAccountWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody DeleteManyDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteManyCustomer");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "customer", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/Account/DeleteWithFilters")
    @Operation(
            summary =
                    "Start a delete customers flow that allows users to remove customers account.")
    public Map<String, String> startDeleteAccountWithFiltersWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody GlobalRequestModelDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteCustomersWithFilters");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "filter", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }
}
