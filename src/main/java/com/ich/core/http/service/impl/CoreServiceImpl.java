package com.ich.core.http.service.impl;

import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.service.CallbackService;
import com.ich.core.http.service.CoreService;

import java.util.List;

public class CoreServiceImpl implements CoreService {

    private List<CallbackService> backList;

    public List<CallbackService> getBackList() {
        return backList;
    }

    public void setBackList(List<CallbackService> backList) {
        this.backList = backList;
    }

    @Override
    public void executeCallback(String json) {
        for(CallbackService service : backList) service.execute(json);
    }

    @Override
    public void executeCallback(HttpResponse response) {
        for(CallbackService service : backList) service.execute(response);
    }

}
