package com.vicc.springboot.hash;

import java.security.NoSuchAlgorithmException;

public interface HashAlgorithm {

	String hash(String value) throws NoSuchAlgorithmException;
}
