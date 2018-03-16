package com.ich.core.http.service;

import com.ich.core.http.entity.HttpResponse;

public interface CallbackService {

    @Deprecated
    public void execute(String callback);

    public void execute(HttpResponse response);
}
