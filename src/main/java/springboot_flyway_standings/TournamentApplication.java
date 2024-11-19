package springboot_flyway_standings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.service.MatchService;
import springboot_flyway_standings.service.TeamService;

@SpringBootApplication
public class TournamentApplication {

    private final MatchService matchService;
    private final TeamService teamService;

    @Autowired
    public TournamentApplication(MatchService matchService, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TournamentApplication.class, args);
    }
}