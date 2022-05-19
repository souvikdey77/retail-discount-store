package com.tech.retaildiscountstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author souvikdey
 * This is the model class of unique id created in mongo db
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "db_sequence")
public class DbSequence {
    @Id
    private String id;
    private int seq;
}
