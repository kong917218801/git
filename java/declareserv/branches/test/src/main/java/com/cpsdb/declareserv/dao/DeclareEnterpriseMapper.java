package com.cpsdb.declareserv.dao;

import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.dto.DeclareEnterpriseQuery;
import com.cpsdb.declareserv.entity.DeclareEnterprise;
import com.cpsdb.declareserv.entity.DeclareToEnterprise;
import com.cpsdb.declareserv.params.declarer.JEnterpriseListParams;
import com.cpsdb.declareserv.params.declarer.JEnterpriseQueryParams;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;

/**
 *
 * @ClassName DeclareEnterpriseMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface DeclareEnterpriseMapper {

    Long insert(DeclareEnterprise declareEnterprise);

    Integer update(DeclareEnterprise declareEnterprise);

    DeclareEnterprise getById(Long id);

    DeclareEnterprise getByCellPhone(@Param(value = "cellphone") String cellphone);

    DeclareEnterprise getByIdNumber(@Param(value = "idNumber") String idNumber);

    void updateState(@Param(value = "id") Long id, @Param(value = "state") String state);

    List<DeclareEnterprise> queryDeclarer(@Param(value = "params") JEnterpriseListParams params,
                                          @Param(value = "pager") Pageable pager);

    List<DeclareEnterprise> queryList(@Param(value = "params") JEnterpriseQueryParams params,
                                           @Param(value = "pager") DatagridPager pager);

    Long queryOrganizCount(@Param(value = "params") JEnterpriseQueryParams params);

    DeclareEnterprise getByEnterpriseName(@Param(value = "enterpriseName")String enterpriseName);

    Long getEnterpriseCount(@Param(value = "params") JEnterpriseListParams params);

    void updateOrganiz(@Param("declarerId") Long declarerId,
                       @Param("organizId") Long organizId);

}