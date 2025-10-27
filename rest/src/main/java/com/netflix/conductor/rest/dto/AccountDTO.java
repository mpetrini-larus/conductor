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

import java.util.HashSet;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountDTO {
    private String id;
    private String accountTypeId;
    private String shipmentMethodId;
    private String transportMethodId;
    private String currencyId;
    private String paymentTypeId;
    private String discountClassId;
    private String languageId;
    private String firstName;
    private String lastName;
    private String alias;
    private String vatNumber;
    private String fiscalCode;
    private String code;
    private String description;
    private String ateco;
    private String ownerId;
    private Map<String, Object> fields;
    private boolean IsDeleted;

    private HashSet<AddressDTO> Addresses;
    private HashSet<ContactDTO> Contacts;
}
