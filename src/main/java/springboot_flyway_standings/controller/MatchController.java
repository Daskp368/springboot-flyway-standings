package springboot_flyway_standings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot_flyway_standings.TournamentTable;
import springboot_flyway_standings.model.Match;
import springboot_flyway_standings.service.MatchService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public Match registerMatch(@RequestBody Match match) {
        return matchService.saveMatch(match);
    }


    @GetMapping("/table/{date}")
    public ResponseEntity<List<TournamentTable>> getTournamentTable(@PathVariable String date) {
        LocalDate matchDate = LocalDate.parse(date);
        System.out.println(matchDate);
        return ResponseEntity.ok(matchService.getTournamentTable(matchDate));
    }

}
/*


    @GetMapping("/table/{date}")
    public List<TournamentTable> getTournamentTable(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate matchDate) {
        return matchService.getTournamentTable(matchDate);
    }

    @GetMapping("/table/{date}")
    public List<TournamentTable> getTournamentTable(@RequestParam("date") LocalDate matchDate) {
        return matchService.getTournamentTable(matchDate);
    }
     */