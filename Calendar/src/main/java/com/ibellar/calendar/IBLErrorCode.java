package com.ibellar.calendar;

public class IBLErrorCode {

	public final static int ALL_OK = 603;
	public final static int ACCOUNT_DOES_NOT_EXIST = 1001;
	public final static int ACCOUNT_PASS_EROOR = 1002;
	public final static int ACCOUNT_FORMAT_ERROR = 1003;
	public final static int ACCOUNT_ALREADY_EXIST = 1004;

	public final static int DOMAIN_ALREADY_EXIST = 2001;
	
	public final static int ILLEGAL_PARAM  = 101;
	
	public final static int DATABASE_ERROR = 3001;
	
	public final static int AUTHORY_EOORY = 4001;

	public static String codeToString(int code) {

		switch (code) {
		case ACCOUNT_DOES_NOT_EXIST:
			return IBLDefine.NOACCOUNT;
		case ACCOUNT_PASS_EROOR:
			return IBLDefine.PWError;
		case ACCOUNT_FORMAT_ERROR:
			return IBLDefine.ACCOUNT_FORMAT_ERR;
		case ACCOUNT_ALREADY_EXIST:
			return IBLDefine.ACCOUNT_ALREADY_Exist;
		case DOMAIN_ALREADY_EXIST:
			return IBLDefine.DOMAIN_EXIST;
		case ILLEGAL_PARAM:
			return IBLDefine.ILLEGAL_PARAMETER;
		case DATABASE_ERROR:
			return IBLDefine.DATABASE_ERROR;
		case AUTHORY_EOORY:
			return IBLDefine.AUTHORY_FAIL;
		default:
			break;
		}
		return "";
	}
}
