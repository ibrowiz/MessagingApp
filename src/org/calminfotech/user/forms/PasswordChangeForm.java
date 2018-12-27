package org.calminfotech.user.forms;

import javax.persistence.Entity;

@Entity
public class PasswordChangeForm {
	private String password1;
	private String password2;
	private String password3;
	
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword3() {
		return password3;
	}
	public void setPassword3(String password3) {
		this.password3 = password3;
	}
}
