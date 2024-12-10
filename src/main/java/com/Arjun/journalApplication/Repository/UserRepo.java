package com.Arjun.journalApplication.Repository;

import com.Arjun.journalApplication.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUserName(String userName);

    void deleteByUserName(String userName);
}
