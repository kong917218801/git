package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.DeclareToEnterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李银
 * @ClassName DeclareToEnterpriseMapper
 * @Description
 * @date 2018-09-20 10:42:40
 */
public interface DeclareToEnterpriseMapper {

    Long insert(DeclareToEnterprise declareToEnterprise);

    Integer update(DeclareToEnterprise declareToEnterprise);

    DeclareToEnterprise getById(Long id);

    List<Long> getEnterpriseId(@Param("serviceId") Long serviceId,
                               @Param("organizId") Long organizId,
                               @Param("declarerId") Long declarerId);

    DeclareToEnterprise getByEnterpriseId(Long enterpriserId);

    DeclareToEnterprise getByDeclareEnterpriseId(Long declareEnterpriseId);
}