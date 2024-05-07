package com.mehdi.demoangularspring.dtos;

import com.mehdi.demoangularspring.entities.PaymentStatus;
import com.mehdi.demoangularspring.entities.PaymentType;
import com.mehdi.demoangularspring.entities.Student;
import lombok.*;


import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}
