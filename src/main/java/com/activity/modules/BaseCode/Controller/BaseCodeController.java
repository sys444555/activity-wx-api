package com.activity.modules.BaseCode.Controller;

import com.activity.common.utils.BaseCodeUtil;
import com.activity.common.utils.ResponseUtil;
import com.activity.common.utils.WebGetTokenUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/9/17 9:13
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/wx/api")
public class BaseCodeController {

    @RequestMapping(value = "/getBaseCode", method = RequestMethod.POST)
    public ResponseUtil getBaseCode(String sceneStr){
        String token = WebGetTokenUtils.getToken();
        BaseCodeUtil.getminiqrQr(sceneStr, token);
        return ResponseUtil.success();
    }

}
