public class Comparator implements java.util.Comparator<FootballClub>  {

    public int compare(FootballClub o1, FootballClub o2) {
        if(o1.getPoints()>o2.getPoints())
            return -1;
        else
        if(o1.getPoints()<o2.getPoints())
            return 1;
        else{
            int goalDifferent01=o1.getScoredGoals()-o1.getRecievedGoals();
            int goalDifferent02=o2.getScoredGoals()-o2.getRecievedGoals();
            if(goalDifferent01>goalDifferent02)
                return -1;
            else
            if(goalDifferent01<goalDifferent02)
                return 1;
            else return 0;

        }
    }


}