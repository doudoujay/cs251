import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Rhyme implements Comparable<Rhyme> {
  private String token;
  private ArrayList<String> rests;
  private ArrayList<Rhyme> rhymes;
  private Rhyme parent = null;
  public static Rhyme current_rhyme = null;
  public static String temp_token;
  private static Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
    public int compare(String str1, String str2) {
      str1 = str1+temp_token;
      str2 = str2+temp_token;
      int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
      if (res == 0) {
        res = str1.compareTo(str2);
      }
      return res;
    }
  };

  public Rhyme() {
    this("");
  }

  public Rhyme(String token) {
//        Passed normal content, not reversed
    this(token, new ArrayList<String>());

  }


  public Rhyme(String token, ArrayList<String> rests) {
    this(token, rests, new ArrayList<Rhyme>());

  }

  public Rhyme(String token, ArrayList<String> rests, ArrayList<Rhyme> rhymes) {
    this.token = token;
    this.rests = rests;
    this.rhymes = rhymes;
    current_rhyme = this;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public ArrayList<String> getLocalRests() {
//        Change local rests
    return rests;
  }

  public void setLocalRests(ArrayList<String> rests) {
//        Change local rests
    this.rests = rests;
  }

  public ArrayList<String> getRests() {
//        TODO, recursively get all Rests
    temp_token = this.token;
//    TODO
    ArrayList<String> temp = getRests(this, temp_token);
//        Sort the array
    Collections.sort(temp,ALPHABETICAL_ORDER);
    return temp;
  }

  public ArrayList<String> getRests(Rhyme rhyme, String temp_token) {
    ArrayList<String> temp = new ArrayList<String>();
    if (!(rhyme.getRhymes().size() < 2 && rhyme.getLocalRests().size() == 0)) {
//      base case
      for (String s : rhyme.getLocalRests()
        ) {
        String temp_s = s + rhyme.getToken();
        String rest_result  =getRestWithToken(temp_s, temp_token);
        temp.add(rest_result);
      }


    }
    if(rhyme.getRhymes().size() != 0){
      for (Rhyme r : rhyme.getRhymes()
        ) {
        temp.addAll(getRests(r, temp_token));
      }
    }
    return temp;


  }


  public ArrayList<Rhyme> getRhymes() {
    return rhymes;
  }

  public void setRhymes(ArrayList<Rhyme> rhymes) {
    this.rhymes = rhymes;
  }

  public Rhyme getParent() {
    return parent;
  }

  public void setParent(Rhyme parent) {
    this.parent = parent;
  }


  @Override
  public String toString() {
    String temp = "[ ";
    for (String s : rests
      ) {
      temp += String.format("%s|%s ", s, token);
    }
    temp += " ]";
    return temp;
  }

  @Override
  public int compareTo(Rhyme o) {
    int len_compare = Integer.compare(token.length(), o.getToken().length());
    if (len_compare == 0) { //same string length
      return token.compareTo(o.token);
    } else {
      return len_compare;
    }
  }

  //     Reverse a string
  public static String reverse(String s) {
    StringBuilder sb = new StringBuilder(s);
    return new String(sb.reverse());
  }

  // Get Tokens
  public static ArrayList<String> rhymeTokens(ArrayList<Rhyme> rhymes) {
    ArrayList<String> tokens = new ArrayList<String>();
    for (Rhyme r : rhymes
      ) {
      tokens.add(r.getToken());
    }
    return tokens;
  }

  public static Rhyme getRhymeWithToken(String token, ArrayList<Rhyme> rhymes) {
//        TODO
    return rhymes.get(0);
  }

  public static String getTokenBetweenStrings(String s1, String s2) {
    int maxCompareLen = Math.min(s1.length(), s2.length());
    int i;
    for (i = 0; i <= maxCompareLen; i++) {
      if (!s1.substring(s1.length() - i).equals(s2.substring((s2.length() - i)))) {
        break;
      }
    }
    i--;
    return s1.substring(s1.length() - i);
  }

  public static String getRestWithToken(String s, String token) {
    return s.substring(0, s.length() - token.length());
  }

  public static void addRhymeToParentRhyme(Rhyme child, Rhyme parent) {
    child.setParent(parent);
    parent.getRhymes().add(child);
  }

  public static void swimUp(String token) {
    while (token.length() < current_rhyme.getToken().length()) {
      current_rhyme = current_rhyme.getParent();
    }

  }

  public static void sink(Rhyme current_rhyme) {

  }

  public static void printRhyme(Rhyme rhyme) {

    String temp = "[ ";
    for (String s : rhyme.getRests()
      ) {
      temp += String.format("%s|%s, ", s, rhyme.getToken());
    }
    temp = temp.substring(0, temp.length() - 2);
    temp += " ]";
    System.out.println(temp);
  }

  public static ArrayList<Rhyme> collect(Rhyme rhyme) {
    ArrayList<Rhyme> rhymes = new ArrayList<Rhyme>();

    if (rhyme.getToken().equals("")) {

    } else if (!(rhyme.getRhymes().size() < 2 && rhyme.getLocalRests().size() == 0)) {
//
      rhymes.add(rhyme);
    }
    for (Rhyme r : rhyme.getRhymes()) {
      rhymes.addAll(collect(r));
    }
    return rhymes;


  }


  public static void collectRhymes(Rhyme rhyme) {
    ArrayList<Rhyme> result = collect(rhyme);
    Collections.sort(result);
    for (Rhyme r : result
      ) {
      printRhyme(r);
    }
  }


}
