package zaseki;

import java.io.*;
import java.util.*;

public class ZasekiTest {
  public static void main(String[] args) {
    String filename = "zaseki4.csv";
    ArrayList<Human> humans = new ArrayList<Human>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String string;

      // read line
      while ((string = br.readLine()) != null) {
        String array[] = string.split(",");
        humans.add(from(array));
      }

      br.close();

    } catch (FileNotFoundException e) {
      System.out.println(filename + " is not found");
    } catch (IOException e) {
      System.out.println("IO Error");
    } catch (NumberFormatException e) {
      System.out.println("NumberFormat Error");
    }

    if (args.length == 2) {
      filterByDivision(args, humans);
    } else {
      serchHuman(args[0], humans);
    }
  }

  static String selectDivision(String string) {
    if (string.equals("its")) {
      return "ITS";
    } else if (string.equals("etec")) {
      return "ETEC";
    } else if (string.equals("med")) {
      return "医療";
    } else if (string.equals("kanri")){
      return "管理部";
    }else if(string.equals("kinyu")){
      return "金融";
    }
    
    return null;
  }

  static Human from(String[] array) {
    return new Human(array);
  }

  static void filterByDivision(String[] strings, ArrayList<Human> humans) {
    String name = strings[0];
    String division = selectDivision(strings[1]);

    if (division == null) {
      serchHuman(name, humans);
    } else {
      System.out.println("-----------------[ " + name + " (" + division + ") ]-------------------");

      for (Human human : humans) {
        if (name.equals(human.getFurigana()) && division.equals(human.getDivision())) {
          System.out.println(human);
        }
      }

      System.out.println("------------------------------------------------");
    }
  }

  static void serchHuman(String string, ArrayList<Human> humans) {
    System.out.println("-----------------[ " + string + " ]-------------------");

    for (Human human : humans) {
      if (string.equals(human.getFurigana())) {
        System.out.println(human);
      }
    }

    System.out.println("------------------------------------------------");

  }
}
