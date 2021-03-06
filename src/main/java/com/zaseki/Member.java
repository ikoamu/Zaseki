package com.zaseki;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "member")
@EqualsAndHashCode
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "yomigana")
  private String yomigana;

  @Column(name = "division")
  private String division;

  @Column(name = "floor")
  private String floor;

  @Column(name = "extensionnumber")
  private String extensionNumber;

  public Member() {
    super();
  }

  public Member(Integer id, String name, String yomigana, String division, String floor, String extensionNumber) {
    super();
    this.id = id;
    this.name = name;
    this.yomigana = yomigana;
    this.division = division;
    this.floor = floor;
    this.extensionNumber = extensionNumber;
  }

  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getYomigana() {
    return yomigana;
  }

  public void setFurigana(String yomigana) {
    this.yomigana = yomigana;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getExtensionNumber() {
    return extensionNumber;
  }

  public void setExtensionNumber(String extensionNumber) {
    this.extensionNumber = extensionNumber;
  }

  public boolean furiganaIs(String yomigana) {
    return this.yomigana.equals(yomigana);
  }

  public boolean divisionIs(String div) {
    return this.division.equals(Division.from(div).name());
  }
}
