package com.bdqn.cjz.us.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.cjz.us.dao.UserDao;
import com.bdqn.cjz.us.entity.User;
import com.bdqn.cjz.us.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-02 13:35:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
