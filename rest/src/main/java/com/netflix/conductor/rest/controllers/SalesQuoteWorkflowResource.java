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

import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import com.netflix.conductor.rest.dto.SalesQuoteHeaderDTO;
import com.netflix.conductor.rest.dto.SalesQuoteLineDTO;
import com.netflix.conductor.rest.dto.DeleteManyDTO;
import com.netflix.conductor.service.WorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static com.netflix.conductor.rest.config.RequestMappingConstants.SALES_QUOTE_MANAGER_WORKFLOW;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(SALES_QUOTE_MANAGER_WORKFLOW)
public class SalesQuoteWorkflowResource {
    private final WorkflowService workflowService;

    public SalesQuoteWorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }
    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteHeaders")
    @Operation(
            summary =
                    "Start a flow that allows users to create a sales quote header.")
    public Map<String, String> startCreateSalesQuoteHeaderWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody SalesQuoteHeaderDTO salesQuoteHeader) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("AddSalesQuoteHeader");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteHeader", salesQuoteHeader,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteLines")
    @Operation(
            summary =
                    "Start a flow that allows users to create a sales quote line.")
    public Map<String, String> startCreateSalesQuoteLineWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody SalesQuoteLineDTO salesQuoteLine) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("AddSalesQuoteLine");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteLine", salesQuoteLine,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    
    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteHeaders")
    @Operation(
            summary =
                    "Start a flow that allows users to update a sales quote header.")
    public Map<String, String> startUpdateSalesQuoteHeaderWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody SalesQuoteHeaderDTO salesQuoteHeader) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("UpdateSalesQuoteHeader");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteHeader", salesQuoteHeader,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteLines")
    @Operation(
            summary =
                    "Start a flow that allows users to update a sales quote line.")
    public Map<String, String> startUpdateSalesQuoteLineWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody SalesQuoteLineDTO salesQuoteLine) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("UpdateSalesQuoteLine");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteLine", salesQuoteLine,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteHeaders/{salesQuoteHeaderId}")
    @Operation(
            summary =
                    "Start a flow that allows users to delete a sales quote header.")
    public Map<String, String> startDeleteSalesQuoteHeaderWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @PathVariable("salesQuoteHeaderId") String salesQuoteHeaderId) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteSalesQuoteHeader");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteHeaderId", salesQuoteHeaderId,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteLines/{salesQuoteLineId}")
    @Operation(
            summary =
                    "Start a flow that allows users to delete a sales quote line.")
    public Map<String, String> startDeleteSalesQuoteLineWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @PathVariable("salesQuoteLineId") String salesQuoteLineId) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteSalesQuoteLine");
        workflowRequest.setInput(
            Map.of(
                "salesQuoteLineId", salesQuoteLineId,
                "userId", userId,
                "bearer", bearer
            )
        );
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteHeaders/Delete")
    @Operation(
            summary =
                    "Start a flow that allows users to remove many sales quote headers to their dashboard.")
    public Map<String, String> startRemoveManySalesQuoteHeadersWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody DeleteManyDTO dto) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteManySalesQuoteHeader");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "salesQuoteHeaderIds", dto.getToBeDeleted(),
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, path = "/SalesQuoteLines/Delete")
    @Operation(
            summary =
                    "Start a flow that allows users to remove many sales quote lines to their dashboard.")
    public Map<String, String> startRemoveManySalesQuoteLinesWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody DeleteManyDTO dto) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteManySalesQuoteLine");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "salesQuoteLineIds", dto.getToBeDeleted(),
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }
}