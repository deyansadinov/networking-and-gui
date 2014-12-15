package com.clouway.networkingandgui.calculator.core;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 13:48 Nov 14-11-27
 */
public interface UserRepository {

  User findUserByName(String name);
}
