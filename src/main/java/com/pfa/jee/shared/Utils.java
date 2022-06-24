package com.pfa.jee.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopkrstuvwxyz";

	public String generateStringId(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
		}
		return new String(returnValue);
	}
}