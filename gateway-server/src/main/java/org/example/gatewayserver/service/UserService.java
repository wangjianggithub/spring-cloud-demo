//package org.example.gatewayserver.service;
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 1. 验证用户名
//        if (!"fox".equals(username)) {
//            throw new UsernameNotFoundException("用户不存在: " + username);
//        }
//
//        // 2. 加密密码
//        String password = passwordEncoder.encode("123456");
//
//        // 3. 返回用户信息
//        return User.builder()
//                .username("fox")
//                .password(password)
//                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
//                .build();
//    }
//}
//
