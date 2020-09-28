package com.acme.otorongo.domain.model.operations;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "simple_rate_operation_id")
@Table(name = "simple_rate_operations")
public class SimpleRateOperation extends Operation {
    @Column(name = "capital")
    private Float capital;
    @Column(name = "future")
    private Float future;
}
