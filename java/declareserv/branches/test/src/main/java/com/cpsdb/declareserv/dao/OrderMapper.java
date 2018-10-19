package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.Order;
import com.cpsdb.declareserv.entity.Organiz;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;

/**
 *
 * @ClassName OrderMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:54:46
 */
public interface OrderMapper {

    Long insert(Order order);

    Integer update(Order order);

    Order getById(Long id);

    Order getByDeclarerId(@Param(value = "declarerId") Long declarerId, @Param(value = "state") String state);
}