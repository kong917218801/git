package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 李银
 * @ClassName UserMapper
 * @Description
 * @date 2018-09-20 10:27:36
 */
public interface UserMapper {

    Long insert(User user);

    Integer update(User user);

    User getById(Long id);

    User getByUsername(String username);

    User getByObjectId(@Param("objectId") Long objectId, @Param("userType") Integer userType);

    void updateState(@Param(value = "objectId") Long objectId,
                     @Param(value = "type") Integer type,
                     @Param(value = "state") Integer state);
}