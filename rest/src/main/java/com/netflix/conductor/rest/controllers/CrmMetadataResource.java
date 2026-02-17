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

import com.netflix.conductor.common.metadata.workflow.WorkflowDef;
import com.netflix.conductor.core.exception.NotFoundException;
import com.netflix.conductor.rest.dto.WorkflowSummaryDTO;
import com.netflix.conductor.service.MetadataService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import static com.netflix.conductor.rest.config.RequestMappingConstants.CRM_WORKFLOW_METADATA;

@RestController
@RequestMapping(value = CRM_WORKFLOW_METADATA)
public class CrmMetadataResource {

    private final MetadataService metadataService;

    public CrmMetadataResource(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @Operation(summary = "Retrieves workflow definition along with blueprint")
    @GetMapping("/workflow/{name}")
    public WorkflowDef get(
            @PathVariable("name") String name,
            @RequestParam(value = "version", required = false) Integer version) {
        var outcome = metadataService.getWorkflowDef(name, version);
        var metadata = outcome.getMetadata();
        boolean crmCheck = metadata.containsKey("type") &&
                (StringUtils.equalsAnyIgnoreCase(metadata.get("type").toString(), "BUSINESS_LOGIC", "PALETTE"));
        if(!crmCheck) {throw new NotFoundException("No such workflow found by name: %s, version: %d", name, version);}
        return outcome;
    }

    @Operation(summary = "Returns latest versions workflow names and descriptions only (no definition bodies)")
    @GetMapping("/workflow")
    public Set<WorkflowSummaryDTO> getWorkflowNamesAndVersions() {
        return metadataService.getWorkflowDefsLatestVersions()
                .stream()
                .filter(wfdef -> {
                    var metadata = wfdef.getMetadata();
                    return metadata.containsKey("type") &&
                            StringUtils.equalsIgnoreCase(metadata.get("type").toString(),"BUSINESS_LOGIC");
                })
                .map(wf -> {
                    var wfSummary = new WorkflowSummaryDTO();
                    wfSummary.setName(wf.getName());
                    wfSummary.setDescription(wf.getDescription());
                    wfSummary.setVersion(wf.getVersion());
                    wfSummary.setCreateTime(wfSummary.getCreateTime());
                    return wfSummary;
                }).collect(Collectors.toSet());
    }

    @Operation(summary = "Returns latest versions of available workflow palette, name and descriptions only (no definition bodies)")
    @GetMapping("/workflow/palette")
    public Set<WorkflowSummaryDTO> getWorkflowPaletteNamesAndVersions() {
        return  metadataService.getWorkflowDefsLatestVersions()
                .stream()
                .filter(wfdef -> {
                    var metadata = wfdef.getMetadata();
                    return metadata.containsKey("type") &&
                            StringUtils.equalsIgnoreCase(metadata.get("type").toString(),"PALETTE");
                })
                .map(wf -> {
                    var wfSummary = new WorkflowSummaryDTO();
                    wfSummary.setName(wf.getName());
                    wfSummary.setDescription(wf.getDescription());
                    wfSummary.setVersion(wf.getVersion());
                    wfSummary.setCreateTime(wfSummary.getCreateTime());
                    return wfSummary;
                }).collect(Collectors.toSet());
    }

}
