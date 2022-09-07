package com.bdqn.cjz.us.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.cjz.us.entity.User;
import org.springframework.stereotype.Repository;

/**
 * (User)表数据库访问层
 *
 * @author shenning
 * @description
 * @since 2022-03-02 13:35:47
 */
@Repository
public interface UserDao extends BaseMapper<User> {

}
