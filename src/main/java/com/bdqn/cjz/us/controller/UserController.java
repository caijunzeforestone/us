package com.bdqn.cjz.us.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.cjz.us.entity.User;
import com.bdqn.cjz.us.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author shenning
 * @description
 * @since 2022-03-02 13:35:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/index")
    public IPage<User> index(
            @RequestParam int pageNum,
            @RequestParam int pageSize,
            @RequestParam String username
    ) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        return userService.page(page, queryWrapper);
    }

    @DeleteMapping("/del/{id}")
    public boolean del(@PathVariable int id) {
        return userService.removeById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @PostMapping("/batch")
    public boolean batch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }
}
