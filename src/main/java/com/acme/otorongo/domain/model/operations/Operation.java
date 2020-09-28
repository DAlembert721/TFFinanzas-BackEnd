package com.acme.otorongo.domain.model.operations;


import com.acme.otorongo.domain.model.expenses.Currency;
import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import com.acme.otorongo.domain.model.users.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "operations")
public class Operation {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Float rateValue;

    @NotNull
    private Date discountDate;

    @NotNull
    private Date expireDate;

    @NotNull
    private Boolean state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    private PromissoryNote promissoryNote;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    @JsonIgnore
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;
}
