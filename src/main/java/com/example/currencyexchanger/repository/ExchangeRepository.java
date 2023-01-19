package com.example.currencyexchanger.repository;

import com.example.currencyexchanger.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ExchangeRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findByClientName(String clientName, Pageable pageable);

    Page<Operation> findByAmount(Double amount, Pageable pageable);

    Page<Operation> findByDate(LocalDate date, Pageable pageable);
}
