import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Serializable,Comparable<FootballClub> {


    private int wins;
    private int draws;
    private int defeats;
    private int recievedGoals;
    private int scoredGoals;
    private int score;
    private int points;

    public FootballClub(String clubName, String location, double clubRate,  String regId, int wins, int draws, int defeats, int recievedGoals, int scoredGoals, int points) {
        super(clubName, location, clubRate,  regId);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.recievedGoals = recievedGoals;
        this.scoredGoals = scoredGoals;

        this.points = points;
    }

    public FootballClub(String clubName, String location, double clubRate, String regId ) {
        super(clubName, location, clubRate,  regId);
    }

        public FootballClub(String clubName,int wins, int draws, int defeats,int scoredGoals, int points){
                this.setClubName(clubName);
                this.setWins(wins);
                this.setDraws(draws);
                this.setDefeats(defeats);
                this.setScoredGoals(scoredGoals);
                this.setPoints(points);
}


    public FootballClub(){

    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getRecievedGoals() {
        return recievedGoals;
    }

    public void setRecievedGoals(int recievedGoals) {
        this.recievedGoals = recievedGoals;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    @Override
    public int compareTo(FootballClub o) {
        return 0;
    }
}