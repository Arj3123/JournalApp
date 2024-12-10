package com.Arjun.journalApplication.Services;

import com.Arjun.journalApplication.Entity.JournalEntryEntity;
import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Transactional
    public void saveEntry(JournalEntryEntity journalEntryEntity,String userName){
        UserEntity user = userEntryService.findByName(userName);
        JournalEntryEntity saved = journalEntryRepo.save(journalEntryEntity);
        user.getJournalEntries().add(saved);
        userEntryService.saveEntry(user);

    }
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserEntryService userEntryService;


    public void saveEntry(JournalEntryEntity journalEntryEntity){
        journalEntryRepo.save(journalEntryEntity);

    }
    public Optional<JournalEntryEntity> getById(ObjectId id){
        return journalEntryRepo.findById(id);
   }
    public List<JournalEntryEntity> getAll() {
        return journalEntryRepo.findAll();
    }
    public void delete(ObjectId id, String userName){
        UserEntity user=userEntryService.findByName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userEntryService.saveEntry(user);
        journalEntryRepo.deleteById(id);
    }
    public List<JournalEntryEntity>findByUserName(String userName){
       UserEntity user=userEntryService.findByName(userName);
       return user.getJournalEntries();
    }
}
