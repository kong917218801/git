package com.cpsdb.declareserv.controller.publics;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.declareserv.dto.JBank;
import com.cpsdb.declareserv.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 李银
 * @ClassName DeclarerAppSubsidyController
 * @Description:
 * @date: 2018年9月20日 17:13:12
 */

@RestController
@RequestMapping("publics/bank")
public class PublicsBankController extends BaseController {

    @Autowired
    private BankService bankService;

    /**
     * @Description 获取公用银行列表
     */
    @GetMapping("")
    List<JBank> amount() {
        return bankService.getAll()
                .stream().map(s -> new JBank()
                        .setId(s.getId())
                        .setName(s.getName())
                        .setLogo(s.getLogo())
                ).collect(Collectors.toList());
    }
}
