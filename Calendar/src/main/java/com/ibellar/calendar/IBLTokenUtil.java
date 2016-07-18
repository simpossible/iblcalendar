package com.ibellar.calendar;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IBLTokenUtil {

	public static String EncryptString(String ssoToken) throws UnsupportedEncodingException {
		byte[] _ssoToken = ssoToken.getBytes("UTF-8");
		String name = new String();
		// char[] _ssoToken = ssoToken.toCharArray();
		for (int i = 0; i < _ssoToken.length; i++) {
			int asc = _ssoToken[i];
			_ssoToken[i] = (byte) (asc + 27);
			name = name + (asc + 27) + "%";
		}
		return name;
	}

	public static String decryptToken(String ssoToken) {
		String name = new String();
		java.util.StringTokenizer st = new java.util.StringTokenizer(ssoToken, "%");
		while (st.hasMoreElements()) {
			int asc = Integer.parseInt((String) st.nextElement()) - 27;
			name = name + (char) asc;
		}

		return name;
	}

	public static String encryptMap(Map<String, Object> map) throws UnsupportedEncodingException {
		Gson json = new Gson();
		String code = json.toJson(map);
		String enCode = EncryptString(code);
		return enCode;

	}

	/***
	 * 对 token 解码 并得到存储在里面的
	 * 
	 * @param token
	 * @param key
	 * @return
	 */
	public static Object getvalueFromTokenWithKey(String token, String key) {
		if (token == null || key == null) {
			return null;
		}
		String json = decryptToken(token);
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		return map.get(key);
	}

	public static String generateGetTokenWithKey(String email) {
		return email;
	}

	public static String tokenForRequest(HttpServletRequest request) {
		return request.getParameter(IBLDefine.Token_key);
	}
}
