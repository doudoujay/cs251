

import java.util.*;


public class Rhyming {


  public static void findRhyming(ArrayList<String> wordList) {
    Rhyme rhyme = new Rhyme();


//        Read in
    for (int i = 1; i < wordList.size(); i++) {


      if (i == 1) {
//                Init,  add the index 0 word to the root node.
//              TODO
        String first_word = wordList.get(i - 1);
        Rhyme.current_rhyme.getLocalRests().add(first_word);
      }
//            Keep track for strings
      String string_1 = wordList.get(i - 1);
      String string_2 = wordList.get(i);
      String token = Rhyme.getTokenBetweenStrings(string_1, string_2);
      String rest_1 = Rhyme.getRestWithToken(string_1, token);
      String rest_2 = Rhyme.getRestWithToken(string_2, token);


      if (token.length() > Rhyme.current_rhyme.getToken().length()) {
//                    if token length is greater than parent token
//              Create Tree accordingly

        int child_depth = token.length() - Rhyme.current_rhyme.getToken().length();
        int base_len = Rhyme.current_rhyme.getToken().length();
        Rhyme.current_rhyme.getLocalRests().remove(Rhyme.current_rhyme.getLocalRests().size() - 1); //Remove for sink down
        for (int depth = 1; depth <= child_depth; depth++) {
          String temp_token = token.substring(token.length() - depth - base_len);
          Rhyme temp_parent = Rhyme.current_rhyme;
          Rhyme temp_child = new Rhyme(temp_token);
          Rhyme.addRhymeToParentRhyme(temp_child, temp_parent);

        }
//              Sink down string_1/last string
//              Add string_2

        Rhyme.current_rhyme.getLocalRests().add(rest_1);
        Rhyme.current_rhyme.getLocalRests().add(rest_2);


      } else if (token.length() == Rhyme.current_rhyme.getToken().length()) {
        if (Rhyme.current_rhyme.getToken().equals(token)) {
//                    If same token length and same content, add rest to local rests
          Rhyme.current_rhyme.getLocalRests().add(rest_2);
        } else {
//                    If same token length and different content, siblings
//                  Only swim up for 1 char
          Rhyme.swimUp(token.substring(1));
          Rhyme temp_parent = Rhyme.current_rhyme;
          Rhyme child = new Rhyme(token);
          Rhyme.addRhymeToParentRhyme(child, temp_parent);
          Rhyme.current_rhyme.getLocalRests().add(rest_2);


        }
      } else if (token.length() < Rhyme.current_rhyme.getToken().length()) {
//                Swim up to parent and add
        Rhyme.swimUp(token);
        Rhyme.current_rhyme.getLocalRests().add(rest_2);

      }
    }

    Rhyme.collectRhymes(rhyme);

  }


  public static String tokenString(String s, int rhymeLength) {
    return s.substring(s.length() - rhymeLength);
  }

  public static String restString(String s, int rhymeLength) {
    return s.substring(0, s.length() - rhymeLength);
  }


  public static void main(String[] args) {

    try {
      String file = args[0];
      In inFile = new In(file); //read file
      ArrayList<String> wordList = new ArrayList<String>();
      while (!inFile.isEmpty()) {
        wordList.add(inFile.readString()); //reverse the string
      }
      Collections.sort(wordList, new rhymeStringReverseSort());
      //            List processing
      findRhyming(wordList);


    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
    }

  }

}

class rhymeStringReverseSort implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    String o1_t = Rhyme.reverse(o1);
    String o2_t = Rhyme.reverse(o2);
    return o1_t.compareTo(o2_t);
  }


}

