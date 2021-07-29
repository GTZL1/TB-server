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

/**
 * Execute matchmaking with websockets
 * Display site index and rules page
 */
public class WebSocketController extends Controller {

  private final ActorSystem actorSystem;
  private final Materializer materializer;

  private final Map<Integer, ActorRef> wss = new ConcurrentHashMap<>();

  @Inject
  public WebSocketController(ActorSystem actorSystem, Materializer materializer) {
    this.actorSystem = actorSystem;
    this.materializer = materializer;
  }

  /**
   * Match together 2 websocket connections
   * @return WebSocket created
   */
  public WebSocket socket() {
    int key = Math.abs(new Random().nextInt());
    return WebSocket.Text.accept(
        request -> ActorFlow.actorRef(out -> {
          wss.put(key, out);
          int found = -1;

          //wait for another connection
          while (found < 0) {
            for (Integer mapElement : wss.keySet()) {
              //look for another websocket in waiting
              if(mapElement!=key) {
                found= mapElement;
              }
            }
          }

          //create connection with another ActorRef as out
          return WebSocketActor.props(wss.remove(found));
        }, actorSystem, materializer));
  }

  /**
   * Display index page
   * @return page rendering
   */
  public Result index() {
    return ok(views.html.home.render());
  }

  /**
   * Display rules page
   * @return page rendering
   */
  public Result rules() {
    return ok(views.html.rules.render());
  }
}

/**
 * WebSocketActor used in websockets
 */
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
        .match(String.class, message -> {
          //retransmit message
          out.tell(message, self());})
        .build();
  }

  @Override
  public void postStop() {
    //Tell other client to quit too
    out.tell("exit", self());
  }
}
