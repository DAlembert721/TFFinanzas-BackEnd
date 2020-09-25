package com.acme.otorongo.resource.save_promissories_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SavePromissoryNoteResource {
    @NotNull
    private Float tcea;
    @NotNull
    private Float nominalValue;
    @NotNull
    private Long time;
    @NotNull
    private Float rateValue;
    @NotNull
    private Date signDate;
    @NotNull
    private Date discountDate;
    @NotNull
    private Date expireDate;
    @NotNull
    private Date paymentDate;
    @NotNull
    private Float initialWithholding;
    @NotNull
    private Float finalWithholding;
    @NotNull
    private Boolean state;
}
