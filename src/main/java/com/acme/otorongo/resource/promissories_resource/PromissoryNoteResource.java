package com.acme.otorongo.resource.promissories_resource;

import lombok.Data;
import java.util.Date;

@Data
public class PromissoryNoteResource {
    private Long id;
    private Float tcea;
    private Float nominalValue;
    private Long time;
    private Float rateValue;
    private Date signDate;
    private Date discountDate;
    private Date expireDate;
    private Date paymentDate;
    private Float initialWithholding;
    private Float finalWithholding;
    private Boolean state;
    private String clientName;
    private String rateName;
    private String quotationName;
    private String currencyName;
}
