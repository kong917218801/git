package com.cpsdb.declareserv.service;

import com.cpsdb.declareserv.dao.AreaLimitMapper;
import com.cpsdb.declareserv.dto.JAreaLimitOrganiz;
import com.cpsdb.declareserv.entity.AreaLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李银
 * @ClassName AreaLimitService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class AreaLimitService {

    @Autowired
    private AreaLimitMapper areaLimitMapper;

    public void insert(AreaLimit areaLimit) {
        areaLimitMapper.insert(areaLimit);
    }

    public void update(AreaLimit areaLimit) {
        areaLimitMapper.update(areaLimit);
    }

    public AreaLimit getById(Long id) {
        return areaLimitMapper.getById(id);
    }

    public AreaLimit getByAreaCode(String areaCode) {
        return areaLimitMapper.getByAreaCode(areaCode);
    }

    public JAreaLimitOrganiz getAreaLimitList(String areaCode){
        AreaLimit areaLimit = areaLimitMapper.getAreaLimitList(areaCode);
        return new JAreaLimitOrganiz()
                    .setId(areaLimit.getId())
                    .setRemainNumber(areaLimit.getRemainNumber())
                    .setAreaCode(areaLimit.getAreaCode());

    }
}