package com.tech.retaildiscountstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author souvikdey
 * UserTO transfer Object
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTO {
    private String userName;
    private String userType;
    private int numberOfYears;
}
