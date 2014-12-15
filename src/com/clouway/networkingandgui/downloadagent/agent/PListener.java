package com.clouway.networkingandgui.downloadagent.agent;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 12:37 Nov 14-11-29
 */
public interface PListener {
  void onProgressUpdate(int value);
  void setMax(int value);
}
