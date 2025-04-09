package Player;

public class PlayerStats {
    protected String playerName;
    protected int matchesPlayed;

    public PlayerStats(String playerName, int matchesPlayed) {
        this.playerName = playerName;
        this.matchesPlayed = matchesPlayed;
    }

    public void displayStats() {
        System.out.println(playerName + " played " + matchesPlayed + " matches.");
    }
}
