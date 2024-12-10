package com.Arjun.journalApplication.Controller;


import com.Arjun.journalApplication.Entity.JournalEntryEntity;
import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Services.JournalEntryServices;
import com.Arjun.journalApplication.Services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;
    @Autowired
    private UserEntryService userEntryService;

    @PostMapping
    public ResponseEntity<JournalEntryEntity> createEntry(@RequestBody JournalEntryEntity journalEntryEntity) {
        try {
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String userName= auth.getName();
            journalEntryEntity.setDate(LocalDateTime.now());
            journalEntryServices.saveEntry(journalEntryEntity,userName);
            return new ResponseEntity<>(journalEntryEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllByUserName(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName= auth.getName();
        UserEntity user=userEntryService.findByName(userName);
        List<JournalEntryEntity> list= user.getJournalEntries();
        if(list!=null) return new ResponseEntity<>(list,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntryEntity> getById(@PathVariable ObjectId myId){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName= auth.getName();
        List<JournalEntryEntity> list=journalEntryServices.findByUserName(userName).stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!list.isEmpty()){
            Optional<JournalEntryEntity> journalEntry=journalEntryServices.getById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String userName= auth.getName();
        journalEntryServices.delete(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     @PutMapping("id/{myId}")
    public ResponseEntity<?>update(@RequestBody JournalEntryEntity newEntry,@PathVariable ObjectId myId){
         Authentication auth= SecurityContextHolder.getContext().getAuthentication();
         String userName= auth.getName();
         List<JournalEntryEntity> list=journalEntryServices.findByUserName(userName).stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
         if(!list.isEmpty()) {
             Optional<JournalEntryEntity> journalEntry = journalEntryServices.getById(myId);
             if (journalEntry.isPresent()) {
                 JournalEntryEntity temp = journalEntry.get();
                 temp.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : temp.getContent());
                 temp.setName(newEntry.getName() != null && !newEntry.getName().equals("") ? newEntry.getName() : temp.getName());
                 journalEntryServices.saveEntry(temp);
                 return new ResponseEntity<>(temp, HttpStatus.OK);
             }
         }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
}
