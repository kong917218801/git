package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.params.provence.JProvenceQueryParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李银
 * @ClassName ProvenceCenterMapper
 * @Description
 * @date 2018-09-20 10:42:40
 */
public interface ProvenceCenterMapper {

    void insert(ProvenceCenter provenceCenter);

    void update(ProvenceCenter provenceCenter);

    ProvenceCenter getById(Long id);

    ProvenceCenter getByPhone(String cellphone);

    void updateState(@Param(value = "id") Long id,
                     @Param(value = "state") String state,
                     @Param(value = "reason") String reason);


    ProvenceCenter getIdNumber(String idNumber);

    ProvenceCenter getByAreaCode(String areaCode);

    Long count(@Param(value = "params") JProvenceQueryParams params);

    List<ProvenceCenter> query(@Param("params") JProvenceQueryParams params,
                               @Param("pager") Pageable pager);

    List<String> getAreaCodeList();

    void updatePayment(@Param(value = "id") Long id, @Param(value = "payment") String payment);
}