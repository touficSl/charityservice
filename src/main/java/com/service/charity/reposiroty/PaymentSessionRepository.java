package com.service.charity.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.charity.model.PaymentSession;

public interface PaymentSessionRepository extends JpaRepository<PaymentSession, String> {
	
}
