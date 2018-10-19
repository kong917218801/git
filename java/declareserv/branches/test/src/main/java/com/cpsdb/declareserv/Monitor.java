package com.cpsdb.declareserv;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统监控专用
 *
 * @author 李银 on 2018年3月30日 09:29:51
 */
@RestController
public class Monitor {

    @RequestMapping(value = "monitor")
    String monitor() {
        return "I'm health!";
    }

    @RequestMapping("/")
    String monitorX() {
        return "I'm health!";
    }
}
