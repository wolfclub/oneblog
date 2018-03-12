package com.github.wolfclub.oneblog.commons.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liuchan
 * @date 10:53 2018/3/5
 */
public class FlowCodeUtil {

	/**
	 * 取得指定代码的下一个流水代码。参与流水可能的字符是'0'-'9''A'-'Z'，具体取决于参数scale。
	 *
	 * @param code
	 *          指定输入的代码。not null，也不可以为空串。
	 * @throws RuntimeException
	 *           当参数非法时抛出；当越界时抛出；当字符非法时抛出。
	 */
	public static String flowCode(String code) throws RuntimeException {
		if (StringUtils.isBlank(code)) {
			throw new RuntimeException("传入的代码无法流水(" + code + ")。");
		}

		char[] chars = code.toCharArray();
		int index = chars.length - 1;
		while (index >= 0) {
			char c = chars[index];
			if (c >= '0' && c < '9' || c >= 'A' && c < 'Z' || c >= 'a' && c < 'z') {
				chars[index] = (char) (c + 1);
				break;
			} else if (c == '9') {
				chars[index] = '0';
			} else if (c == 'Z') {
				chars[index] = 'A';
			} else if (c == 'z') {
				chars[index] = 'a';
			}
			index--;
		}
		if (index < 0) {
			throw new RuntimeException("传入的代码已经是达到最大(" + code + ")。");
		}
		return String.valueOf(chars);
	}

}
