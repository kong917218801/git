package com.cpsdb.declareserv.dao;


import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.params.organiz.JOrganizQueryParams;
import com.cpsdb.declareserv.params.organiz.JQueryList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author 李银
 * @ClassName OrganizMapper
 * @Description
 * @date 2017-12-13 10:08:08
 */
public interface OrganizMapper {

    Long insert(Organiz organiz);

    Integer update(Organiz organiz);

    Organiz getById(@Param(value = "id") Long id);

    List<Organiz> query(
            @Param(value = "params") JOrganizQueryParams params,
            @Param(value = "pager") Pageable pager);

    Long count(@Param(value = "params") JOrganizQueryParams params);

    List<Organiz> getOrganizlist(@Param(value = "params") JQueryList params,
                                 @Param(value = "pager") Pageable pager);

    Integer getOrganizCount(@Param(value = "params") JQueryList params);

    void updateState(@Param(value = "id") Long id,
                     @Param(value = "state") String state);

    Organiz getByIdNumber(@Param(value = "idNumber") String idNumber);

    Organiz getByCellphone(@Param(value = "cellphone") String cellphone);


    void updateProvenceId(@Param(value = "areaCode") String areaCode,
                          @Param(value = "provenceId")Long provenceId);

    List<Organiz> organizName(String name);

    List<Organiz> getByIdS(@Param(value = "organizIds") Set<Long> organizIds);
}