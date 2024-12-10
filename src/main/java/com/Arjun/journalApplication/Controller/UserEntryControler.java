package com.Arjun.journalApplication.Controller;

import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Services.UserEntryService;
import com.Arjun.journalApplication.Services.WeatherService;
import com.Arjun.journalApplication.WeatherApiResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryControler {
    @Autowired
    private UserEntryService userEntryService;

   @Autowired
   private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String userName=auth.getName();
        UserEntity old= userEntryService.findByName(userName);
        if(old!=null){
             old.setUserName(user.getUserName());
             old.setPassword(user.getPassword());
             userEntryService.saveNewUser(old);
             return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  @DeleteMapping
    public ResponseEntity<?>Delete(){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        userEntryService.deleteByName(auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
      Response response=weatherService.report("Mumbai");
      String greeting="";
      if(response!=null){
          greeting ="Weather feels like "+response.getCurrent().getFeelslike();
      }
        return new ResponseEntity<>("Hi "+auth.getName()+" "+greeting,HttpStatus.OK);
  }


}
