package com.Arjun.journalApplication.Services;

import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDetailImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepo.findByUserName(username);
        if(user!=null){
         UserDetails userDetails=   User.builder()
                                    .username(user.getUserName())
                                    .password(user.getPassword())
                                    .roles(user.getRoles().toArray(new String[0]))
                                     .build();
         return userDetails;
        }
        throw new UsernameNotFoundException("user not found with"+ username);
    }
}
