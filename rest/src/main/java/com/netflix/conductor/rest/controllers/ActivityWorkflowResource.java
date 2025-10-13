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

import org.springframework.web.bind.annotation.*;

import com.netflix.conductor.common.metadata.workflow.StartWorkflowRequest;
import com.netflix.conductor.rest.dto.UpsertActivityRequestDTO;
import com.netflix.conductor.service.WorkflowService;

import io.swagger.v3.oas.annotations.Operation;

import static com.netflix.conductor.rest.config.RequestMappingConstants.ACTIVITY_MANAGER_WORKFLOW;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ACTIVITY_MANAGER_WORKFLOW)
public class ActivityWorkflowResource {

    private final WorkflowService workflowService;

    public ActivityWorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/CalendarActivities/Upsert")
    @Operation(
            summary =
                    "Start a bookmarking flow that allows users to add bookmarks to their dashboard.")
    public Map<String, String> startAddBookmarkWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody UpsertActivityRequestDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("UpsertActivity");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "activity", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @DeleteMapping(produces = APPLICATION_JSON_VALUE, path = "/CalendarActivities/{activityId}")
    @Operation(
            summary =
                    "Start a bookmarking flow that allows users to remove bookmarks to their dashboard.")
    public Map<String, String> startRemoveBookmarkWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @PathVariable("activityId") String activityId) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("DeleteActivity");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "activityId", activityId,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }
}
