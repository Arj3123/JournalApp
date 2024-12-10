package com.Arjun.journalApplication.Scheduler;

import com.Arjun.journalApplication.Entity.JournalEntryEntity;
import com.Arjun.journalApplication.Entity.UserEntity;
import com.Arjun.journalApplication.Enum.Sentiment;
import com.Arjun.journalApplication.Repository.UserRepoImpl;
import com.Arjun.journalApplication.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepoImpl userRepo;
    @Scheduled(cron="0 0 9 * * SUN")
    public void fetchUsersAndSendMail(){
        List<UserEntity> users =userRepo.getUserForSA();
        for(UserEntity user: users){
         List<JournalEntryEntity> journalEntries=user.getJournalEntries();
        List<Sentiment> sentiments= journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> map= new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null){
                    map.put(sentiment,map.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFreq=null;
            int c=0;
            for(Map.Entry<Sentiment,Integer> enrty: map.entrySet()){
                if(enrty.getValue()>c){
                    c= enrty.getValue();
                    mostFreq=enrty.getKey();
                }
            }
            if(mostFreq!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days" ,mostFreq.toString());
            }
         }
    }

}
