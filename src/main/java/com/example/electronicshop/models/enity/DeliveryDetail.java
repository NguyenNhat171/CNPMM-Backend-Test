package com.example.electronicshop.models.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetail {
    private String receiveName;
    private String receivePhone;
    private String receiveProvince;
    private String receiveDistrict;
    private String receiveWard;
    private String receiveAddress;
}

