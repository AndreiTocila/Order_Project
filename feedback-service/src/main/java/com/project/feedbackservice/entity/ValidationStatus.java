package com.project.feedbackservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "validation_status")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ValidationStatus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String orderId;

    @Column
    private Boolean stockValidation;

    @Column
    private Boolean cardValidation;
}
