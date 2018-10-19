package com.cpsdb.declareserv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpsdb.declareserv.dao.DeclarerCodeDetailMapper;
import com.cpsdb.declareserv.entity.DeclarerCodeDetail;

import java.util.List;

/**
 * 
 * @ClassName DeclarerCodeDetailService
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
@Service
public class DeclarerCodeDetailService {

    @Autowired
    private DeclarerCodeDetailMapper declarerCodeDetailMapper;
    
    public void insert(DeclarerCodeDetail declarerCodeDetail) {
declarerCodeDetailMapper.insert(declarerCodeDetail);
    }
    
    public void update(DeclarerCodeDetail declarerCodeDetail) {
declarerCodeDetailMapper.update(declarerCodeDetail);
    }

    public DeclarerCodeDetail getById(Long id) {
        return declarerCodeDetailMapper.getById(id);
    }

    public List<DeclarerCodeDetail> getDetailsByDeclarerId(Long organizId) {
        return declarerCodeDetailMapper.getDetailsByDeclarerId(organizId);
    }
}