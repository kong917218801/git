package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.OrganizAddress;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;

/**
 *
 * @ClassName OrganizAddressMapper
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
public interface OrganizAddressMapper {

Long insert(OrganizAddress organizAddress);

    Integer update(OrganizAddress organizAddress);

OrganizAddress getById(Long id);

   OrganizAddress getaddress(@Param(value = "organizId") Long organizId, @Param(value = "adressType") Integer adressType);
}