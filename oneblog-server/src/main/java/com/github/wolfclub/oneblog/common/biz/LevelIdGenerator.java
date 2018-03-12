package com.github.wolfclub.oneblog.common.biz;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.text.MessageFormat;
//import java.util.List;
//import java.util.regex.Pattern;
//
///**
// * @author liuchan
// * @date 11:21 2018/3/5
// */
//@Component
//public class LevelIdGenerator {
//	@Value(value = "${oneblog.levelId.generator.rule:4444}")
//	private String rule;
//	@Value(value = "${oneblog.levelId.generator.propName:levelId}")
//	private String propName;
//
//
//	private static final Pattern PATTERN_RULE = Pattern.compile("^\\d+$");
//
//	public LevelIdGenerator() {
//	}
//
//	public LevelIdGenerator(String rule, String propName) {
//		this.rule=rule;
//		this.propName=propName;
//	}
//
//	/**
//	 * 产生下一个层级码。
//	 *
//	 * @param em
//	 *          指定EntityManager。not null。
//	 * @param entityClass
//	 *          持久化对象类型
//	 * @param upper
//	 *          指定上级层级码。传入null或者是空白字符串，表示取得第一层层级码。
//	 * @return 返回新的层级码。
//	 * @throws RuntimeException
//	 */
//	public <T> String next(MyMapper<T> mapper, Class entityClass, String parent) throws RuntimeException {
//		if (mapper == null)
//			return null;
//		if (parent == null || "".equals(parent.trim())) {
//			parent = "";
//		}
//		if (PATTERN_RULE.matcher(rule).matches() == false) {
//			throw new RuntimeException("指定参数rule取值(" + rule + ")不合法。");
//		}
//
//		int current = which(parent);
//		String pattern = parent + mask(current + 1);
//		String levelId = parent + zero(current + 1);
//		String hql = "select max(o." + propName + ") from " + entityClass.getName() + " o" + " where o."
//						+ propName + " like :levelId";
//		Query qry = em.createQuery(hql);
//		qry.setParameter("levelId", pattern);
//		List list = qry.getResultList();
//		if (list.isEmpty() == false && list.get(0) != null) {
//			String max = (String) list.get(0);
//			levelId = parent + FlowCodeUtil.flowCode(max.substring(parent.length()));
//		}
//		return levelId;
//	}
//
//	/**
//	 * 判断指定的代码是否符合层级码规则。
//	 *
//	 * @param code
//	 *          指定被验证的代码。not null。
//	 * @param upperCode
//	 *          指定上级的代码。传入null等价于空字符串。
//	 * @throws RuntimeException
//	 */
//	public boolean validate(String code, String upperCode) throws RuntimeException {
//		if (code == null) {
//			return false;
//		}
//		if (upperCode == null) {
//			upperCode = "";
//		}
//
//		if (code.startsWith(upperCode) == false) {
//			return false;
//		}
//		int upperLevel = which(upperCode);
//		if (code.length() - upperCode.length() != levelLength(upperLevel + 1)) {
//			return false;
//		}
//		return true;
//	}
//
//	private String zero(int level) throws RuntimeException {
//		return StringUtils.repeat('0', levelLength(level));
//	}
//
//	private String mask(int level) throws RuntimeException {
//		return StringUtils.repeat('_', levelLength(level));
//	}
//
//	private int which(String levelId) throws RuntimeException {
//		if ("".equals(levelId)) {
//			return 0;
//		}
//		int level = 1;
//		int len = 0;
//		while (true) {
//			len += levelLength(level);
//			if (levelId.length() <= len) {
//				return level;
//			}
//			level++;
//		}
//	}
//
//	private int levelLength(int level) throws RuntimeException {
//		assert level >= 1;
//		if (level > rule.length()) {
//			throw new RuntimeException(MessageFormat.format("超出层级码的最大范围(当前{0}，最大允许{1})。",
//							Integer.valueOf(level), Integer.valueOf(rule.length())));
//		}
//		return Integer.parseInt(rule.substring(level - 1, level));
//	}
//
//
//
//	public String getRule() {
//		return rule;
//	}
//	public String getPropName() {
//		return propName;
//	}
//}
