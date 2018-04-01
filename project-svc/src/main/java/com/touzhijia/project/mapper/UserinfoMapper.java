package com.touzhijia.project.mapper;


import com.touzhijia.project.domain.entity.Userinfo;
import tk.mybatis.mapper.common.Mapper;

public interface UserinfoMapper extends Mapper<Userinfo> {

  /**
   * 根据ID查用户信息
   * @param id
   * @return
   */
  Userinfo findById(Integer id);
}