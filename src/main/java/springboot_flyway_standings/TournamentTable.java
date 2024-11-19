package springboot_flyway_standings;


public class TournamentTable {
    private String teamName;
    private int playedMatches;
    private int points;

    public TournamentTable(String teamName, int playedMatches, int points) {
        this.teamName = teamName;
        this.playedMatches = playedMatches;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
