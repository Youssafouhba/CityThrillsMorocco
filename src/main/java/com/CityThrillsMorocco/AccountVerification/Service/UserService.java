package com.CityThrillsMorocco.AccountVerification.Service;

import com.CityThrillsMorocco.Client.Model.Client;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(Client user);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}