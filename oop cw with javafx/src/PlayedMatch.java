import java.io.Serializable;
import java.util.Date;

public class PlayedMatch implements Serializable {
    private Date date;
    private FootballClub Ateam;
    private String c1name;
    private String c2name;
    private int AScore;
    private FootballClub Bteam;
    private int BScore;


    public PlayedMatch(){

    }

  //  public PlayedMatch(Date date,FootballClub ateam, int aScore, FootballClub bteam, int bScore) {

  // }


    // public PlayedMatch(Date date, FootballClub ateam, int aScore, FootballClub bteam, int bScore) {
    //}

/*  public PlayedMatch(Date date, FootballClub ateam, int aScore, FootballClub bteam, int bScore) {
        this.setDate(date);
        this.setAteam(ateam);
        this.setAScore(aScore);
        this.setBteam(bteam);
        this.setBScore(bScore);
    }

    public PlayedMatch() {

    }*/


    public FootballClub getAteam(){
        return Ateam;
    }
    public FootballClub getBteam(){
        return Bteam;
    }
    public int getAScore(){
        return AScore;
    }
    public int getBScore(){
        return BScore;
    }
    public Date getDate(){
        return date;
    }

    public void setAteam(FootballClub Ateam){
        this.Ateam=Ateam;
    }
    public void setBteam(FootballClub Bteam){
        this.Bteam=Bteam;
    }
    public void setAScore(int AScore){
        this.AScore=AScore;
    }
    public void setBScore(int BScore){
        this.BScore=BScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setAteamName(String clubName) {
        this.c1name = clubName;
    }

    public void setBteamName(String clubName) {
        this.c2name = clubName;
    }

    public String getC1name() {
        return c1name;
    }

    public String getC2name() {
        return c2name;
    }
}
