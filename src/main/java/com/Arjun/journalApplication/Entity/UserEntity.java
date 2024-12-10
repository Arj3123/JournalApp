package com.Arjun.journalApplication.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection ="users")
@NoArgsConstructor
public class UserEntity {
        @Id
        private ObjectId id;
        @Indexed(unique = true)
        @NonNull
        private String userName;
        private String email;
        private boolean sentimentAnalysis;
        @NonNull
        private String password;

        private List<String>roles;

        @DBRef
        private List<JournalEntryEntity> journalEntries=new ArrayList<>();
}