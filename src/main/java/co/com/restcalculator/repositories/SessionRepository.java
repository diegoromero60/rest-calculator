package co.com.restcalculator.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import co.com.restcalculator.domain.session.Session;

public interface SessionRepository  extends MongoRepository<Session, String> {

  public Optional<Session> findById(String id);

}

