package com.example.demo2_sp.service;

import com.example.demo2_sp.entity.UserType.UserType;
import com.example.demo2_sp.entity.user.User;
import com.example.demo2_sp.repository.UserRepository;
import com.example.demo2_sp.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    public UserService(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }
    public boolean logout(int UID){
        User user=userRepository.findByUID(UID);
        UserType userType=userTypeRepository.findByUID(UID);
        if (user!=null){
            userRepository.delete(user);
            userTypeRepository.delete(userType);
            return true;
        }
        return false;
    }
    //密码验证
    public User isPasswordCorrect(String phone, String user_password) {
        // 通过手机号码查询用户记录
        User user = userRepository.findByPhone(phone);
        // 如果找到了用户记录
        if (user != null && user.getUser_password().equals(user_password)) {
            // 比较输入的密码和数据库中的密码是否匹配
            return user;
        }
        // 如果未找到用户记录，或者密码不匹配，返回 false
        return null;
    }
    public User login(String phone){
        return userRepository.findByPhone(phone);
    }
    //注册
    public User registerUser(User userRegister) {
        // 检查是否存在具有相同电话号码的用户
        User existingUser = userRepository.findByPhone(userRegister.getPhone());
        User existingUser1 = userRepository.findByIdCard(userRegister.getId_card());
        // 保存新用户记录
        if (existingUser == null&& existingUser1 ==null) {
            userRepository.save(userRegister);
            UserType userType=new UserType(userRegister.getUID(),1);
            System.out.println(userType);
            userTypeRepository.save(userType);
            return userRegister;
        }
        return null;
    }
    //修改密码，保存新值
    public User saveUser(User user){
        return userRepository.save(user);
    }
    //修改密码
    public  User ChangePassword(String phone){
    return userRepository.findByPhone(phone);
    }
}