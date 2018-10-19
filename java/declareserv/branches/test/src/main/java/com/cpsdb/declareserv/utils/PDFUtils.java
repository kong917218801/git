package com.cpsdb.declareserv.utils;

import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

public class PDFUtils {

    protected static final Logger logger = LoggerFactory.getLogger(PDFUtils.class);

    /**
     * 利用模板生成pdf
     */
    public static void fillTemplate(OutputStream out, String templatePath, String[] targets) throws IOException, DocumentException {
        logger.info("传递的参数：{}", targets);
        try (// 读取pdf模板
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            PdfReader reader = new PdfReader(PDFUtils.class.getResourceAsStream(templatePath));
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            int i = 0;
            for (String name : form.getFields().keySet()) {
                form.setField(name, targets[i++]);
                form.setSubstitutionFonts(Lists.newArrayList(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED)));
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.setFormFlattening(true);
            stamper.close();
            out.write(bos.toByteArray());
        }
    }

    /**
     * 利用模板生成pdf
     */
    public static void fillTemplate(String templatePath,String newPDFPath,String[] str) {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        BaseFont bf;
        try {
            // 输出流
            out = new FileOutputStream(newPDFPath);
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            int i = 0;
            Iterator<String> it = form.getFields().keySet().iterator();

            while (it.hasNext()) {
                String name = it.next();
                form.setField(name, str[i++]);
                form.setSubstitutionFonts(Lists.newArrayList(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED)));
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.setFormFlattening(true);
            stamper.close();

            out.write(bos.toByteArray());
            out.flush();
            out.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    public static void main(String[] args) {
    }
}