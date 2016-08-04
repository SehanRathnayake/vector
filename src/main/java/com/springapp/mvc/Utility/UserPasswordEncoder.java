package com.springapp.mvc.utility;

import com.springapp.mvc.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
public class UserPasswordEncoder implements PasswordEncoder {

    @Autowired
    private CryptoService cryptoService;

    @Override
    public String encode(CharSequence rawPassword) {

        String encripted = cryptoService.encrypt(rawPassword.toString());
        return encripted;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (this.encode(rawPassword).equals(encodedPassword)) {
            return true;
        }
        return false;
    }
}
