package com.zsyao.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONObjectUtil
{
	public static String getJSONStringValue(JSONObject json, String key)
	{
		if (json == null)
		{
			return null;
		}
		
		if (key == null)
		{
			return null;
		}
		
		if (json.containsKey(key))
		{
			return json.getString(key);
		}
		return null;
	}
	
	public static JSONArray getJSONArrayValue(JSONObject json, String key)
	{
		if (json == null)
		{
			return null;
		}
		
		if (key == null)
		{
			return null;
		}
		
		if (json.containsKey(key))
		{
			return json.getJSONArray(key);
		}
		return null;
	}
	
	public static JSONObject getJSONObjectValue(JSONObject json, String key)
	{
		if (json == null)
		{
			return null;
		}
		
		if (key == null)
		{
			return null;
		}
		
		if (json.containsKey(key))
		{
			return json.getJSONObject(key);
		}
		return null;
	}
}
