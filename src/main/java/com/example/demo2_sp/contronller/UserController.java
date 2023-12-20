package com.example.demo2_sp.contronller;

import com.example.demo2_sp.entity.user.User;
import com.example.demo2_sp.entity.user.User_Password_Login;
import com.example.demo2_sp.entity.user.User_Verification_Login;
import com.example.demo2_sp.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //验证码登录
    @PostMapping("/verification_code_login")
    public ResponseEntity<?> verification_code_loginUser(@RequestBody User_Verification_Login userVerificationLogin) {
        User user=userService.login(userVerificationLogin.getPhone());
        if (user != null) {
            // 登录成功，将用户对象转换为 JSON 并返回

            return getResponseEntity(user);
        }else {
            // 登录失败，返回一个属性都为空的用户对象的 JSON
            return getResponseEntity(null);
        }
    }
    //密码登录
    @PostMapping("/password_login")
    public ResponseEntity<?> password_login_User(@RequestBody User_Password_Login login) {
        String phone = login.getPhone();
        String user_password = login.getUser_password();
        // 调用 UserService 中的方法验证密码是否正确
        User user = userService.isPasswordCorrect(phone, user_password);

        if (user != null) {
            // 登录成功，将用户对象转换为 JSON 并返回

            return getResponseEntity(user);
        }else {
            // 登录失败，返回一个属性都为空的用户对象的 JSON
            return getResponseEntity(null);
        }
    }

    //注册
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user_register) {
        try {
            // 用 UserService 中的方法将用户数据插入数据库
            User reg = userService.registerUser(user_register);
            if (reg != null) {

                return getResponseEntity(reg);
            } else {
                // 如果注册失败，返回适当的错误响应
                return getResponseEntity(null);
            }
        } catch (Exception e) {
            // 处理插入数据库时可能出现的异常
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to register user");
        }
    }
    //修改密码
    @PostMapping("/change_password")
    public ResponseEntity<?> changePassword(@RequestBody User_Password_Login changePasswordRequest) {
        String phone = changePasswordRequest.getPhone();
        String newPassword = changePasswordRequest.getUser_password();
        // 1. 使用电话号码从数据库中查找用户对象
        User user = userService.ChangePassword(phone);

        if (user != null) {
                //  如果密码匹配，将密码更新为新密码
                user.setUser_password(newPassword);
                User user1=userService.saveUser(user);
                // 返回成功响应
            System.out.println(user1.toString());
                return getResponseEntity(user1);
        }
        else {
            // 登录失败，返回一个属性都为空的用户对象的 JSON
            return getResponseEntity(null);
        }
    }
    //转换为json
    @NotNull
    private ResponseEntity<?> getResponseEntity(User user) {
        //返回空json
        if (user == null) {
            User emptyUser = new User();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String emptyUserJson = objectMapper.writeValueAsString(emptyUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(emptyUserJson);
            } catch (JsonProcessingException e) {
                // 处理 JSON 转换异常
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to convert empty user to JSON");
            }
        }
        else{
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String userJson = objectMapper.writeValueAsString(user);
                return ResponseEntity.ok(userJson);
            } catch (JsonProcessingException e) {
                // 处理 JSON 转换异常
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to convert user to JSON");
            }
        }
    }
    // 注销
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody int UID) {
        // 调用 UserService 中的方法来尝试注销用户
        boolean logoutSuccessful = userService.logout(UID);

        if (logoutSuccessful) {
            // 如果注销成功，返回成功的响应
            return ResponseEntity.ok("用户注销成功");
        } else {
            // 如果注销失败，返回相应的错误响应
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户不存在或注销失败");
        }
    }
}



