import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {

    private String clubName;
    private String location;
    private double clubRate;

    private String regId;

    public SportsClub(String clubName, String location, double clubRate,String regId) {

        this.clubName = clubName;
        this.location = location;
        this.clubRate = clubRate;
        this.regId=regId;
    }
    public SportsClub(){

    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) { this.clubName = clubName; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getClubRate() {
        return clubRate;
    }

    public void setClubRate(double clubRate) {
        this.clubRate = clubRate;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }


}