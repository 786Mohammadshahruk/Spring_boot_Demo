package com.sthumbh.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponseModel {

    private MetaDate metaDate;
    private ResourceData resourceData;


}
