package com.greatwqs.ssm2api.common.util.json;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/***
 * 
 * @see https://code.google.com/p/google-gson/
 * @see https://github.com/google/gson/blob/master/UserGuide.md
 * @author greatwqs
 * 
 */
public class GSonUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final Gson NAMING_GSON = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

	public static final Gson NORMAL_GSON = new Gson();

	/***
	 * 把Json转化为对象
	 * 
	 * @param <T>
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, Class<T> classOfT) {
		return NORMAL_GSON.fromJson(json, classOfT);
	}

	/**
	 * 反序列化成数组
	 * @param json="[{}]"格式
	 * @param classOfT 类型
	 */
	public static <T> ArrayList<T> getObjectsFromJson(String json, Class<T> classOfT) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
		ArrayList<JsonObject> jsonObjs = new Gson().fromJson(json, type);
		ArrayList<T> listOfT = new ArrayList<>();
		for (JsonObject jsonObj : jsonObjs) {
			listOfT.add(new Gson().fromJson(jsonObj, classOfT));
		}
		return listOfT;
	}

	/**
	 * 将对象实例转化到Gson字符串; 如果在此class中某些属性表明了@Expose; 
	 * 则此属性不会被Gson实例化到json字符串中;
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJson(Object obj) {
		return NAMING_GSON.toJson(obj);
	}

	/***
	 * 同步固定的版本信息,对对象进行json序列化;
	 * 
	 * @param gson
	 * @param obj
	 * @return
	 */
	public static String getJson(double appVersion, Object obj) {
		final Gson gson = getVersionGson(appVersion);
		return gson.toJson(obj);
	}

	/****
	 * 通过版本号获取Gson;
	 * 
	 * @param version
	 * @return
	 */
	public static Gson getVersionGson(double appVersion) {
		Gson VERSION_NAMING_GSON = new GsonBuilder().setDateFormat(DATE_FORMAT).setVersion(appVersion).create();
		return VERSION_NAMING_GSON;
	}

	// public static void main(String[] dsada){
	// Map<String,String> guigePriceMap = getObjectFromJson("", Map.class);
	// System.out.println(guigePriceMap);
	// }
}
