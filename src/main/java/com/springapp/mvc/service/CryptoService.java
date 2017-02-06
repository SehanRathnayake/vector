package com.springapp.mvc.service;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
public interface  CryptoService {

    public String encrypt(String plainText);

    public String decrypt(String cipherText);
}

