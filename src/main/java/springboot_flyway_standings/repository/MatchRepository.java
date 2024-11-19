package springboot_flyway_standings.repository;

import org.springframework.stereotype.Repository;
import springboot_flyway_standings.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByDateBefore(LocalDate date);
}
