package com.tech.retaildiscountstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author souvikdey
 * UserTypeEntity model class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserModel {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Integer _id;
    private String username;
    private String password;
    private String userType;
    private int numberOfYears;
}
