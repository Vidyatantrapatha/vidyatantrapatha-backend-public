package me.electronicsboy.vidyatantrapatha.temporary.objects;

public class TemporaryDeviceObject {
	private Long id;
	private String deviceId;
	private String deviceName;
	
	public TemporaryDeviceObject(Long id, String deviceId, String deviceName) {
		this.id = id;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
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
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
