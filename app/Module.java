import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import database.card.CardRepository;
import database.card.JPAHeroRepository;
import database.card.JPAUnitRepository;

public class Module extends AbstractModule {
  protected void configure(){
    bind(CardRepository.class).annotatedWith(Names.named("Unit")).to(JPAUnitRepository.class);
    bind(CardRepository.class).annotatedWith(Names.named("Hero")).to(JPAHeroRepository.class);
    bind(CardRepository.class).annotatedWith(Names.named("Spy")).to(JPAUnitRepository.class);
    bind(CardRepository.class).annotatedWith(Names.named("Vehicle")).to(JPAHeroRepository.class);
  }
}
