import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

    private String uniId;
    private String uniName;

    public UniversityFootballClub(String clubName, String location, double clubRate,  String regId, int wins, int draws, int defeats, int recievedGoals, int scoredGoals, int points, String uniId, String uniName) {
        super(clubName, location, clubRate, regId, wins, draws, defeats, recievedGoals, scoredGoals, points);
        this.uniId = uniId;
        this.uniName = uniName;
    }

    public UniversityFootballClub(String uniId, String uniName) {
        this.uniId = uniId;
        this.uniName = uniName;
    }

    public UniversityFootballClub(){

    }

    public String getUniId() {
        return uniId;
    }

    public void setUniId(String uniId) {
        this.uniId = uniId;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

}