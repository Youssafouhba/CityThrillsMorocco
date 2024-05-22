package com.CityThrillsMorocco.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentRequestDto {
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
