package com.zaseki;

class Member {
  private int id;
  private String name;
  private String furigana;
  private String division;
  private String floor;
  private String extensionNumber;

  public Member(int id, String name, String furigana, String division, String floor, String extensionNumber) {
    super();
    this.id = id;
    this.name = name;
    this.furigana = furigana;
    this.division = division;
    this.floor = floor;
    this.extensionNumber = extensionNumber;
  }
  
  public int getId() {
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

  public String getFurigana() {
    return furigana;
  }

  public void setFurigana(String furigana) {
    this.furigana = furigana;
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

}