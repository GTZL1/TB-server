package controllers;

import javax.inject.Inject;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class Application extends Controller {
  private final FormFactory formFactory;

  @Inject
  public Application(FormFactory formFactory){
    this.formFactory=formFactory;
  }

  public Result newPlayer(Http.Request request){
    System.out.println("Post");
      Form<PlayerForm> playerForm=formFactory.form(PlayerForm.class).withDirectFieldAccess(true);
      PlayerForm player=playerForm.bindFromRequest(request).get();
      return ok();
  }
}
