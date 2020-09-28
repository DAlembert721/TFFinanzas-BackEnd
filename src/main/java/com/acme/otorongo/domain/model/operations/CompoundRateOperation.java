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
@PrimaryKeyJoinColumn(name = "compound_rate_operation_id")
@Table(name = "compound_rate_operations")
public class CompoundRateOperation extends Operation{
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
