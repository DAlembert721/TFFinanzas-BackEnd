package com.acme.otorongo.domain.model.promissories;

import com.acme.otorongo.domain.model.expenses.Rate;
import com.acme.otorongo.domain.model.operations.Operation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Date signDate;

    @NotNull
    private Date paymentDate;

    @NotNull
    private Float initialWithholding;

    @NotNull
    private Float finalWithholding;

    @OneToOne(mappedBy = "promissoryNote", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Operation operation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rate_id", nullable = false)
    @JsonIgnore
    private Rate rate;

    @OneToMany(mappedBy = "promissoryNote")
    private Set<PromissoryNoteInitialCost> promissoryNoteInitialCosts = new HashSet<>();

    @OneToMany(mappedBy = "promissoryNote")
    private Set<PromissoryNoteFinalCost> promissoryNoteFinalCosts = new HashSet<>();
}
