package com.Arjun.journalApplication.Services;

import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Component
public class UserEntryService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService;
    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void saveNewUser(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER"));
        userRepo.save(userEntity);
        emailService.sendEmail(userEntity.getEmail(),"Sign in Confirmation","Welcome to Journal App");
    }
    public void saveEntry(UserEntity userEntity){
        userRepo.save(userEntity);
    }
    public Optional<UserEntity> getById(ObjectId id){
        return userRepo.findById(id);
    }
    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }
    public void deleteByName(String userName){userRepo.deleteByUserName(userName);}
    public void delete(ObjectId id){
        userRepo.deleteById(id);
    }
    public UserEntity findByName(String user){
        return userRepo.findByUserName(user);
    }

    public void saveAdmin(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }
}
