package controllers;

import play.data.validation.Constraints.Required;

/**
 * Class used to transmit data entered by user while creating new account
 */
public class PlayerForm {
  @Required String username;
  @Required String password1;
  @Required String password2;
}
