package com.ich.core.http.service;

import com.ich.core.http.entity.HttpResponse;

public interface CoreService {

    @Deprecated
    public void executeCallback(String json);

    public void executeCallback(HttpResponse response);

}
