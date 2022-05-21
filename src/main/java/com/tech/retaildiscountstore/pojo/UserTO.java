package com.tech.retaildiscountstore.pojo;

import lombok.*;

/**
 * @author souvikdey
 * UserTO transfer Object
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTO {
    private String userName;
    private String password;
    private String userType;
    private int numberOfYears;
}
