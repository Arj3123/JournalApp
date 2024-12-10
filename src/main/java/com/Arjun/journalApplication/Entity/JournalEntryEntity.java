package com.Arjun.journalApplication.Entity;

import com.Arjun.journalApplication.Enum.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection ="journal_entries")
@NoArgsConstructor
public class JournalEntryEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;




}
