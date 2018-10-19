package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.entity.SubsidyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpsdb.declareserv.dao.SubsidyWithdrawRequestItemMapper;
import com.cpsdb.declareserv.entity.SubsidyWithdrawRequestItem;
import com.cpsdb.declareserv.service.SubsidyWithdrawRequestItemService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李银
 * @ClassName SubsidyWithdrawRequestItemService
 * @Description
 * @date 2018-09-20 17:26:02
 */
@Service
public class SubsidyWithdrawRequestItemService {

    @Autowired
    private SubsidyWithdrawRequestItemMapper subsidyWithdrawRequestItemMapper;

    public void insert(SubsidyWithdrawRequestItem subsidyWithdrawRequestItem) {
        subsidyWithdrawRequestItemMapper.insert(subsidyWithdrawRequestItem);
    }

    public void update(SubsidyWithdrawRequestItem subsidyWithdrawRequestItem) {
        subsidyWithdrawRequestItemMapper.update(subsidyWithdrawRequestItem);
    }

    public SubsidyWithdrawRequestItem getById(Long id) {
        return subsidyWithdrawRequestItemMapper.getById(id);
    }

    public List<SubsidyWithdrawRequestItem> getByRequestId(Long requestId) {
        return subsidyWithdrawRequestItemMapper.getByRequestId(requestId);
    }

    public void insert(Long requestId, List<SubsidyDetail> details) {
        AssertUtils.notEmpty(details, new CustomException("没有可申请的补贴!"));
        List<SubsidyWithdrawRequestItem> items = details.stream()
                .map(s -> new SubsidyWithdrawRequestItem()
                        .setFkSubsidyDetailId(s.getId())
                        .setAmount(s.getAmount().multiply(s.getPercent()))
                )
                .collect(Collectors.toList());

        this.subsidyWithdrawRequestItemMapper.batchInsert(requestId, items);
    }
}