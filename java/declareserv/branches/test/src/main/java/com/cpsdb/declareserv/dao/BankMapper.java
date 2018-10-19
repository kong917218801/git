package com.cpsdb.declareserv.dao;

import com.cpsdb.declareserv.entity.Bank;

import java.lang.Long;
import java.util.List;

/**
 * @author 李银
 * @ClassName BankMapper
 * @Description
 * @date 2018-09-20 15:43:26
 */
public interface BankMapper {

    Long insert(Bank bank);

    Integer update(Bank bank);

    Bank getById(Long id);

    List<Bank> getAll();
}