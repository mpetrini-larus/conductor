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
import com.netflix.conductor.rest.dto.PreferenceRequestDTO;
import com.netflix.conductor.service.WorkflowService;
import com.netflix.conductor.service.WorkflowTestService;

import io.swagger.v3.oas.annotations.Operation;

import static com.netflix.conductor.rest.config.RequestMappingConstants.USER_MANAGER_WORKFLOW;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(USER_MANAGER_WORKFLOW)
public class UserWorkflowResource {

    private final WorkflowService workflowService;

    private final WorkflowTestService workflowTestService;

    public UserWorkflowResource(
            WorkflowService workflowService, WorkflowTestService workflowTestService) {
        this.workflowService = workflowService;
        this.workflowTestService = workflowTestService;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/Preferences/addBookmark")
    @Operation(
            summary =
                    "Start a bookmarking flow that allows users to add bookmarks to their dashboard.")
    public Map<String, String> startAddBookmarkWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody PreferenceRequestDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("AddBookmark");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "bookmark", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/Preferences/deleteBookmark")
    @Operation(
            summary =
                    "Start a bookmarking flow that allows users to remove bookmarks to their dashboard.")
    public Map<String, String> startRemoveBookmarkWorkflow(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestHeader(value = "X-User-Id") String userId,
            @RequestBody PreferenceRequestDTO request) {
        StartWorkflowRequest workflowRequest = new StartWorkflowRequest();
        workflowRequest.setName("RemoveBookmark");
        workflowRequest.setInput(
                Map.of(
                        "userId", userId,
                        "bookmark", request,
                        "bearer", bearer));
        return Map.of("operationId", workflowService.startWorkflow(workflowRequest));
    }
}
