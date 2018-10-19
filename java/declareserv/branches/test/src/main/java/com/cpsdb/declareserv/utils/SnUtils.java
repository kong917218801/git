package com.cpsdb.declareserv.utils;

import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.utils.AreaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SnUtils {

    @Autowired
    private AreaApi areaApi;

    public static String getOrganizSn(String organizId) {
        return "Cps-Ds(国)" + leftFill(organizId, "0", 5);
    }

    public static Long resolveOrganiz(String sn) {
        if (sn != null) {
            return CPSStringUtils.parseLong(sn.replaceAll("Cps-Ds(国)", ""));
        }
        return null;
    }

    public static String getAuthofficer(String authofficerId) {
        return "CpsCo" + leftFill(authofficerId, "0", 5);
    }

    public static String getDeclarerSn(String declareId) {
        return "CpsDo" + leftFill(declareId, "0", 7);
    }

    public static String getProvinceSn(Long id,String areaCode) {
        return "CpsPsc(" +AreaUtils.getShortOfProvence(areaCode) +")"+ leftFill(id.toString(), "0", 4);
    }

    public static Long resolveDeclarer(String sn) {
        if (sn != null) {
            return CPSStringUtils.parseLong(sn.replaceAll("CpsDo", ""));
        }
        return null;
    }


    /**
     * 左填充
     *
     * @param source 需要被填充的字符串
     * @param target 填充使用的字符串
     * @param length 填充至多少位，当source的位数本身大于times时，不做任何事；
     * @return
     */
    public static String leftFill(String source, String target, int length) {
        source = StringUtils.defaultIfBlank(source, StringUtils.EMPTY);
        if (source.length() >= length) {
            return source;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - source.length(); i++) {
            sb.append(target);
        }
        return sb.append(source).toString();
    }

    public static String replaceIdNumber(String idNumber) {
        String reaple = idNumber.substring(6, 14);
        return idNumber.replace(reaple, "********");
    }

    public static String replaceCellPhone(String cellphone) {
        String reaple = cellphone.substring(4, 7);
        return cellphone.replace(reaple, "****");
    }


    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).collect(Collectors.toList());
        System.out.println(squaresList);


        Long count =numbers.stream().filter(s ->s>2).mapToLong(a ->a.longValue()).sum();
        System.out.println(count);

        LocalDateTime.now();

    }

//        List<String> test= Lists.newArrayList("Google","Baidu","Xinlang","Tencent");
//
//        Collections.sort(test,(s,s1) ->s.compareTo(s1));
//        System.out.println(test);

//        String[] str = { "3", "4", "5" };
//        StringBuilder sb = new StringBuilder();
//        if (str != null && str.length > 0) {
//
//            for (String s : str) {
//                sb.append(s + ",");  //循环遍历数组中元素，添加到 StringBuilder 对象中
//            }
//        }
//        if (sb.length() > 0)
//            sb.deleteCharAt(sb.length() - 1); //调用 字符串的deleteCharAt() 方法,删除最后一个多余的逗号
//        System.out.println(sb.toString());
}
