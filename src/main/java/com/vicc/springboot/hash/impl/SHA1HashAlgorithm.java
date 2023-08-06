package com.vicc.springboot.hash.impl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.vicc.springboot.hash.HashAlgorithm;

public class SHA1HashAlgorithm implements HashAlgorithm {

  @Override
  public String hash(String value) throws NoSuchAlgorithmException {

    final byte[] bytes = MessageDigest.getInstance("SHA-1").digest(value.getBytes(StandardCharsets.UTF_8));
    final StringBuilder hash = new StringBuilder(new BigInteger(1, bytes).toString(16));

    while (hash.length() < 32)
      hash.insert(0, '0');

    return hash.toString().toUpperCase();
  }
}
