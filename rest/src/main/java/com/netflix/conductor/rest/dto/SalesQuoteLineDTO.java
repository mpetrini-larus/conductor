package com.netflix.conductor.rest.dto;

import java.util.HashSet;
import java.util.Map;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SalesQuoteLineDTO {
    private String Id;
    private String TenantId;
    private String QuoteHeaderId;
    private String ItemId;
    private float LineNumber;
    private String Description;
    private String UomId;
    private float Quantity;
    private float Price;
    private Map<String, Object> Discounts;
    private float TotalAmount;
    private float OutstandingAmount;
    private float OutstandingQty;
    private Instant DueDate;
    private String VatId;
    private String Note;
    private String ExternalSystemId;
    private boolean IsDeleted;
}
