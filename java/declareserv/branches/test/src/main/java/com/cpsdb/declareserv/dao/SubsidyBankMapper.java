package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.SubsidyBank;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;

/**
 * @author 李银
 * @ClassName SubsidyBankMapper
 * @Description
 * @date 2018-09-20 15:43:06
 */
public interface SubsidyBankMapper {

    Long insert(SubsidyBank subsidyBank);

    Integer update(SubsidyBank subsidyBank);

    SubsidyBank getById(Long id);

    /**
     * 取最后5条
     * @param userId
     */
    List<SubsidyBank> getByUserId(Long userId);


    SubsidyBank getSubsidyBank(@Param(value = "userId") Long userId,
                               @Param(value = "bankId") Long bankId,
                               @Param(value = "bankName") String bankName,
                               @Param(value = "bankCard") String bankCard,
                               @Param(value = "name") String name);
}