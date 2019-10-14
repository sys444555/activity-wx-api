package com.activity.modules.room.service.impl;


import com.activity.common.exception.JcException;
import com.activity.common.utils.AMapUtils;
import com.activity.common.utils.LngLat;
import com.activity.modules.business.entity.po.BusinessPo;
import com.activity.modules.business.mapper.BusinessMapper;
import com.activity.modules.pay.entity.PayEntity;
import com.activity.modules.pay.mapper.PayMapper;
import com.activity.modules.room.entity.RoomDetailVO;
import com.activity.modules.room.entity.RoomVO;
import com.activity.modules.room.entity.po.RoomPO;
import com.activity.modules.room.entity.po.RoomPic;
import com.activity.modules.room.mapper.RoomMapper;
import com.activity.modules.room.service.RoomService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:31
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, RoomVO> implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private BusinessMapper businessMapper;

    @Resource
    private PayMapper payMapper;

    DecimalFormat df = new DecimalFormat("#.0");

   @Override
    public List<RoomPO> selectPage(Double latitude , Double longitude) {
        List<RoomPO> list = roomMapper.roomList();

        for(int i = 0; i<list.size(); i++){

            LngLat start = null;
            LngLat end = null;

            RoomPO roomPO = list.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            roomPO.setStartTimeStr(sdf.format(roomPO.getStartTime()));

            //'聊天', '游戏', '推广', '招聘', '合伙人'
            switch (roomPO.getRoomType()){
                case "1" : roomPO.setTypeStr("聊天");break;
                case "2" : roomPO.setTypeStr("游戏");break;
                case "3" : roomPO.setTypeStr("推广");break;
                case "4" : roomPO.setTypeStr("招聘");break;
                case "5" : roomPO.setTypeStr("合伙人");break;
                default:  roomPO.setTypeStr("读取失败");break;
            }

            start = new LngLat(longitude,latitude);
            //当前店铺的经纬度
            end = new LngLat(roomPO.getBusinessPo().getLongitude(),roomPO.getBusinessPo().getLatitude());

            roomPO.setKm(Double.parseDouble(df.format((AMapUtils.calculateLineDistance(start,end)/1000))));

        }
        return list;
    }

    @Override
    public void createRoom(RoomVO roomVO, String openId) {
        //获取新增后返回的id值
        Integer roomId = roomMapper.createRoom(roomVO);
        System.out.println("roomId = " + roomVO.getId());
        if(roomId == null || roomId == 0){
            throw new JcException("新建房间失败");
        }
        roomMapper.createRoomer(roomVO.getId(), openId);

    }

    @Override
    public boolean hasRoom(String openId) {
        if(openId != null && openId != ""){
            //判断数据库是否存在当前用户的房间
            Integer count = roomMapper.hasRoom(openId);
            //当前用户存在房间返回true,否则false
            if(count != 0){
                return true;
            }else {
                return false;
            }
        }else {
            throw new JcException("openId为空");
        }


    }

    /**
     *  获取个人所有房间
     * @param openId
     * @return
     */
    @Override
    public List<RoomPO> getUserRooms(Integer status, String openId) {
        //如果状态值为0,查找全部房间，否则按照状态码查找对应的房间
        return roomMapper.getUserRooms(status, openId);
    }

    /**
     * 支付成功后更新个人房间状态
     * @param openId
     * @param roomId
     * @param status
     */
    @Override
    public void updateRoomStatus( Integer roomId, Integer status,String openId) {
        roomMapper.updateRoomStatus(roomId,status,openId);
    }

    @Override
    public void changeUserAccount(String openId, Integer id, Integer roomJoinAccount) {
        Integer count = roomMapper.changeUserAccount(id, roomJoinAccount);
        if(count != null & count != 0){
            roomMapper.createJoiner(id, openId);
        }
    }

    @Override
    public boolean isPay(Integer roomId, String openId) {
        Integer pay = roomMapper.isPay(roomId, openId);
        System.out.println("pay1 = " + pay);
        if(pay != null && pay == 1){
            System.out.println("pay = " + pay);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public RoomDetailVO getRoomDetailById(Integer id , String openId) {

        Integer payStatus = 99;

        List<RoomVO> roomVO = roomMapper.selectList(new EntityWrapper<RoomVO>().eq("id",id));

        List<RoomPic> roomPic = roomMapper.selectPic(id);

        BusinessPo businessPo = businessMapper.selectById(roomVO.get(0).getJoinId());

        roomVO.get(0).setBusinessPo(businessPo);

        List<PayEntity> payEntities = payMapper.selectList(new EntityWrapper<PayEntity>().eq("open_id", openId).eq("room_id", id));

        if(payEntities.size() == 1){
            PayEntity payEntity = payEntities.get(0);
            //返回 0则未支付， 返回1则代表支付了，99代表没操作
            payStatus = payEntity.getPayStatus();
        }else if(payEntities.size() == 0){
            // -1 则代表数据多条或者没有数据，则均为数据异常
            payStatus = 0;
        }else{
            // -1 则代表数据多条，则均为数据异常
            payStatus = -1;
        }

        return new RoomDetailVO(roomVO.get(0),roomPic,payStatus);
    }

    @Override
    public Integer checkPwd(String id, String pwd) {
        List<RoomVO> roomVOS = roomMapper.selectList(new EntityWrapper<RoomVO>().eq("id",id));
        if(roomVOS.size() != 1){
            return 0;
        }
        return  roomVOS.get(0).getPassword().equals(DigestUtils.md5DigestAsHex(pwd.getBytes())) ? 1 : 2;
    }

    @Override
    public List<RoomPO> selectPageById(Integer roomId,Double latitude , Double longitude) {
        List<RoomPO> list = roomMapper.roomListById(roomId);

        for(int i = 0; i<list.size(); i++){

            LngLat start = null;
            LngLat end = null;

            RoomPO roomPO = list.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            roomPO.setStartTimeStr(sdf.format(roomPO.getStartTime()));

            //'聊天', '游戏', '推广', '招聘', '合伙人'
            switch (roomPO.getRoomType()){
                case "1" : roomPO.setTypeStr("聊天");break;
                case "2" : roomPO.setTypeStr("游戏");break;
                case "3" : roomPO.setTypeStr("推广");break;
                case "4" : roomPO.setTypeStr("招聘");break;
                case "5" : roomPO.setTypeStr("合伙人");break;
                default:  roomPO.setTypeStr("读取失败");break;
            }

            start = new LngLat(longitude,latitude);
            //当前店铺的经纬度
            end = new LngLat(roomPO.getBusinessPo().getLongitude(),roomPO.getBusinessPo().getLatitude());

            roomPO.setKm(Double.parseDouble(df.format((AMapUtils.calculateLineDistance(start,end)/1000))));

        }
        return list;
    }

}
