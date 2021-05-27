import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface LeagueManager {

    void displayStat();
    void deleteClub();
    void clubList();
    void addMatch() throws ParseException;
    public void insertDetails();
    public List<FootballClub> getClubList();
    public List<FootballClub>getClubListByGoals();
    public List<FootballClub>getClubListByWins();
    public  void random();
    public List<PlayedMatch>getMList();
    public List<PlayedMatch> getMatchListByDate();
    public List<PlayedMatch> searchdate(Date date);

}