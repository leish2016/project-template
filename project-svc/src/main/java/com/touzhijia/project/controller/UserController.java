package com.touzhijia.project.controller;


import com.github.pagehelper.PageInfo;
import com.touzhijia.project.dto.UserReq;
import com.touzhijia.project.entity.Userinfo;
import com.touzhijia.project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(description = "用户操作服务")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
    @ApiOperation(value = "添加用户")
    public Userinfo addUserInfo(@RequestBody @Valid UserReq userReq){
        return userService.addUser(userReq);
    }

    @DeleteMapping(value = "/user/{name}")
    @ApiOperation(value = "根据用户名删除用户")
    public boolean delUserInfo(@PathVariable("name") String name) {
        return userService.delUser(name);
    }

    @PutMapping(value = "/user/{id}")
    @ApiOperation(value = "修改用户")
    public Userinfo updateUserInfo(@PathVariable("id") Integer id,@RequestBody @Valid UserReq userReq) {
        return userService.updateUser(id,userReq);
    }

    @GetMapping(value = "/user/{name}")
    @ApiOperation(value = "根据用户名查询用户")
    public Userinfo findUserInfo(@PathVariable("name") String name) {
        return userService.findUserByName(name);
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "查询用户按传入条件")
    public Userinfo getUserInfo(Userinfo userInfo){
        return userService.getUserInfo(userInfo);
    }

    @GetMapping(value = "/users")
    @ApiOperation(value = "查询所有用户")
    public PageInfo<Userinfo> findAllUserInfo(int page,int size) {
        return userService.findAllUserInfo(page,size);
    }
}
