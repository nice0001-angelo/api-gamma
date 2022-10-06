package net.jin.common.security.jwt.credential;

import com.fasterxml.jackson.annotation.*;

import io.swagger.annotations.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Credential {
	@ApiModelProperty(position = 0)
	private String username;
	@ApiModelProperty(position = 1)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
