package tw.elliot.util;

public class SecurityContextHolder {
	private static ThreadLocal<LoginUser> loginUser = new ThreadLocal<LoginUser>();

	public static LoginUser getLoginUser() {
		return loginUser.get();
	}

	public static void setLoginUser(LoginUser user) {
		loginUser.set(user);
	}
	
	public static String getLoginUserAccount() {
		return null != loginUser.get()?loginUser.get().getAccount():"Tester";
	}
}
