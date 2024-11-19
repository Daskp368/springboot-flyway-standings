package springboot_flyway_standings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.repository.TeamRepository;
import springboot_flyway_standings.service.TeamService;

import java.util.List;
import java.util.Optional;

public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTeam() {
        Team team = new Team("Team A");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team savedTeam = teamService.saveTeam(team);

        assertNotNull(savedTeam);
        assertEquals("Team A", savedTeam.getName());
    }

    @Test
    void testGetTeamFound() {
        Team team = new Team("Team A");
        team.setId(1);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Team foundTeam = teamService.getTeam(1L);

        assertNotNull(foundTeam);
        assertEquals("Team A", foundTeam.getName());
    }

    @Test
    void testGetTeamNotFound() {
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        Team foundTeam = teamService.getTeam(1L);

        assertNull(foundTeam);
    }

    @Test
    void testGetAllTeams() {
        List<Team> teams = List.of(new Team("Team A"), new Team("Team B"));

        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> foundTeams = teamService.getAllTeams();

        assertEquals(2, foundTeams.size());
    }

    @Test
    void testDeleteTeam() {
        doNothing().when(teamRepository).deleteById(1L);

        assertDoesNotThrow(() -> teamService.deleteTeam(1L));

        verify(teamRepository).deleteById(1L);
    }
}

