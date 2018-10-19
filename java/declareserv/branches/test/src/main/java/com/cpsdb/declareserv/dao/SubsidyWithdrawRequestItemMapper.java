package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.SubsidyWithdrawRequestItem;
import org.apache.ibatis.annotations.Param;

import java.lang.Long;
import java.util.List;

/**
 * @author 李银
 * @ClassName SubsidyWithdrawRequestItemMapper
 * @Description
 * @date 2018-09-20 15:44:16
 */
public interface SubsidyWithdrawRequestItemMapper {

    Long insert(SubsidyWithdrawRequestItem subsidyWithdrawRequestItem);

    Integer update(SubsidyWithdrawRequestItem subsidyWithdrawRequestItem);

    SubsidyWithdrawRequestItem getById(Long id);

    void batchInsert(@Param(value = "requestId") Long requestId,
                     @Param(value = "items") List<SubsidyWithdrawRequestItem> items);

    List<SubsidyWithdrawRequestItem> getByRequestId(Long requestId);
}