package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.BeanUtils;
import com.cpsdb.base.common.CPSDateUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dto.JCodeDetails;
import com.cpsdb.declareserv.entity.DeclarerCodeDetail;
import com.cpsdb.declareserv.service.DeclarerCodeDetailService;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.excel.config.Header;
import com.cpsdb.excel.writer.XmlExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarerCodeDetailManager {


    @Autowired
    private DeclarerCodeDetailService declarerCodeDetailService;

    public void declarerExport(Long declarerId, HttpServletResponse response) throws IOException {
        List<DeclarerCodeDetail> declarerCodeDetails = declarerCodeDetailService.getDetailsByDeclarerId(declarerId);

        String systemName = "扫描记录-" + CPSDateUtils.toString(new Date());
        systemName = new String(systemName.getBytes(), "ISO8859-1");

        List<Header> headerList = Header.of(
                Header.of("id", "扫描ID"),
                Header.of("address", "扫描地址"),
                Header.of("createTime", "扫描时间")
        );

        //得到响应流并发送
        response.reset();
        try (OutputStream out = response.getOutputStream()) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + systemName + ".xls\"");
            response.setContentType("application/octet-stream;charset=UTF-8");

            try (XmlExcel excel = XmlExcel.of(out, headerList)
                    .start()
                    .appendBody(declarerCodeDetails)
                    .finish()) {
            }
            out.flush();
        } catch (Exception e) {
            throw new CustomException("下载失败");
        }
    }

    public List<JCodeDetails> getCodeDetails(Long declarerId) {
        List<DeclarerCodeDetail> declarerCodeDetails = declarerCodeDetailService.getDetailsByDeclarerId(declarerId);
        return declarerCodeDetails.stream()
                .map(s -> BeanUtils.copyObject(s, new JCodeDetails()
                        .setAddress(s.getAddress())
                        .setId(s.getId())
                        .setCreateTime(s.getCreateTime().getTime()))
                ).collect(Collectors.toList());
    }
}
