package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author Kevin Chen
 */
public class TokenRefresher {
	private static final Logger logger = LoggerFactory.getLogger(TokenRefresher.class);

	private final TokenFetcher tokenFetcher;

	private final ZoneOffset zoneOffset;

	public TokenRefresher(TokenFetcher tokenFetcher, ZoneOffset zoneOffset) {
		this.tokenFetcher = tokenFetcher;
		this.zoneOffset = zoneOffset;
	}

	public TokenResponse fetchToken() {
		TokenResponse tokenResponse = tokenFetcher.fetchToken();
		logger.debug("TokenResponse successful: {}", tokenResponse.isSuccess());
		logger.debug("TokenResponse toString: {}", tokenResponse);

		LocalDateTime expirationDate = getTokenExpirationTime(tokenResponse);
		LocalDateTime now = LocalDateTime.now(zoneOffset);
		logger.debug("Current time is {}", now);
		logger.debug("Token will expire on {}", expirationDate);
		logger.debug(
			"Token expires in {} seconds ({} minutes)",
			now.until(expirationDate, ChronoUnit.SECONDS),
			now.until(expirationDate, ChronoUnit.MINUTES)
		);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			logger.debug("TOKEN SERIALIZED = {}", objectMapper.writeValueAsString(tokenResponse));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return tokenResponse;
	}

	public boolean isTokenExpired(TokenResponse tokenResponse) {
		if (tokenResponse == null) {
			logger.debug("Token not found... fetching one now...");
			return true;
		}

		LocalDateTime now = LocalDateTime.now(zoneOffset);
		LocalDateTime expirationTime = getTokenExpirationTime(tokenResponse);

		if (now.isAfter(expirationTime)) {
			logger.debug("Current time {} is after token expiration time {}", now, expirationTime);
			return true;
		}

		return false;
	}

	protected LocalDateTime getTokenExpirationTime(TokenResponse tokenResponse) {
		long expiresEpochMs = tokenResponse.getExpires();
		return LocalDateTime.ofEpochSecond(expiresEpochMs / 1000, 0, zoneOffset);
	}
}
