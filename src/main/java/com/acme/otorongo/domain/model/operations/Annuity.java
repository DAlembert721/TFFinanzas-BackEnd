package com.acme.otorongo.domain.model.operations;

import com.acme.otorongo.domain.model.expenses.Quotation;
import com.acme.otorongo.domain.model.expenses.Rate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "annuity_id")
@Table(name = "annuities")
public class Annuity extends Operation{
    @Column(name = "initial_quota")
    private Float initialQuota;
    @Column(name = "capital")
    private Float capital;
    @Column(name = "future")
    private Float future;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rate_id", nullable = false)
    @JsonIgnore
    private Rate rate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quotation_id", nullable = false)
    @JsonIgnore
    private Quotation quotation;
}
