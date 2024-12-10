package com.Arjun.journalApplication.Repository;

import com.Arjun.journalApplication.Entity.JournalEntryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface JournalEntryRepo extends MongoRepository<JournalEntryEntity,ObjectId> {



}
