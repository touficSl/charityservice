package com.service.charity.builder.response;

import java.math.BigDecimal;

import com.service.charity.model.Project;

public interface ProjectCharityAmount {
    Project getProject();
    BigDecimal getTotalAmount();
}