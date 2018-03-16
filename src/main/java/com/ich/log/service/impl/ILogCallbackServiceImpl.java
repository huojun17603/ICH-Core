package com.ich.log.service.impl;

import com.ich.core.base.JsonUtils;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.service.CallbackService;
import com.ich.log.dto.ILogLocalData;

import java.util.Map;

public class ILogCallbackServiceImpl implements CallbackService {

    @Override
    public void execute(String callback) {
        try {
            Map<String, Object> map = JsonUtils.jsonToPojoX(callback, Map.class);
            if (map.containsKey(HttpResponse.RETURN_STATUS)&&map.containsKey(HttpResponse.RETURN_MSG)) {
                HttpResponse response = new HttpResponse((int) map.get(HttpResponse.RETURN_STATUS), (String) map.get(HttpResponse.RETURN_MSG));
                ILogLocalData.setData("ilog_callbak", JsonUtils.objectToJson(response));
            } else {
                ILogLocalData.setData("ilog_callbak", "");
            }
        }catch (Exception e){
            ILogLocalData.setData("ilog_callbak", "unresolved format!!");
        }
    }

    @Override
    public void execute(HttpResponse response) {
        HttpResponse data = new HttpResponse(response.getStatus(), response.getMsg());
        ILogLocalData.setData("ilog_callbak", JsonUtils.objectToJson(data));
    }

}
