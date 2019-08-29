package com.activity.modules.client.service.impl;



import com.activity.common.utils.UUIDUtils;
import com.activity.modules.client.entity.vo.ClientVO;
import com.activity.modules.client.mapper.ClientMapper;
import com.activity.modules.client.service.ClientService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/22 14:56
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    public void insertClient(ClientVO clientVO) {
        String uuid = UUIDUtils.getUUID();
        if(clientVO.getCompanyCode() == null || "".equals(clientVO.getCompanyCode())){
            clientVO.setCompanyCode(uuid);
        }
      /*  Date time=clientVO.getAccessTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.format(time);*/
        clientMapper.insertClient(clientVO);
    }

    @Override
    public void updateClient(ClientVO clientVO) {
        clientMapper.updateClient(clientVO);
    }

    @Override
    public void deleteById(Integer linkId) {
        clientMapper.deleteById(linkId);
    }

    @Override
    public List<ClientVO> selectPage() {
        List<ClientVO> clientList = clientMapper.selectPage();
        return clientList;
    }



    public void delete(Integer id) {
        clientMapper.delete(id);
    }

   /* @Override
    public void insert(ClientEntity clientEntity, ClientInformationEntity clientInformationEntity, ClientCompanyEntity clientCompanyEntity) {
        if(clientEntity.getClientType() != null && (clientEntity.getClientType()).equals("0")){
            System.out.println("clientEntity = " + clientEntity);
            String linkCode = UUIDUtils.getUUID();
            clientInformationEntity.setLinkCode(linkCode);
            clientMapper.insertClientInformation(clientInformationEntity);
            System.out.println("linkId = " + clientInformationEntity.getLinkId());
            clientEntity.setClientId(clientInformationEntity.getLinkId());
            clientMapper.insertClient(clientEntity);
            if (clientCompanyEntity.getCompanyCode() == null) {
                clientCompanyEntity.setCompanyCode(linkCode);
                clientMapper.insertClientCompany(clientCompanyEntity);
            }
        }
    }*/
}
