package com.springapp.mvc.service.impl;

import com.springapp.mvc.service.CryptoService;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
@Service
public class CryptoServiceImpl implements CryptoService {

    private Logger LOG = LoggerFactory.getLogger(CryptoServiceImpl.class);

    private static Cipher eCipher;

    private static Cipher dCipher;

    private static SecretKey key;

    private String keyString = "vector@cse";

    @PostConstruct
    public void init() {
        try {

            byte[] securityKey = keyString.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            securityKey = sha.digest(securityKey);
            securityKey = Arrays.copyOf(securityKey, 16);

            key = new SecretKeySpec(securityKey, "AES");
            eCipher = Cipher.getInstance("AES");
            dCipher = Cipher.getInstance("AES");

            eCipher.init(Cipher.ENCRYPT_MODE, key);
            dCipher.init(Cipher.DECRYPT_MODE, key);

        } catch (NoSuchAlgorithmException e) {
            LOG.error("Key initialization error occurred", e);
        } catch (NoSuchPaddingException e) {
            LOG.error("Key initialization error occurred", e);
        } catch (InvalidKeyException e) {
            LOG.error("Key initialization error occurred", e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Key initialization error occurred", e);
        }
    }

    @Override
    public String encrypt(String plainText) {

        try {
            byte[] utf8 = plainText.getBytes("UTF8");
            byte[] enc = eCipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);

        } catch (Exception e) {
            LOG.error("Password encryption error occurred", e);
        }

        return null;
    }

    @Override
    public String decrypt(String cipherText) {

        LOG.info("Password decryption started");
        try {

            byte[] dec = BASE64DecoderStream.decode(cipherText.getBytes());
            byte[] utf8 = dCipher.doFinal(dec);
            return new String(utf8, "UTF8");

        } catch (Exception e) {
            LOG.error("Password decryption error occurred", e);
        }

        return null;
    }
}
