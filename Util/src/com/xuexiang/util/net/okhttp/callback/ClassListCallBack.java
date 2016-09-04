package com.xuexiang.util.net.okhttp.callback;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

import com.google.gson.reflect.TypeToken;
import com.xuexiang.util.net.JsonUtil;

/**
 * �������󷵻ص���һ��ʵ����󼯺�
 * @author xx
 * @param <T>
 */
public abstract class ClassListCallBack<T> extends Callback<List<T>>{
	
	@Override
    public List<T> parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        Type listType = new TypeToken<List<T>>(){}.getType(); 
        List<T> list = JsonUtil.fromRequest(string, listType);
        return list;
    }
}

