package com.greatwqs.ssm2api.domain.model;

import com.google.gson.annotations.SerializedName;

/***
 * 
 * 服务器端返回Json参数给客户端时进行的数据传输Json规则;
 * 
 * @author greatwqs
 * 
 */
public class GsonModel {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Object data;
	
	public static GsonModel create(int code, Object data) {
		GsonModel gsonModel = new GsonModel();
		gsonModel.setCode(code);
		gsonModel.setData(data);
		return gsonModel;
	}
	
	public GsonModel() {
		super();
	}
	
	public GsonModel(Object data) {
		super();
		this.data = data;
	}
	
	public GsonModel(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GsonModel other = (GsonModel) obj;
		if (code != other.code)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
