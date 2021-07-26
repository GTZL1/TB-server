package controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.player.JPAPlayerRepository;
import database.player.Player;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.mvc.*;

/**
 * Execute requests to create new accounts
 */
public class AccountController extends Controller {

  private final FormFactory formFactory;
  private final MessagesApi messagesApi;
  private final JPAPlayerRepository playerRepository;

  @Inject
  public AccountController(FormFactory formFactory, MessagesApi messagesApi,
      JPAPlayerRepository playerRepository) {
    this.formFactory = formFactory;
    this.messagesApi = messagesApi;
    this.playerRepository = playerRepository;
  }

  /**
   * Display site page with account creation form
   * @param request  the request to link with messages
   * @return  site page
   */
  public Result newAccount(Http.Request request) {
    Form<PlayerForm> playerForm = formFactory.form(PlayerForm.class);
    Messages messages = messagesApi.preferred(request);
    return ok(views.html.newAccount.render(playerForm, messages));
  }

  /**
   * Store a new account in the database
   * @param request  request with new account informations
   * @return       result of the operation
   * @throws ExecutionException if a problem happens accessing the database
   * @throws InterruptedException if a problem happens accessing the database
   */
  public Result saveNewPlayer(Http.Request request) throws ExecutionException, InterruptedException {
    Form<PlayerForm> playerForm = formFactory.form(PlayerForm.class).withDirectFieldAccess(true).bindFromRequest(request);
    if(playerForm.hasErrors()){
      return badRequest(views.html.newAccount.render(playerForm, messagesApi.preferred(request)));
    }

    PlayerForm player = playerForm.get();
    if(!(player.password1.equals(player.password2)) || playerRepository.existsPlayerUsername(
        player.username)){
      return badRequest(views.html.newAccount.render(playerForm, messagesApi.preferred(request)));
    } else {
      Player newPlayer= new Player();
      newPlayer.setUsername(player.username);
      newPlayer.setPasswordHash(BCrypt.withDefaults().hashToString(8, player.password1.toCharArray()));
      playerRepository.addPlayer(newPlayer);
      return ok(views.html.accountCreated.render(newPlayer.getUsername()));
    }
  }
}
