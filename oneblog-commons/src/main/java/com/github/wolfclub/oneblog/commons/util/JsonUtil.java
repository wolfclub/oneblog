package com.github.wolfclub.oneblog.commons.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchan
 * @date 10:42 2018/3/5
 */
public class JsonUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	private static ThreadLocal<ObjectMapper> threadLocal = new ThreadLocal<ObjectMapper>() {
		@Override
		protected ObjectMapper initialValue() {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper;
		}
	};

	public static String objectToJson(Object value) throws RuntimeException {
		if (value == null) {
			return null;
		}

		try {
			return threadLocal.get().writeValueAsString(value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public static <T> T jsonToObject(String json, Class<T> valueType) throws RuntimeException {
		if (StringUtils.isBlank(json)) {
			return null;
		}

		try {
			return threadLocal.get().readValue(json, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static <T> List<T> jsonToArrayList(String json, Class<T> elementType)
					throws RuntimeException {
		if (StringUtils.isBlank(json)) {
			return new ArrayList<T>();
		}

		try {
			return threadLocal.get().readValue(json,
							threadLocal.get().getTypeFactory().constructParametricType(ArrayList.class, elementType));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
