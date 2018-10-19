package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.baseservapi.utils.AreaUtils;
import com.cpsdb.declareserv.dao.OrganizAddressMapper;
import com.cpsdb.declareserv.entity.AreaLimit;
import com.cpsdb.declareserv.entity.OrganizAddress;
import com.cpsdb.declareserv.utils.OrganizUtils;
import com.cpsdb.gpsapi.api.BaiduGpsApi;
import com.cpsdb.gpsapi.dto.AddressToLocation;
import com.cpsdb.gpsapi.params.AddressToLocationParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李银
 * @ClassName OrganizAddressService
 * @Description
 * @date 2018-09-20 10:42:40
 */
@Service
public class OrganizAddressService {

    @Autowired
    private OrganizAddressMapper organizAddressMapper;

    @Autowired
    private AreaApi areaApi;

    @Autowired
    private AreaLimitService areaLimitService;

    @Autowired
    private BaiduGpsApi baiduGpsApi;

    public void insert(OrganizAddress organizAddress) {
        organizAddressMapper.insert(organizAddress);
    }

    public void update(OrganizAddress organizAddress) {
        organizAddressMapper.update(organizAddress);
    }

    public OrganizAddress getById(Long id) {
        return organizAddressMapper.getById(id);
    }

    public OrganizAddress getAddress(Long organizId, OrganizUtils.AdressType adressType) {
        return organizAddressMapper.getaddress(organizId, adressType.getValue());
    }

    /**
     * 获取地址的经纬度（调用百度api）
     * @param address 地址
     */
    private AddressToLocation getLatAndLong(String address) {
        AddressToLocation t = baiduGpsApi.addressToLocation(
                new AddressToLocationParams(address));
        return t;
    }

    /**
     * 插入申报区域地址信息
     *
     * @param organizId ： 服务处id
     * @param applyAddressCode
     */
    public void insertApplyAddress(Long organizId, String applyAddressCode,String address) {
        // 校验申请区域
        AreaUtils.Type type = AreaUtils.getType(applyAddressCode);
        AssertUtils.isTrue(type == AreaUtils.Type.district, new CustomException("申请区域未满足县区级！"));

        AreaLimit areaLimit = areaLimitService.getByAreaCode(applyAddressCode);
        AssertUtils.notNull(areaLimit, new CustomException("该区域暂无申请名额！"));
        AssertUtils.isTrue(areaLimit.getRemainNumber() > 0, new CustomException("该区域申请名额已满！"));
        // 扣减名额
        this.areaLimitService.update(areaLimit.setRemainNumber(areaLimit.getRemainNumber() - 1));

        ApiArea area = areaApi.getByCode(applyAddressCode);
        AssertUtils.notNull(area, new CustomException("地址信息填写有误！"));
        /*获取常驻地址经纬度*/
        AddressToLocation t = getLatAndLong(address);
        OrganizAddress organizAddress = new OrganizAddress()
                .setAddress(address)
                .setFkOrganizId(organizId)
                .setType(OrganizUtils.AdressType.declareAdress.getValue())
                .setAreaCode(area.getAreaCode())
                .setLatitude(t.getLocation().getLat())
                .setLongitude(t.getLocation().getLng());
        this.insert(organizAddress);

    }

    /**
     * 插入常驻地址地址信息
     * @param organizId ： 服务处id
     * @param liveAddressCode
     */
    public void insertOrUpdateLiveAddress(Long organizId, String liveAddressCode, String address) {
        ApiArea area = areaApi.getByCode(liveAddressCode);
        AssertUtils.notNull(area, new CustomException("地址信息填写有误！"));
        /*获取常驻地址经纬度*/
        AddressToLocation t = getLatAndLong(address);
        /*常驻地址是否存在 存在则更新 反之 添加*/
        OrganizAddress organizAddress =this.getAddress(organizId, OrganizUtils.AdressType.liveAdress);
        if( organizAddress == null ) {
            organizAddress = new OrganizAddress()
                    .setAddress(address)
                    .setFkOrganizId(organizId)
                    .setType(OrganizUtils.AdressType.liveAdress.getValue())
                    .setAreaCode(area.getAreaCode())
                    .setLatitude(t.getLocation().getLat())
                    .setLongitude(t.getLocation().getLng());
            this.insert(organizAddress);
        } else {
            organizAddress.setAddress(address)
                    .setFkOrganizId(organizId)
                    .setType(OrganizUtils.AdressType.liveAdress.getValue())
                    .setAreaCode(area.getAreaCode())
                    .setLatitude(t.getLocation().getLat())
                    .setLongitude(t.getLocation().getLng());
            this.update(organizAddress);
        }
    }


}