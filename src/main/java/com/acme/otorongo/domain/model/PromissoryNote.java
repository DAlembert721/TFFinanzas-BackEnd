package com.acme.otorongo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "promissory_notes")
@Data
public class PromissoryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rate_id", nullable = false)
    @JsonIgnore
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quotation_id", nullable = false)
    @JsonIgnore
    private Quotation quotation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    @JsonIgnore
    private Currency currency;

}
