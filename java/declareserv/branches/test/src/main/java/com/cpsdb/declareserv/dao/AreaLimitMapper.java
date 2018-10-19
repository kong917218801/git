package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.AreaLimit;

/**
 *
 * @ClassName AreaLimitMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface AreaLimitMapper {

Long insert(AreaLimit areaLimit);

    Integer update(AreaLimit areaLimit);

AreaLimit getById(Long id);

    AreaLimit getByAreaCode(String areaCode);

    AreaLimit getAreaLimitList(String areaCode);
}