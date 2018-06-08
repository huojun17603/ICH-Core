package com.ich.monitor.service.impl;

import com.ich.monitor.dao.IDataLockMapper;
import com.ich.monitor.pojo.IDataLock;
import com.ich.monitor.service.IDataLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IDataLockServiceImpl implements IDataLockService {

    @Autowired
    IDataLockMapper iDataLockMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean enableLock(String servercode) {
        try {
            iDataLockMapper.insert(servercode);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean disableLock(String servercode) {
        try {
            iDataLockMapper.delete(servercode);
        }catch (Exception e){}
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public IDataLock findLock(String servercode) {
        IDataLock iDataLock = iDataLockMapper.selectById(servercode);
        return  iDataLock;
    }



}
