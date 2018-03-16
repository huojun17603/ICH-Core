package com.ich.log.service.impl;

import com.ich.core.http.entity.HttpResponse;
import com.ich.log.dao.ILogMapper;
import com.ich.log.pojo.ILog;
import com.ich.log.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ILogServiceImpl implements ILogService {

    @Autowired
    private ILogMapper iLogMapper;

    @Override
    public void addInterceptorILog(ILog iLog) {
        iLogMapper.insert(iLog);
    }
}
