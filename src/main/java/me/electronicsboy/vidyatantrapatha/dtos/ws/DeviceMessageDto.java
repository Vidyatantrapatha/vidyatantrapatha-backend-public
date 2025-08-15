package me.electronicsboy.vidyatantrapatha.dtos.ws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import me.electronicsboy.vidyatantrapatha.data.DeviceRequestType;
import me.electronicsboy.vidyatantrapatha.data.DeviceStatus;

public class DeviceMessageDto {
	private DeviceStatus status;
	private DeviceRequestType requestType;
	private String data;
	
	@JsonCreator
	public DeviceMessageDto(
			@JsonProperty("status") DeviceStatus status, 
			@JsonProperty("requestType") DeviceRequestType requestType, 
			@JsonProperty("data") String data
	) {
		super();
		this.status = status;
		this.requestType = requestType;
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public DeviceStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

	/**
	 * @return the requestType
	 */
	public DeviceRequestType getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(DeviceRequestType requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
}
