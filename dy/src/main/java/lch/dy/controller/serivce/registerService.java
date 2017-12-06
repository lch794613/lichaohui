package lch.dy.controller.serivce;

import lch.dy.controller.Entity.user;
import lch.dy.controller.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class registerService {
    @Autowired
    private userMapper userMapper;

    public void registerUser(String name,String password){
        userMapper.register(name,password);
    }
    public user queryByName(String name){
        return userMapper.getUserByName(name);
    }
}
