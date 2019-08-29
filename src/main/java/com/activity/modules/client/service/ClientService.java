package com.activity.modules.client.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.activity.modules.client.entity.vo.ClientVO;

import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/22 14:55
 * @description：
 * @modified By：
 * @version:
 */
public interface ClientService {

    //public void insert(ClientEntity clientEntity, ClientInformationEntity clientInformationEntity, ClientCompanyEntity clientCompanyEntity);

    public void insertClient(ClientVO clientVO);

    public void updateClient(ClientVO clientVO);

    public void deleteById(Integer linkId);

    public List<ClientVO> selectPage();


}
