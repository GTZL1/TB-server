package controllers;

import static play.mvc.Results.ok;

import database.card.*;
import java.util.concurrent.ExecutionException;
import javax.inject.*;
import play.mvc.*;

public class CardController {
 private final JPAHeroRepository heroRepository;
 private final JPAUnitRepository unitRepository;

  @Inject
  public CardController(JPAHeroRepository heroRepository,
      JPAUnitRepository unitRepository) {
    this.heroRepository = heroRepository;
    this.unitRepository = unitRepository;
  }

  public Result getCards() throws ExecutionException, InterruptedException {
    // TODO require and verify idSession
    System.out.println(heroRepository.getCards());
    System.out.println(unitRepository.getCards());
    return ok();
  }
}
