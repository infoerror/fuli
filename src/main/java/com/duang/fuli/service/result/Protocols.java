package com.duang.fuli.service.result;


/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:54:10
 */
public class Protocols {

	public static final int USER_NO_LOGIN_CODE = 9999;

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:47
	 */
	public static class Common {

		public static final int SUCC = 0;

	}

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:42
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
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:35
	 */
	public static class Login extends Common {

		public static final int USERNAME_OR_PASSWORD_ERROR = 20000;
		public static final int USERNAME_FORMAT_ERROR = 20001;
		public static final int PASSWORD_FORMAT_ERROR = 21000;
		public static final int CAPTCHA_ERROR = 22000;

	}

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:31
	 */
	public static class AddWelfare extends Common {

		public static final int TITLE_FORMAT_ERROR = 30000;
		public static final int CONTENT_FORMAT_ERROR = 31000;

	}

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:24
	 */
	public static class ModifyBasicInfo extends Common {

		public static final int NICKNAME_FORMAT_WRONG = 40000;

		public static final int NICKNAME_CONTAIN_DANGEROUS = 41000;
	}

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:18
	 */
	public static class ModifyAvatar extends Common {

		public static final int UPLOAD_AVATAR_FAIL = 50000;

		public static final int UPLOAD_SUCC = 0;

	}

	/**
	 * 
	 * @author zgq
	 * @date 2016年3月19日 下午8:53:13
	 */
	public static class ModifyPassword extends Common {

		public static final int PASSWORD_FORMAT_ERROR = 60000;
		public static final int TWO_PASSWORD_INEQUAL = 61000;
		public static final int ORIGIN_PASSWORD_WRONG = 62000;

	}
	
	/**
	 * 
	 * @author zgq
	 * @date 2016年3月20日 下午12:02:47
	 */
	public static class Follow extends Common {

		public static final int USER_INEXISTENT = 70000;
		public static final int ALREADY_FOLLOWED = 71000;
		public static final int UNABLE_TO_FOLLOW_ONESLFE = 72000;


	}
	
	/**
	 * 
	 * @author zgq
	 * @date 2016年3月20日 下午7:51:20
	 */
	public static class CheckFollow extends Common {
		
		public static final int ALREADY_FOLLOW = 0;

		public static final int NOT_FOLLOW = -1;
		
	}
	
	
	public static class Welfare extends Common{
		
		public static final int ADD_COMMENT_SUCC=SUCC;
		public static final int CONTENT_FORMAT_ERROR = 80000;
		public static final int COMMENT_INEXISTENT = 81000;
		
		
	}
	
	
}
