package com.example.myFirstProject.entry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "config_journal_app")
@Getter
@Setter
@NoArgsConstructor
@Component
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}
