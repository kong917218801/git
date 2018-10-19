package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.entity.Authofficer;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerQueryParams;
import org.apache.ibatis.annotations.Param;

import java.lang.Integer;
import java.util.List;

/**
 *
 * @ClassName AuthofficerMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface AuthofficerMapper {
    Integer insert(Authofficer authofficer);

    Integer update(Authofficer authofficer);

    Authofficer getById(Long id);

    Authofficer getByCellPhone(String cellPhone);

    void updateState(@Param(value = "id") Long id, @Param(value = "state") Boolean state);

    Integer count(@Param(value = "params") JAuthofficerQueryParams params);

    List<Authofficer> query(@Param(value = "params") JAuthofficerQueryParams params,
                            @Param(value = "pager") Pageable pager);

    List<Authofficer> getByName(@Param(value = "name") String name);
}