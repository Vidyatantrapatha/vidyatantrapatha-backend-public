package me.electronicsboy.vidyatantrapatha.temporary.objects;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.data.util.Pair;

import me.electronicsboy.vidyatantrapatha.data.DeviceAuthenticationStatus;

public class TemporaryDeviceWebsocketSession {
	private Long id;
	private String sessionId;
	private DeviceAuthenticationStatus authenticationStatus;
	private String token;
	private String refreshToken;
	private boolean authenticationSession;
	private Pair<PublicKey, PrivateKey> serverEncryptionKeys;
	private PublicKey devicePublicKey;
	private TemporaryDeviceObject deviceObject;
	
	public TemporaryDeviceWebsocketSession(Long id, String sessionId, DeviceAuthenticationStatus authenticationStatus, String token, String refreshToken, boolean authenticationSession, TemporaryDeviceObject deviceObject) {
		this.id = id;
		this.sessionId = sessionId;
		this.authenticationStatus = authenticationStatus;
		this.token = token;
		this.refreshToken = refreshToken;
		this.authenticationSession = authenticationSession;
		this.setDeviceObject(deviceObject);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the authenticationStatus
	 */
	public DeviceAuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}
	/**
	 * @param authenticationStatus the authenticationStatus to set
	 */
	public void setAuthenticationStatus(DeviceAuthenticationStatus authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the authenticationSession
	 */
	public boolean isAuthenticationSession() {
		return authenticationSession;
	}

	/**
	 * @param authenticationSession the authenticationSession to set
	 */
	public void setAuthenticationSession(boolean authenticationSession) {
		this.authenticationSession = authenticationSession;
	}

	/**
	 * @return the serverEncryptionKeys
	 */
	public Pair<PublicKey, PrivateKey> getServerEncryptionKeys() {
		return serverEncryptionKeys;
	}

	/**
	 * @param serverEncryptionKeys the serverEncryptionKeys to set
	 */
	public void setServerEncryptionKeys(Pair<PublicKey, PrivateKey> serverEncryptionKeys) {
		this.serverEncryptionKeys = serverEncryptionKeys;
	}

	/**
	 * @return the devicePublicKey
	 */
	public PublicKey getDevicePublicKey() {
		return devicePublicKey;
	}

	/**
	 * @param devicePublicKey the devicePublicKey to set
	 */
	public void setDevicePublicKey(PublicKey devicePublicKey) {
		this.devicePublicKey = devicePublicKey;
	}

	public TemporaryDeviceObject getDeviceObject() {
		return deviceObject;
	}

	public void setDeviceObject(TemporaryDeviceObject deviceObject) {
		this.deviceObject = deviceObject;
	}
}
