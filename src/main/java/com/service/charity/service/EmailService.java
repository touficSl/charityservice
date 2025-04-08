package com.service.charity.service;

import com.service.charity.builder.request.EmailDetailsRq;

public interface EmailService {

	boolean sendSimpleMail(EmailDetailsRq details);

}
