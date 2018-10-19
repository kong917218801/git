package com.cpsdb.declareserv.service;

import com.cpsdb.declareserv.dao.OperationLogMapper;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpsdb.declareserv.entity.OperationLog;

/**
 * 
 * @ClassName OperationLogService
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
@Service
public class OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;
    
    public void insert(OperationLog operationLog) {
        operationLogMapper.insert(operationLog);
    }
    
    public void update(OperationLog operationLog) {
operationLogMapper.update(operationLog);
    }

    public OperationLog getById(Long id) {
        return operationLogMapper.getById(id);
    }

    public void insert(OperationLogUtils.Type type, Long objectId, String stateFrom, String event, String stateTo, String owner, String mark) {
        OperationLog log = new OperationLog()
                .setType(type.name())
                .setObjectId(objectId)
                .setStateFrom(stateFrom)
                .setEvent(event)
                .setStateTo(stateTo)
                .setMark(mark)
                .setOwner(owner);
        this.insert(log);
    }

}