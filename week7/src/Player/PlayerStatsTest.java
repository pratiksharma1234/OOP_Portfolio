package Player;

public class PlayerStatsTest {
    public static void main(String[] args) {
        FootballStats Dibas = new FootballStats("pratik sharma", 900, 1000);
        CricketStats Bibas = new CricketStats("pratik sharma", 600, 25000);

        Dibas.showFootballStats();
        Bibas.showCricketStats();
    }
}


