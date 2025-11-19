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
import com.netflix.conductor.service.WorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.netflix.conductor.rest.config.RequestMappingConstants.ITEM_MANAGER_WORKFLOW;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ITEM_MANAGER_WORKFLOW)
public class ItemWorkflowResource {

    private final WorkflowService workflowService;

    public ItemWorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/{itemId}")
    @Operation(
            summary =
                    "Start a flow that allows users to remove item to their dashboard.")
    public Map<String, String> startRemoveItemWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @PathVariable("activityId") String itemId) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteItem");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "item", itemId,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }
}
