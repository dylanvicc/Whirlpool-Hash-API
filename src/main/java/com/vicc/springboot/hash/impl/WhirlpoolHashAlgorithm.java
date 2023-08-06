package com.vicc.springboot.hash.impl;

import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.bouncycastle.util.encoders.Hex;

import com.vicc.springboot.hash.HashAlgorithm;

public class WhirlpoolHashAlgorithm implements HashAlgorithm {

  @Override
  public String hash(String value) {

    final WhirlpoolDigest digest = new WhirlpoolDigest();
    digest.reset();

    final byte[] bytes = value.getBytes();
    digest.update(bytes, 0, bytes.length);

    final byte[] hash = new byte[digest.getDigestSize()];
    digest.doFinal(hash, 0);

    return Hex.toHexString(hash).toUpperCase();
  }
}