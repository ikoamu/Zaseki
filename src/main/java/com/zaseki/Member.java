package com.zaseki;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private int id;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "yomigana")
  @Getter
  @Setter
  private String yomigana;

  @Column(name = "division_id")
  @Getter
  @Setter
  private int divisionId;

  @Column(name = "floor")
  @Getter
  @Setter
  private String floor;

  @Column(name = "extensionnumber")
  @Getter
  @Setter
  private String extensionNumber;

  public boolean yomiganaIs(String yomigana) {
    return this.yomigana.equals(yomigana);
  }

  public boolean divisionIs(String div, List<Division> divList) {
    return this.divisionId == Division.getIdFromDiv(div, divList);
  }

}
