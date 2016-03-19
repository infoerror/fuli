package com.duang.fuli.service.result;

public class Protocols {

	public static final int USER_NO_LOGIN_CODE = 9999;
	
	public static class Common {

		public static final int SUCC = 0;
		
	}
	

	/**
	 * 10000 - 20000
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Register extends Common {

		public static final int EXIST_USER = 10000;

		public static final int USERNAME_FORMAT_ERROR = 11000;

		public static final int PASSWORD_FORMAT_ERROR = 12000;

		public static final int TWO_PASSWORD_INEQUAL_ERROR = 12001;

		public static final int CAPTCHA_ERROR = 13000;

		public static final int EXPIRED_MAIL = 14000;

	}

	/**
	 * 20000 - 30000
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Login extends Common {

		public static final int USERNAME_OR_PASSWORD_ERROR = 20000;
		public static final int USERNAME_FORMAT_ERROR = 20001;
		public static final int PASSWORD_FORMAT_ERROR = 21000;
		public static final int CAPTCHA_ERROR = 22000;

		
	}

	/**
	 * 30000 - 40000
	 * 
	 * @author Administrator
	 * 
	 */
	public static class AddWelfare extends Common {

		public static final int TITLE_FORMAT_ERROR = 30000;
		public static final int CONTENT_FORMAT_ERROR = 31000;

	}

	/**
	 * 40000 - 50000
	 * 
	 * @author Administrator
	 * 
	 */
	public static class ModifyBasicInfo extends Common {

		public static final int NICKNAME_FORMAT_WRONG = 40000;

		public static final int NICKNAME_CONTAIN_DANGEROUS =41000;
	}

	/**
	 * 50000 - 60000
	 * 
	 * @author Administrator
	 * 
	 */
	public static class ModifyAvatar extends Common {

		public static final int UPLOAD_AVATAR_FAIL = 50000;

		public static final int UPLOAD_SUCC = 0;

	}
}
