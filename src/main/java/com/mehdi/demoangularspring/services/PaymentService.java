package com.mehdi.demoangularspring.services;

import com.mehdi.demoangularspring.entities.Payment;
import com.mehdi.demoangularspring.entities.PaymentStatus;
import com.mehdi.demoangularspring.entities.PaymentType;
import com.mehdi.demoangularspring.entities.Student;
import com.mehdi.demoangularspring.repositories.PaymentRepository;
import com.mehdi.demoangularspring.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType type, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"emsi-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"emsi-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date)
                .type(type)
                .student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);
    }
    public Payment updatePaymentStatus( PaymentStatus status, Long id){
        Payment payment = paymentRepository.findById(id).orElseThrow();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
