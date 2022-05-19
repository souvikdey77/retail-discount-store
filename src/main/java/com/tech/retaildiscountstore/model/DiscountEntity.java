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
 * DiscountEntity model class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userdetails")
public class DiscountEntity {

    @Transient
    public static final String SEQUENCE_NAME = "discount_sequence";

    @Id
    private Integer userId;
    private String userName;
    private String userType;
    private String productType;
    private Integer numberOfYear;
    private Double actualPrice;
    private Double discountedPrice;

}
