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

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {
    private String id;
    private String contactId;
    private String accountId;
    private String stateProvinceId;
    private String countryId;
    private String code;
    private String description;
    private String street;
    private String postalCode;
    private String city;
    private String note;
    private Map<String, Object> fields;
    private List<String> phones;
    private List<String> mails;
    private boolean isDeleted;
}
