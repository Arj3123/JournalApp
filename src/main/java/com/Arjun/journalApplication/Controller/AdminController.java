package com.Arjun.journalApplication.Controller;

import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> list=userEntryService.getAll();
        if(list!=null&&!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody UserEntity user){
        userEntryService.saveAdmin(user);
    }

}
