/*
 * Copyright 2025 Conductor Authors.
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
package com.netflix.conductor.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemMasterDTO {
    private String id;
    public String Code;
    public String Description;
    public String ManufacturerItemCode;
    public String Alias;
    public String StatusId;
    public String UomId;
    public String TypeId;
    public String BrandId;
    public String LineId;
    public String CategoryId;
    public String SubCategoryId;
    public String Note;
    // public required JsonDocument? Fields { get; set; } = JsonDocument.Parse("{}");
    public boolean IsBlocked;
    public boolean IsDeleted;
}
