package com.netflix.conductor.rest.dto;

import java.util.HashSet;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SalesQuoteHeaderDTO {
    private String Id ;// obbligatorio
    private String TenantId ;// obbligatorio
    private String CompanyId ;// obbligatorio
    private String TypeId ; // obbligatorio
    private String StageId ;// obbligatorio
    private String OwnerUserId ;// obbligatorio
    private int  QuoteNumber ;// obbligatorio
    private String QuoteCode ;// obbligatorio
    private Instant  QuoteDate ;// obbligatorio
    private String QuoteObject ;
    private String BillToAccountId ; //Attualmente uguale a ShipToAccountId (mettendo il cliente) // obbligatorio
    private String ShipToAccountId ; //Attualmente uguale a BillToAccountId (mettendo il cliente) // obbligatorio
    private String BillToAddressId ; //Attualmente uguale a ShipToAddressId (mettendo il cliente) // obbligatorio
    private String ShipToAddressId ; //Attualmente uguale a BillToAddressId (mettendo il cliente) // obbligatorio
    private String ShipToAddress ;
    private HashSet<String>  Contacts ; // Id di contatti, combo multipla
    private String YourReference ;
    private String OurReference ;
    private String PaymentTermId ;
    private String ShipmentMethodId ;
    private String TranspoortMethodId ;
    private Instant  DueDate ;
    private Instant  ExpirationDate ; // data scadenza offerta // obbligatorio
    private HashSet<String>  SalesPeople ;
    private String PriceListId ;
    private String CurrencyId ;
    private float  CurrencyFactor ;
    private String LocationId ;
    private boolean IsFromExternalSystem ;
    private String ExternalSystemId ;
    private boolean IsDeleted ;
}
