package com.zaseki;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@EqualsAndHashCode
public class Member {

  @Autowired
  @Transient
  DivisionRepository divisionRepository;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private int id;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "furigana")
  @Getter
  @Setter
  private String furigana;

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

  public Member() {
    super();
  }

  public Member(Integer id, String name, String yomigana, int divisionId, String floor, String extensionNumber) {
    super();
    this.id = id;
    this.name = name;
    this.furigana = yomigana;
    this.divisionId = divisionId;
    this.floor = floor;
    this.extensionNumber = extensionNumber;
  }

  public boolean furiganaIs(String furigana) {
    return this.furigana.equals(furigana);
  }

  public boolean divisionIs(String div) {
    Division d = divisionRepository.findByDiv(div);
    return this.divisionId == d.getId();
  }
}
