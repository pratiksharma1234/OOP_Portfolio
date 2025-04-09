package Player;

public class CricketStats extends PlayerStats {
    private int runs;

    public CricketStats(String playerName, int matchesPlayed, int runs) {
        super(playerName, matchesPlayed);
        this.runs = runs;
    }

    public void showCricketStats() {
        displayStats();
        System.out.println("Runs Scored: " + runs);
    }
}
