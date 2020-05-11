package com.pubinfo.memory.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pubinfo.memory.entity.User;
import com.pubinfo.memory.repository.UserRepository;
import com.pubinfo.memory.service.IUserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements IUserService {
}
