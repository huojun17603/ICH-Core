package com.ich.international.service.impl;


import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.service.CallbackService;
import com.ich.international.pojo.ILocaleMessage;

public class InternationalCallbackServiceImpl implements CallbackService {

    @Override
    public void execute(String callback) {}

    @Override
    public void execute(HttpResponse response) {
        //获取KEY，VALUE
        String value = ILocaleMessage.DATAMAP.get(response.getMsg());
        //替换返回值
        if(ObjectHelper.isNotEmpty(value)){
            response.setMsg(value);
        }
    }
}
