package com.Arjun.journalApplication.Cache;

import com.Arjun.journalApplication.Entity.ConfigJournalAppEntity;
import com.Arjun.journalApplication.Repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    ConfigJournalAppRepo repo;
    public Map<String,String> map=new HashMap<>();
    @PostConstruct
    public void inIt(){
     List<ConfigJournalAppEntity> all= repo.findAll();
     for(ConfigJournalAppEntity configJournalAppEntity:all){
         map.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
     }

    }

}
