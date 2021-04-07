package controllers;

import play.data.validation.Constraints.Required;

public class PlayerForm {
  @Required String username;
  @Required String password1;
  @Required String password2;
}
