package springboot_flyway_standings.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_flyway_standings.TournamentTable;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.repository.MatchRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class MatchService {


    private MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<TournamentTable> getTournamentTable(LocalDate date) {
        List<Match> matches = matchRepository.findByDateBefore(date);
        Map<String, TournamentTable> table = new HashMap<>();

        for (Match match : matches) {
            String homeTeamName = match.getHomeTeam().getName();
            String awayTeamName = match.getAwayTeam().getName();
            updateTable(table, homeTeamName, match.getHomeScore(), match.getAwayScore());
            updateTable(table, awayTeamName, match.getAwayScore(), match.getHomeScore());
        }

        return new ArrayList<>(table.values());
    }

    private void updateTable(Map<String, TournamentTable> table, String teamName, int score, int opponentScore) {
        TournamentTable entry = table.getOrDefault(teamName, new TournamentTable(teamName, 0, 0));
        entry.setPlayedMatches(entry.getPlayedMatches() + 1);

        if (score > opponentScore) {
            entry.setPoints(entry.getPoints() + 3); 
        } else if (score == opponentScore) {
            entry.setPoints(entry.getPoints() + 1); 
        }

        table.put(teamName, entry);
    }
}
