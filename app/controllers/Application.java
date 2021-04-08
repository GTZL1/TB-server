package controllers;

import database.player.JPAPlayerRepository;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class Application extends Controller {

  private final FormFactory formFactory;
  private final MessagesApi messagesApi;
  private final JPAPlayerRepository playerRepository;

  @Inject
  public Application(FormFactory formFactory, MessagesApi messagesApi,
      JPAPlayerRepository playerRepository) {
    this.formFactory = formFactory;
    this.messagesApi = messagesApi;
    this.playerRepository = playerRepository;
  }

  public Result newAccount(Http.Request request) {
    Form<PlayerForm> playerForm = formFactory.form(PlayerForm.class);
    Messages messages = messagesApi.preferred(request);
    return ok(views.html.newAccount.render(playerForm, messages));
  }

  public Result newPlayer(Http.Request request) {
    Form<PlayerForm> playerForm = formFactory.form(PlayerForm.class).withDirectFieldAccess(true).bindFromRequest(request);
    if(playerForm.hasErrors()){
      System.out.println("wrong");
      return badRequest(views.html.newAccount.render(playerForm, messagesApi.preferred(request)));
    }

    PlayerForm player = playerForm.get();

    System.out.println(player.username+" "+player.password1+player.password2);
    playerRepository.test();
    return ok();
  }
}
