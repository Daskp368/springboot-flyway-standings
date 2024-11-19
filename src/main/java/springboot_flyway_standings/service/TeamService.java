package springboot_flyway_standings.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot_flyway_standings.model.Team;
import springboot_flyway_standings.repository.TeamRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    //Optional<Team>
    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}

