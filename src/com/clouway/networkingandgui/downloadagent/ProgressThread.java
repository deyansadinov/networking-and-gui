package com.clouway.networkingandgui.downloadagent;

import javax.swing.*;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:07 Nov 14-11-24
 */
public class ProgressThread extends Thread {

  private final TransfeersObject transfeersObject;
  private final JProgressBar jProgressBar;

  public ProgressThread(TransfeersObject transfeersObject, JProgressBar jProgressBar) {

    this.transfeersObject = transfeersObject;
    this.jProgressBar = jProgressBar;
  }

  public void run() {
    while (true) {
      try {

        Thread.sleep(1000);
      } catch (InterruptedException e) {
        break;
      }
      jProgressBar.setValue(transfeersObject.getTransferred());
    }
  }
}
