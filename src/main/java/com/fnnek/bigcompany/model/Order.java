package com.fnnek.bigcompany.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Order(long id,
    String name,
    BigDecimal amount,
    LocalDateTime date,
    Company company,
    Client client) {
    
}
