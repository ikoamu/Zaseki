package zaseki;

class Human {
  private String name, furigana, division, floor, extensionNumber;
  
  public Human(String[] data) {
    this.name = data[0];
    this.furigana = data[1];
    this.division = data[2];
    this.floor = data[3];
    this.extensionNumber = data[4];
  }
  
  public String toString() {
    return name + "\t" + division + "\t" + floor + "\t" + extensionNumber;
  }
  
  public String getFurigana() {
    return furigana;
  }
  
  public String getDivision() {
    return division;
  }
}
