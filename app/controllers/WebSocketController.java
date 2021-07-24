package controllers;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class WebSocketController extends Controller {

  private final ActorSystem actorSystem;
  private final Materializer materializer;

  private final Map<Integer, ActorRef> wss = new ConcurrentHashMap<>();

  @Inject
  public WebSocketController(ActorSystem actorSystem, Materializer materializer) {
    this.actorSystem = actorSystem;
    this.materializer = materializer;
  }

  public WebSocket socket() {
    int key = Math.abs(new Random().nextInt());
    return WebSocket.Text.accept(
        request -> ActorFlow.actorRef(out -> {
          wss.put(key, out);
          int found = -1;
          while (found < 0) {
            for (Integer mapElement : wss.keySet()) {
              if(mapElement!=key) {
                found= mapElement;
              }
            }
          }

          System.out.println("ws created");
          return WebSocketActor.props(wss.remove(found));
        }, actorSystem, materializer));
  }

  /**
   * An action that renders an HTML page with a welcome message. The configuration in the
   * <code>routes</code> file means that this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    return ok(views.html.home.render());
  }

  public Result rules() {
    return ok(views.html.rules.render());
  }
}

class WebSocketActor extends AbstractActor {

  public static Props props(ActorRef out) {
    return Props.create(WebSocketActor.class, out);
  }

  private ActorRef out;

  public WebSocketActor(ActorRef out) {
    this.out = out;
  }

  public void setProps(ActorRef out) {
    this.out = out;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(String.class, message -> {System.out.println(message);
          out.tell(message, self());})
        .build();
  }

  @Override
  public void postStop() throws Exception {
    out.tell("exit", self());
    System.out.println("quit");
  }
}
