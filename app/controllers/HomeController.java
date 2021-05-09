package controllers;

import akka.actor.AbstractActor;
import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.Materializer;
import akka.stream.javadsl.JavaFlowSupport.Flow;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder.*;
import play.libs.streams.ActorFlow;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class HomeController extends Controller {
  private final ActorSystem actorSystem;
  private final Materializer materializer;

  private List<ActorRef> wss=new ArrayList<>();

  @Inject
  public HomeController(ActorSystem actorSystem, Materializer materializer) {
    this.actorSystem = actorSystem;
    this.materializer = materializer;
  }

  public WebSocket socket() {
    return WebSocket.Text.accept(
        request -> ActorFlow.actorRef(out -> {wss.add(out);

          while(wss.size()<2){
            try {
              TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          System.out.println("ws created");
          return MyWebSocketActor.props(wss.remove(0));}, actorSystem, materializer));
  }

  /**
   * An action that renders an HTML page with a welcome message. The configuration in the
   * <code>routes</code> file means that this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    return ok(views.html.home.render());
  }

  public Result rules() throws InterruptedException {
    return ok(views.html.rules.render());
  }
}

class MyWebSocketActor extends AbstractActor {

  public static Props props(ActorRef out) {
    return Props.create(MyWebSocketActor.class, out);
  }

  public static Props props() {
    return Props.create(MyWebSocketActor.class);
  }

  private ActorRef out;

  public MyWebSocketActor(ActorRef out) {
    this.out = out;
  }

  public MyWebSocketActor(){

  }

  public void setProps(ActorRef out){
    this.out=out;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(String.class, message -> out.tell("I received your message: " + message, self()))
        .build();
  }
}
