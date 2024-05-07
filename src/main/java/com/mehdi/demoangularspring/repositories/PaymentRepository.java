package com.mehdi.demoangularspring.repositories;

import com.mehdi.demoangularspring.entities.Payment;
import com.mehdi.demoangularspring.entities.PaymentStatus;
import com.mehdi.demoangularspring.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
