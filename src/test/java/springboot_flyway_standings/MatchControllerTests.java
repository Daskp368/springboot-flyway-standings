package springboot_flyway_standings;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import springboot_flyway_standings.controller.MatchController;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.service.MatchService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


/*
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.service.MatchService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
*/
@WebMvcTest(MatchController.class)
@AutoConfigureMockMvc
public class MatchControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchService matchService;

    /*
    @Test
    public void testRegisterMatch() throws Exception {
        Match match = new Match(1, "Team A", "Team B", 2, 1, LocalDate.now());
        when(matchService.saveMatch(any(Match.class))).thenReturn(match);

        mockMvc.perform(post("/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType("{\"homeTeam\":\"Team A\",\"awayTeam\":\"Team B\",\"homeScore\":2,\"awayScore\":1,\"date\":\"2023-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.homeTeam").value("Team A"));
    }

     */

    @Test
    void testRegisterMatch() throws Exception {
        Match match = new Match(1, "2023", LocalDate.now(), new Team("Home Team"), new Team("Away Team"), 2, 3);

        when(matchService.saveMatch(any(Match.class))).thenReturn(match);

        mockMvc.perform((RequestBuilder) post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf("{\"season\":\"2023\",\"homeTeam\":{\"name\":\"Home Team\"},\"awayTeam\":{\"name\":\"Away Team\"},\"homeScore\":2,\"awayScore\":3}")))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.season").value("2023"));
    }

    /*
    @Test
    public void testGetTournamentTable() throws Exception {
        List<TournamentTable> standings = List.of(new TournamentTable("Team A", 3, 9));
        when(matchService.getTournamentTable(any(LocalDate.class))).thenReturn(standings);

        mockMvc.perform(get("/matches/table/2023-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].teamName").value("Team A"));
    }

     */

    @Test
    void testGetTournamentTable() throws Exception {
        LocalDate matchDate = LocalDate.of(2023, 10, 1);
        List<TournamentTable> tournamentTables = Collections.singletonList(new TournamentTable("Team A", 1, 3));

        when(matchService.getTournamentTable(matchDate)).thenReturn(tournamentTables);

        mockMvc.perform(get("/api/matches/table/2023-10-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].teamName").value("Team A"));
    }

}
