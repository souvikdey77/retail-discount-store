package com.tech.retaildiscountstore.pojo;

import lombok.*;

/**
 * @author souvikdey
 * OrderDetailsTO transfer object
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsTO {
    private String userName;
    private Double orderPrice;
}
