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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springboot_flyway_standings.controller.TeamController;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.service.TeamService;

import java.util.Collections;
import java.util.List;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTeam() throws Exception {
        Team team = new Team("Team A");
        when(teamService.saveTeam(any(Team.class))).thenReturn(team);

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Team A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Team A"));
    }

    @Test
    void testGetTeam() throws Exception {
        Team team = new Team("Team A");
        team.setId(1);

        when(teamService.getTeam(1L)).thenReturn(team);

        mockMvc.perform(get("/api/teams/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Team A"));
    }

    @Test
    void testGetAllTeams() throws Exception {
        List<Team> teams = List.of(new Team("Team A"), new Team("Team B"));

        when(teamService.getAllTeams()).thenReturn(teams);

        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Team A"))
                .andExpect(jsonPath("$[1].name").value("Team B"));
    }

    @Test
    void testDeleteTeam() throws Exception {
        doNothing().when(teamService).deleteTeam(1L);

        mockMvc.perform(delete("/api/teams/1"))
                .andExpect(status().isNoContent());
    }
}
