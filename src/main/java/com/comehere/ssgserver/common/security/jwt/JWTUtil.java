package com.comehere.ssgserver.common.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JWTUtil {

	private final SecretKey secretkey;
	private final long expiredMs = 1000 * 60 * 60; // 1시간

	// jwt signature 생성
	public JWTUtil(@Value("${jwt.secret}") String secret) {
		this.secretkey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
				Jwts.SIG.HS256.key().build().getAlgorithm());
	}

	public UUID getUserUuid(String token) {
		String uuidString = Jwts.parser()
				.verifyWith(secretkey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.get("userUuid", String.class);
		return UUID.fromString(uuidString);
	}

	public UUID getUuidByAuthorization(String authorization) {
		return getUserUuid(authorization.substring(7));
	}

	public String getRole(String token) {
		return Jwts.parser()
				.verifyWith(secretkey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.get("role", String.class);
	}

	public Boolean isExpired(String token) {
		return Jwts.parser()
				.verifyWith(secretkey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getExpiration()
				.before(new Date());
	}

	public String createJwt(UUID userUuid, String role) {

		return Jwts.builder()
				.claim("userUuid", userUuid.toString())
				.claim("role", role)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(secretkey)
				.compact();
	}
}
