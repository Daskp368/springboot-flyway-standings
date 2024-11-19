package springboot_flyway_standings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.repository.MatchRepository;
import springboot_flyway_standings.service.MatchService;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMatch() {
        Match match = new Match(1, "2023", LocalDate.now(), new Team("Home Team"), new Team("Away Team"), 2, 3);

        when(matchRepository.save(match)).thenReturn(match);

        Match savedMatch = matchService.saveMatch(match);

        assertNotNull(savedMatch);
        assertEquals("2023", savedMatch.getSeason());
    }

    @Test
    void testGetTournamentTable() {
        Match match1 = new Match(1, "2023", LocalDate.of(2023, 10, 1), new Team("Team A"), new Team("Team B"), 3, 1);
        Match match2 = new Match(2, "2023", LocalDate.of(2023, 9, 30), new Team("Team B"), new Team("Team C"), 2, 2);

        when(matchRepository.findByDateBefore(any(LocalDate.class))).thenReturn(Arrays.asList(match1, match2));
        List<TournamentTable> table = matchService.getTournamentTable(LocalDate.now());

        assertEquals(3, table.size());
        assertTrue(table.stream().anyMatch(t -> t.getTeamName().equals("Team A") && t.getPoints() == 3));
    }
}