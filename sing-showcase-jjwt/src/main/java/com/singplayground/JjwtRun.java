package com.singplayground;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class JjwtRun {

	private static String secretKey = "Test*Secret#Key!1@2016#3$4%Sing";
	private static String username = "username1";
	private static String issuer = "UI server";

	public static void main(String[] args) {

		sample1();
		sample1b();
		sample2();
		sample3();
		sample4();

	}

	public static boolean sample1() {

		System.out.println("========== start sample 1 ===============");
		System.out.println("========== set the claim ===============");

		String decodeUsername = "";
		//String decodeIssuer = "";

		JwtBuilder jwtBuilder = Jwts.builder();

		jwtBuilder.setSubject(username);
		String token1 = jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		System.out.println("this is token 1: " + token1);
		decodeUsername = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().getSubject();

		//decodeIssuer = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().getIssuer();
		//System.out.println(decodeIssuer);
		System.out.println("Signature : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getSignature());
		System.out.println("this is username : " + decodeUsername);
		System.out.println("=========== end of sample 1");
		return true;

	}

	public static boolean sample1b() {

		System.out.println("========== start sample 1 ===============");
		System.out.println("========== set the claim ===============");

		String decodeUsername = "";
		String decodeIssuer = "";

		JwtBuilder jwtBuilder = Jwts.builder();

		jwtBuilder.setSubject(username).setIssuer(issuer);
		String token1 = jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		System.out.println("this is token 1: " + token1);
		decodeUsername = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().getSubject();

		decodeIssuer = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().getIssuer();
		System.out.println(decodeIssuer);
		System.out.println("Signature : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getSignature());
		System.out.println("this is username : " + decodeUsername);
		System.out.println("=========== end of sample 1");
		return true;

	}

	public static boolean sample2() {

		System.out.println("========== start sample 2 ===============");
		System.out.println("========== set the claim ===============");

		String decodeUsername = "";
		String decodeIssuer = "";

		JwtBuilder jwtBuilder = Jwts.builder();

		jwtBuilder.setSubject(username).setIssuer(issuer);

		Map claims = new HashMap();
		claims.put("username", "testAcc");
		claims.put("type", "admin");
		jwtBuilder.setClaims(claims);

		String token1 = jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		System.out.println("this is token : " + token1);

		System.out.println("this is body (username) : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().get("username"));
		System.out.println("this is body (type): " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().get("type"));
		System.out.println("this is body (full): " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody());

		return true;

	}

	public static boolean sample3() {

		System.out.println("========== start sample 3 ===============");
		System.out.println("========== use the map set the custom claim ===============");

		String decodeUsername = "";
		String decodeIssuer = "";

		Payload payload = new Payload();
		Payload decodepayload = new Payload();
		payload.setName("TestAcc");
		payload.setType("Admin");

		JwtBuilder jwtBuilder = Jwts.builder();

		jwtBuilder.setSubject(username).setIssuer(issuer);

		Map claims = new HashMap();
		claims.put("userDetail", payload);
		jwtBuilder.setClaims(claims);

		String token1 = jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		System.out.println("this is token : " + token1);

		System.out.println("this is body (username) : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().get("userDetail"));
		System.out.println("===== step 1 ");
		Claims decodeclaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody();
		System.out.println("===== step 2 ");
		System.out.println("decodeclaims : " + decodeclaims);
		System.out.println(decodeclaims.get("userDetail"));

		Map decodeclaimsMap = decodeclaims.get("userDetail", Map.class);
		System.out.println(decodeclaimsMap.get("name"));
		System.out.println(decodeclaimsMap.get("type"));

		System.out.println("end of the sample");

		return true;

	}

	public static boolean sample4() {

		System.out.println("========== start sample 3 ===============");
		System.out.println("========== use the map set the claim ===============");

		String decodeUsername = "";
		String decodeIssuer = "";

		Payload payload = new Payload();
		Payload decodepayload = new Payload();
		payload.setName("TestAcc");
		payload.setType("Admin");

		JwtBuilder jwtBuilder = Jwts.builder();

		Map claims = new HashMap();

		claims.put("userDetail", payload);
		jwtBuilder.setSubject(username).setIssuer(issuer).setClaims(claims);
		jwtBuilder.setSubject(username).setIssuer(issuer).setClaims(claims).signWith(SignatureAlgorithm.HS256, secretKey).compact();
		//jwtBuilder.setClaims(claims);

		String token1 = jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		System.out.println("this is token : " + token1);

		System.out.println("this is body (username) : " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody().get("userDetail"));
		System.out.println("===== step 1 ");
		Claims decodeclaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody();

		System.out.println("getSubject()" + decodeclaims.getSubject());
		System.out.println("getIssuer()" + decodeclaims.getIssuer());
		System.out.println("getAudience()" + decodeclaims.getAudience());
		System.out.println("getAudience()" + decodeclaims.getId());
		System.out.println("getAudience()" + decodeclaims.getIssuedAt());

		System.out.println("===== step 2 ");
		System.out.println("decodeclaims : " + decodeclaims);
		System.out.println(decodeclaims.get("userDetail"));

		Map decodeclaimsMap = decodeclaims.get("userDetail", Map.class);
		System.out.println(decodeclaimsMap.get("name"));
		System.out.println(decodeclaimsMap.get("type"));

		System.out.println("end of the sample");

		return true;

	}

}
