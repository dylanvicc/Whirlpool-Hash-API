package com.vicc.springboot.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vicc.springboot.hash.HashAlgorithm;
import com.vicc.springboot.hash.impl.SHA1HashAlgorithm;
import com.vicc.springboot.hash.impl.SHA256HashAlgorithm;
import com.vicc.springboot.hash.impl.WhirlpoolHashAlgorithm;

@RestController
public class HashAlgorithmController {

  public enum HashingAlgorithms {

    WHIRLPOOL("WHIRLPOOL", new WhirlpoolHashAlgorithm()), SHA256("SHA256", new SHA256HashAlgorithm()),
    SHA1("SHA1", new SHA1HashAlgorithm());

    private String name;
    private HashAlgorithm algorithm;

    HashingAlgorithms(String name, HashAlgorithm algorithm) {
      this.name = name;
      this.algorithm = algorithm;
    }

    public static String hash(String value, String name) throws NoSuchAlgorithmException {
      for (HashingAlgorithms it : HashingAlgorithms.values()) {
        if (it.name.equalsIgnoreCase(name))
          return it.algorithm.hash(value);
      }
      throw new NoSuchAlgorithmException();
    }
  }

  @RequestMapping("/")
  public String hash(@RequestParam("value") String value, @RequestParam("name") String name) {
    if (value.equals(""))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid hash value parameter");
    try {
      return HashingAlgorithms.hash(value, name);
    } catch (NoSuchAlgorithmException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid hash algorithm parameter");
    }
  }
}