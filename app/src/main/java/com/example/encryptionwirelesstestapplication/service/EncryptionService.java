package com.example.encryptionwirelesstestapplication.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface EncryptionService {

    String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
