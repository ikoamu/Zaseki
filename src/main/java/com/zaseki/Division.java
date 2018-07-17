package com.zaseki;

enum Division {
  ITS("its"), ETEC("etec"), 金融("fin"), 管理部("adm"), 医療("med"), 未来企画室("fpo"), アジャイル("ag");

  final String keyWord;

  private Division(final String keyWord) {
    this.keyWord = keyWord;
  }
  
  protected static Division from(String string) {
    for (Division division : Division.values()) {
      if (string.equals(division.keyWord)) {
        return division;
      }
    }

    return null;
  }
}

