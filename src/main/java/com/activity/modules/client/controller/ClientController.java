package com.activity.modules.client.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.activity.common.utils.ResponseUtil;
import com.activity.modules.client.entity.vo.ClientVO;
import com.activity.modules.client.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/22 10:25
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/client/api")
@Api(description = "客户")
public class ClientController {

    @Autowired
    private ClientService clientService;

   @RequestMapping(value = "/insert",method = RequestMethod.POST)
   public ResponseUtil insert(ClientVO clientVO){
       clientService.insertClient(clientVO);
       return ResponseUtil.success();
   }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseUtil update(ClientVO clientVO){
        clientService.updateClient(clientVO);
        return ResponseUtil.success();
    }

    @RequestMapping(value="/delete/{linkId}",method=RequestMethod.POST)
    public ResponseUtil deleteById(@PathVariable Integer linkId){
        clientService.deleteById(linkId);
        return ResponseUtil.success();
    }


    @RequestMapping(value = "/selectPage",method = RequestMethod.GET)
    public ResponseUtil selectPage(Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<ClientVO> list = clientService.selectPage();
        PageInfo<ClientVO> pageInfo = new PageInfo<>(list);
        return ResponseUtil.success(pageInfo);
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
