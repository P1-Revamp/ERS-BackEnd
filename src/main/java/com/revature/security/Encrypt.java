package com.revature.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encrypt {

//	public static String encryptPassword(String unencryptedPassword) {
//		
//		StringBuilder encryptedPassword = null;
//		
//		String algorithm = "SHA";
//
//        byte[] plainText = unencryptedPassword.getBytes();
//
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
//
//            messageDigest.reset();
//            messageDigest.update(plainText);
//            byte[] encodedPassword = messageDigest.digest();
//
//            encryptedPassword = new StringBuilder();
//            for (int i = 0; i < encodedPassword.length; i++) {
//                if ((encodedPassword[i] & 0xff) < 0x10) {
//                	encryptedPassword.append("0");
//                }
//
//                encryptedPassword.append(Long.toString(encodedPassword[i] & 0xff, 16));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        if (encryptedPassword != null) {
//        	return encryptedPassword.toString();
//        } else {
//        	return null;
//        }
//        
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
}
