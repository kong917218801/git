package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CollectionUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareserv.dao.SubsidyBankMapper;
import com.cpsdb.declareserv.dao.SubsidyWithdrawRequestMapper;
import com.cpsdb.declareserv.dto.JSubsidyBank;
import com.cpsdb.declareserv.entity.SubsidyBank;
import com.cpsdb.declareserv.entity.SubsidyWithdrawRequest;
import com.cpsdb.declareserv.params.subsidy.JSubsidyBankParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔清
 * @ClassName SubsidyBankService
 * @Description
 * @date 2018-05-18 11:09:41
 */
@Service
public class SubsidyBankService {

    @Autowired
    private SubsidyBankMapper subsidyBankMapper;

    @Autowired
    private BankService bankService;

    @Autowired
    private SubsidyWithdrawRequestMapper subsidyWithdrawRequestMapper;

    public void insert(SubsidyBank subsidyBank) {
        subsidyBankMapper.insert(subsidyBank);
    }

    public void update(SubsidyBank subsidyBank) {
        subsidyBankMapper.update(subsidyBank);
    }

    public SubsidyBank getById(Long id) {
        return subsidyBankMapper.getById(id);
    }

    public SubsidyBank insertOrGet(Long userId, JSubsidyBankParams bankParams) {

        // 支行
        String bankName = StringUtils.trim(bankParams.getBankBranch()),
                // 银行卡号
                bankCard = StringUtils.trim(bankParams.getBankCard()),
                // 开户人
                name = StringUtils.trim(bankParams.getName());

        AssertUtils.isTrue(StringUtils.isNotBlank(bankName)
                && StringUtils.isNotBlank(bankCard)
                && StringUtils.isNotBlank(name), new CustomException("为了您能顺利提现，【开户名，支行，开户行账号】是必填项"));

        Long bankId = bankParams.getBankId();
        AssertUtils.notNull(bankService.getById(bankId), new CustomException("请选择一个开户行"));

        SubsidyBank bank = subsidyBankMapper.getSubsidyBank(userId, bankParams.getBankId(), bankName, bankCard, name);

        if (bank == null) {
            bank = new SubsidyBank()
                    .setName(name)
                    .setAccount(bankCard)
                    .setBankName(bankName)
                    .setUserId(userId)
                    .setFkBankId(bankParams.getBankId());
            this.insert(bank);
        }
        return bank;
    }

    public List<JSubsidyBank> getHistory(Long userId) {
        List<SubsidyBank> subsidyBanks = this.subsidyBankMapper.getByUserId(userId);
        String cellphone = null;
        // 获取历史最后一次申请填入的手机号码
        if (CollectionUtils.isNotEmpty(subsidyBanks)) {
            List<SubsidyWithdrawRequest> requests = this.subsidyWithdrawRequestMapper.query(new JSubsidyWithdrawQueryParams().setUserId(userId),
                    new DatagridPager(1, 1));
            if (CollectionUtils.isNotEmpty(requests)) {
                cellphone = requests.get(0).getCellphone();
            }
        }

        String c = cellphone;
        return subsidyBanks.stream().map(s -> new JSubsidyBank()
                .setAccount(s.getAccount())
                .setBank(bankService.getById(s.getFkBankId()).getName())
                .setBankId(s.getFkBankId())
                .setName(s.getName())
                .setBankName(s.getBankName())
                .setLogo(bankService.getById(s.getFkBankId()).getLogo())
                .setCellphone(c))
                .collect(Collectors.toList());
    }
}