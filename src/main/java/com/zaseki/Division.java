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
@Table(name = "division")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Division {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private int id;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "div")
  @Getter
  @Setter
  private String div;

  public static int getIdFromDiv(String div, List<Division> divList) {
    for (Division d : divList) {
      if (d.getDiv().equals(div)) {
        return d.getId();
      }
    }

    return 999;
  }
}
