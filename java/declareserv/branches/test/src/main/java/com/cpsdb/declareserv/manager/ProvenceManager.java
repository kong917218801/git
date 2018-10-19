package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.PasswordUtils;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareserv.dao.ProvenceCenterMapper;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.params.provence.JProvenceCenterAddParams;
import com.cpsdb.declareserv.params.provence.JProvenceQueryParams;
import com.cpsdb.declareserv.service.ProvenceCenterService;
import com.cpsdb.redis.annotation.Lock;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Author 老周
 * @Description 功能描述
 * @CreateDate 2018.7.1
 * @Version v1.0
 */
@Service
public class ProvenceManager {

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private ProvenceCenterMapper provenceCenterMapper;

    @Lock(key = "PROVENCE_CENTER_REGISTER", argsIndex = 0)
    public void register(String key, JProvenceCenterAddParams params) {
        /*校验参数*/
        String cellphone = params.getCellphone(),
                code = params.getCode();
//        String random = String.format("%d", Math.abs(new Random().nextInt()) % 99999999);
//        params.setPassword(PasswordUtils.encrypt(password, random));
        /*验证输入的手机短信验证码*/
        juHeApi.vilidaty(ModeType.REGISET, cellphone, code);

        provenceCenterService.create(params);
    }

    public ProvenceCenter getDetails(Long id) {
        return provenceCenterMapper.getById(id);
    }

    public Long count(JProvenceQueryParams params) {
        return provenceCenterMapper.count(params);
    }

    public List<ProvenceCenter> query(JProvenceQueryParams params, DatagridPager pager) {
        return provenceCenterMapper.query(params, pager);
    }

    public List<String> getAreaCodeList(){return provenceCenterMapper.getAreaCodeList ();}


}
