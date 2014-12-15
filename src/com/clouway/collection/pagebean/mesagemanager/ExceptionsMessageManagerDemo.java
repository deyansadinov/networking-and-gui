package com.clouway.collection.pagebean.mesagemanager;

import java.util.HashMap;
import java.util.Map;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 16:45 Oct 14-9-25
 */
public class ExceptionsMessageManagerDemo {
  public static void main(String[] args) {

    Map<String, String> exceptions = new HashMap<>();

    ExceptionsMessageManager emm = new ExceptionsMessageManager(exceptions);

    try {
      emm.registerErrorMessage("4", "This is not the card number we expected.");
      emm.registerErrorMessage("1", "Wrong PIN");
      emm.registerErrorMessage("2", "Invalid number of debit card");
      emm.registerErrorMessage("3", "Invalid postcode");
      System.out.println(emm.raiseError("3"));
      System.out.println(emm.getErrorMessages());

    } catch (MessageExistsException | KeyIsNotFreeException e) {
      e.getMessage();
    }
  }
}
