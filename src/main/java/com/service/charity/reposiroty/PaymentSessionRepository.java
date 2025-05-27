package com.service.charity.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.charity.model.PaymentSession;

@Repository
public interface PaymentSessionRepository extends JpaRepository<PaymentSession, String> {
	
}
