package com.Arjun.journalApplication.Repository;

import com.Arjun.journalApplication.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<UserEntity> getUserForSA(){
        Query query=new Query();
        Criteria criteria=new Criteria();
        query.addCriteria(
                          criteria.andOperator(
                          Criteria.where("email").exists(true),
                          Criteria.where("sentimentAnalysis").is(true),
                                  Criteria.where("email").ne(null).ne("")
                          )
        );
        List<UserEntity>users=mongoTemplate.find(query,UserEntity.class);
        return users;
    }
}
