package controllers;

import akka.actor.*;
import akka.stream.*;
import javax.inject.Inject;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class HomeController extends Controller {

  private final ActorSystem actorSystem;
  private final Materializer materializer;

  @Inject
  public HomeController(ActorSystem actorSystem, Materializer materializer) {
    this.actorSystem = actorSystem;
    this.materializer = materializer;
  }

  public WebSocket socket() {
    System.out.println("socket1");
    return WebSocket.Text.accept(
        request -> ActorFlow.actorRef(MyWebSocketActor::props, actorSystem, materializer));
  }

  /**
   * An action that renders an HTML page with a welcome message. The configuration in the
   * <code>routes</code> file means that this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    return ok(views.html.home.render());
  }

  public Result rules(){ return ok(views.html.rules.render());}

  public Result newAccount(){ return ok(views.html.newAccount.render());}
}

class MyWebSocketActor extends AbstractActor {

  public static Props props(ActorRef out) {
    return Props.create(MyWebSocketActor.class, out);
  }

  private final ActorRef out;

  public MyWebSocketActor(ActorRef out) {
    this.out = out;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(String.class, message -> out.tell("I received your message: " + message, self()))
        .build();
  }
}
