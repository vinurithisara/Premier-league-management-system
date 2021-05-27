public class Comparator1 implements java.util.Comparator<FootballClub> {

    public int compare(FootballClub f1, FootballClub f2) {
        if(f1.getScoredGoals()>f2.getScoredGoals())
            return -1;
        else
        if(f1.getScoredGoals()<f2.getScoredGoals())
            return 1;
        else{

            if(f1.getPoints()>f2.getPoints())
                return -1;
            else
            if(f1.getPoints()<f2.getPoints())
                return 1;
            else return 0;

        }
    }
}