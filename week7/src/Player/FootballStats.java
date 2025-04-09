package Player;

public class FootballStats extends PlayerStats {
    private int goals;

    public FootballStats(String playerName, int matchesPlayed, int goals) {
        super(playerName, matchesPlayed);
        this.goals = goals;
    }

    public void showFootballStats() {
        displayStats();
        System.out.println("Goals Scored: " + goals);
    }
}
