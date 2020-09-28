package com.acme.otorongo.resource.promissories_resource;

import lombok.Data;
import java.util.Date;

@Data
public class PromissoryNoteResource {
    private Long id;
    private Float tcea;
    private Date signDate;
    private Date paymentDate;
    private Float initialWithholding;
    private Float finalWithholding;
    private String rateName;
    private Long operationId;
}
