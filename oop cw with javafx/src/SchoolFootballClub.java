import java.util.Objects;

public class SchoolFootballClub extends FootballClub {
    private String sclId;
    private String sclName;

    public SchoolFootballClub(String clubName, String location, double clubRate,  String regId, int wins, int draws, int defeats, int recievedGoals,int scoredGoals, int points, String sclId, String sclName) {
        super(clubName, location, clubRate, regId, wins, draws, defeats, recievedGoals,scoredGoals, points);
        this.sclId = sclId;
        this.sclName = sclName;
    }

    public SchoolFootballClub(){

    }

    public String getSclId() {
        return sclId;
    }

    public void setSclId(String sclId) {
        this.sclId = sclId;
    }

    public String getSclName() {
        return sclName;
    }

    public void setSclName(String sclName) {
        this.sclName = sclName;
    }


}