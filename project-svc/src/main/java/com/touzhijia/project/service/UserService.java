package com.touzhijia.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.touzhijia.project.common.BizException;
import com.touzhijia.project.dto.UserReq;
import com.touzhijia.project.entity.Userinfo;
import com.touzhijia.project.mapper.UserinfoMapper;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
@Slf4j
public class UserService {
    @Autowired
    UserinfoMapper userInfoMapper;



    public void userNameExists(String name){
        Example example = new Example(Userinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Userinfo> userInfos = userInfoMapper.selectByExample(example);
        if(userInfos.size()>0){
            BizException.throwBizException(String.format("{%s}用户名已存在.", name));
        }
    }

    
    public Userinfo addUser(UserReq userReq) {
        userNameExists(userReq.getName());
        Userinfo u = new Userinfo();
        BeanUtils.copyProperties(userReq, u);
        u.setCreateAt(new Date());
        userInfoMapper.insert(u);
        return userInfoMapper.selectOne(u);
    }

    
    public boolean delUser(String name) {
        //执行删除操作
        Userinfo u = new Userinfo();
        u.setName(name);
        userInfoMapper.delete(u);
        return true;
    }


    public Userinfo updateUser(Integer id, UserReq userReq) {
        //执行修改用户操作
        Userinfo u = userInfoMapper.findById(id);
        if (u == null) {
            BizException.throwBizException(String.format("{id=%s}用户不存在.", id));
        }
        userNameExists(userReq.getName());
        u.setName(userReq.getName());
        u.setPhone(userReq.getPhone());
        userInfoMapper.updateByPrimaryKeySelective(u);
        return userInfoMapper.findById(id);
    }


    public Userinfo findUserByName(String name) {
        //执行查询
        Userinfo u = new Userinfo();
        u.setName(name);
        return userInfoMapper.selectOne(u);
    }


    public Userinfo getUserInfo(Userinfo userInfo) {
        //执行查询
        return userInfoMapper.selectOne(userInfo);
    }

    public PageInfo<Userinfo> findAllUserInfo(int page, int size) {
        //执行查询
        return PageHelper.startPage(page, size).doSelectPageInfo(()->
                userInfoMapper.selectAll()
        );
    }
}
