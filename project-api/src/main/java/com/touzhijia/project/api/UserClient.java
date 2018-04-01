package com.touzhijia.project.api;


import com.github.pagehelper.PageInfo;
import com.touzhijia.project.domain.entity.Userinfo;
import retrofit.http.GET;

/**
 * @author leish
 * @create 2018-03-29
 **/
public interface UserClient {

  /**
   * 查询所有用户
   */
  @GET(value = "/users")
  PageInfo<Userinfo> findAllUserInfo(int page,int size);
}
