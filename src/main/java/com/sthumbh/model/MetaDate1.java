package com.sthumbh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDate1 {
    private String status;
    private String code;
    private String message;
    private String version;

}
