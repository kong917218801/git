package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.OperationLog;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;

/**
 *
 * @ClassName OperationLogMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface OperationLogMapper {

    Long insert(OperationLog operationLog);

    Integer update(OperationLog operationLog);

    OperationLog getById(Long id);

    List<OperationLog> getByOwner(@Param(value = "username") String username,
                                  @Param(value = "declareEnterprise") OperationLogUtils.Type declareEnterprise);

    List<OperationLog> getByIdType(@Param(value = "id") Long id,
                                   @Param(value = "type") OperationLogUtils.Type type);
}