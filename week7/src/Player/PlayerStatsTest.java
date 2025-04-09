package Player;

public class PlayerStatsTest {
    public static void main(String[] args) {
        FootballStats pratik = new FootballStats("pratik sharma", 900, 1000);
        CricketStats pratikss = new CricketStats("pratik sharma", 600, 25000);

        pratik.showFootballStats();
        pratikss.showCricketStats();
    }
}


