
package com.example.bloodbank.model;
import jakarta.persistence.*;

@Entity
public class BloodRequest {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String patientName;
private String bloodGroup;
private String status;

public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public String getPatientName(){return patientName;}
public void setPatientName(String patientName){this.patientName=patientName;}
public String getBloodGroup(){return bloodGroup;}
public void setBloodGroup(String bloodGroup){this.bloodGroup=bloodGroup;}
public String getStatus(){return status;}
public void setStatus(String status){this.status=status;}
}
