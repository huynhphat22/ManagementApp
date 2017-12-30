package Model.MODEL;

public class PasswordChange {
	private String username;
	private String password;
	private String newPassword;
	private String reNewPassword;

	public PasswordChange() {
	}

	public PasswordChange(String username, String password, String newPassword, String reNewPassword) {
		super();
		this.username = username;
		this.password = password;
		this.newPassword = newPassword;
		this.reNewPassword = reNewPassword;
	}

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReNewPassword() {
		return reNewPassword;
	}

	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}

}
