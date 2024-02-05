package com.sthumbh.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "blood_details")
@Data

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BloodDetailsEntity {

    @Id
    public String id;
    private String bloodType;
    private List<String> donateBloodTo;
    private List<String> receiveBloodFrom;

}
