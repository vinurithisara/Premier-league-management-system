public class Comparator2 implements java.util.Comparator<FootballClub> {

    public int compare(FootballClub c1, FootballClub c2) {
        if(c1.getWins()>c2.getWins())
            return -1;
        else
        if(c1.getWins()<c2.getWins())
            return 1;
        else{

            if(c1.getPoints()>c2.getPoints())
                return -1;
            else
            if(c1.getPoints()<c2.getPoints())
                return 1;
            else return 0;

        }
    }
}