package com.zaseki;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class Member {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  private String name;
  
  private String yomigana;
  
  private int division_id;
  
  @ManyToOne
  @JoinColumn(name="division_id",referencedColumnName="id", insertable=false, updatable=false)
  private Division division;
  
  private String floor;
  
  private String extensionnumber;
  
  public boolean yomiganaIs(String yomigana) {
    return this.yomigana.equals(yomigana);
  }
  
  public boolean divisionIs(String div) {
    return this.division.getDiv().equals(div);
  }
}
