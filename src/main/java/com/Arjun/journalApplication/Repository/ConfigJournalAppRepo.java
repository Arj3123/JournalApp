package com.Arjun.journalApplication.Repository;

import com.Arjun.journalApplication.Entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
