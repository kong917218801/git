package com.cpsdb.declareserv.service;

import com.cpsdb.declareserv.dao.BankMapper;
import com.cpsdb.declareserv.dto.JBank;
import com.cpsdb.declareserv.entity.Bank;
import com.cpsdb.redis.annotation.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李银
 * @ClassName BankService
 * @Description
 * @date 2018-09-20 17:26:01
 */
@Service
public class BankService {

    @Autowired
    private BankMapper bankMapper;

    public void insert(Bank bank) {
        bankMapper.insert(bank);
    }

    public void update(Bank bank) {
        bankMapper.update(bank);
    }

    public Bank getById(Long id) {
        return bankMapper.getById(id);
    }

    @Cache(time = "30")
    public List<Bank> getAll() {
        return this.bankMapper.getAll();
    }
}