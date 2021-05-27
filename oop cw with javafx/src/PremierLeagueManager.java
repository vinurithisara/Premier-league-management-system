import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PremierLeagueManager implements LeagueManager, Serializable {
    private List<FootballClub> clubList = new ArrayList<>();
    private List<PlayedMatch> playedmatches=new ArrayList<>();
    public ArrayList<FootballClub> getList(){
        return (ArrayList<FootballClub>)clubList ;

    }
    /*
    code to add a new member and insert details of clubs
     */
    @Override
    public void insertDetails() {

        Scanner sc1=new Scanner(System.in);
        FootballClub club1= new FootballClub();
        System.out.println("Enter the club name:");
        String name1 = sc1.nextLine();
        club1.setClubName(name1);

        for (FootballClub c2 : clubList){
            if(c2.getClubName().equals(name1)){
                System.out.println("This club is already in league!");
                System.out.println("---------------------------------------");
                return;
            }
        }

        System.out.println("Location:");
        String location2=sc1.nextLine();
        club1.setLocation(location2);

        System.out.println("Rate:");

        while(!sc1.hasNextInt()){
            System.out.println("Please enter valid input!!!");
            sc1.next();
        }


        Double rate2 = sc1.nextDouble();
        club1.setClubRate(rate2);
        System.out.println("Club registration number:");
        String regId2=sc1.nextLine();
        club1.setRegId(regId2);
        clubList.add(club1);
        System.out.println("Club " + club1.getClubName() + " is added");
        System.out.println("------------------------------------");
            Main.saveFile();


    }

    @Override
    public void deleteClub() {  //to delete a club from premier league table
        Scanner sc2=new Scanner(System.in);
        System.out.println("Enter the club name you want to delete:");
        String name1=sc2.nextLine();

        boolean search = false;
        for (FootballClub club1 : clubList) {
            if (club1.getClubName().equals(name1)) {

                clubList.remove(club1);
                System.out.println("Club " + club1.getClubName() + " is deleted");
                System.out.println("------------------------------------");
                Main.saveFile();
                search = true;
                return;

            }
        }
        if (!search) {
            System.out.println("The club is not in the list");
            System.out.println("----------------------------------------");
        }


    }
    @Override
    public List<PlayedMatch> searchdate(Date date){ //search match details using date the matches played


        for(PlayedMatch mat:playedmatches){
            if(mat.getDate().equals(date)){
                return this.playedmatches;
            }

        }
    return null;
}
    @Override
    public void displayStat() {     //display a selected club statistic using club name
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Enter the club name:");
        String name = sc3.nextLine();
        for (FootballClub club :clubList) {
            if(club.getClubName().equals(name)){
                System.out.println( " Club: "+ club.getClubName() + "Reg Id:"+club.getRegId()+"\t" + " Points: " + club.getPoints() + " Wins: " + club.getWins() + " Draws: " + club.getDraws() +
                        " Defeats: " + club.getDefeats() + " Goal difference " + (club.getScoredGoals() - club.getRecievedGoals()));
                return;
            }
        }
        System.out.println("No such club in the list");
    }

    @Override
    public void clubList() {    //display club list according to points and galle difference in CLI
        Collections.sort(clubList, new Comparator());
        for (FootballClub club2 : clubList) {
            System.out.println("Club: " + club2.getClubName() +" Location "+club2.getLocation() + " Points: " + club2.getPoints() + " Wins: " + club2.getWins() + " Draws: " + club2.getDraws() +
                    " Defeats: " + club2.getDefeats() + " Goal difference " + (club2.getScoredGoals() - club2.getRecievedGoals()));

        }
        System.out.println("===============================================================");

    }
    @Override
    public List<PlayedMatch> getMList() {   //get match list
        return this.playedmatches;
    }

    /*
    add match to the match list.therefore we get the date of the match played,two clubs names and scored goals
     */
    @Override
    public void addMatch() throws ParseException {
        System.out.println("Enter the date match played(yyyy/MM/dd)");
        Scanner sc4 = new Scanner(System.in);
        String match = sc4.next();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(match);
        } catch (ParseException ex) {
            System.out.println("Enter the date in correct format:");
            return;
        }
        System.out.println("Enter the first club:");
        String cname1 = sc4.nextLine();
        FootballClub first = null;
        for (FootballClub club3 : clubList) {
            if (club3.getClubName().equals(cname1)) {
                first = club3;
            }


        }
       if (first == null) {
            System.out.println("This club is not in the list");
            return;
        }
        //first club score
        System.out.println("Enter the first team scored goals:");

         while(!sc4.hasNextInt()){
            System.out.println("Please enter valid input!!!");
            sc4.next();
        }

        Integer first_score = sc4.nextInt();
        int fscore = -1;
        try {
            fscore = Integer.parseInt(String.valueOf(first_score));

        } catch (Exception e) {

        }
        if (first_score == -1) {
            System.out.println("Enter the scored goals:");
            return;
        }
        System.out.println("Enter the second club:");
        String cname2 = sc4.nextLine();
        FootballClub second = null;
        for (FootballClub club4 : clubList) {
            if (club4.getClubName().equals(cname2)) {
                second = club4;
            }   }
            if (second == null) {
                System.out.println("This club is not in the list.Enter the existing club");
                return;

        }
        //----------------------------------------
        System.out.println("Enter the second team scored goals:");
        while(!sc4.hasNextInt()){
            System.out.println("Please enter valid input!!!");
            sc4.next();
        }

        Integer second_score = sc4.nextInt();
        int sscore = -1;
        try {
            sscore = Integer.parseInt(String.valueOf(second_score));

        } catch (Exception e) {

        }
        if (second_score == -1) {
            System.out.println("Enter the scored goals again:");
            return;
        }

        PlayedMatch m = new PlayedMatch();
        m.setDate(date);
        m.setAteamName(first.getClubName());
        m.setBteamName(second.getClubName());
        m.setAteam(first);
        m.setAScore(fscore);
        m.setBteam(second);
        m.setBScore(sscore);
        playedmatches.add(m);
        first.setScoredGoals(first.getScoredGoals() + fscore);
        second.setScoredGoals(second.getScoredGoals() + sscore);
        first.setRecievedGoals(first.getRecievedGoals() + sscore);
        second.setRecievedGoals(second.getRecievedGoals() + fscore);

        if (fscore > sscore) {
            first.setPoints(first.getPoints() + 3);
            first.setWins(first.getWins() + 1);
            second.setDefeats(second.getDefeats() + 1);

        } else if (fscore < sscore) {
            second.setPoints(second.getPoints() + 3);
            second.setWins(second.getWins() + 1);
            first.setDefeats(first.getDefeats() + 1);
        } else {
            first.setPoints(first.getPoints() + 1);
            first.setDraws(first.getDraws() + 1);
            second.setPoints(second.getPoints() + 1);
            second.setDraws(second.getDraws() + 1);

        }
        Main.saveFile();

    }


    @Override
    public List<FootballClub> getClubList() {
        Collections.sort(clubList, new Comparator());
        for (FootballClub club2 : clubList) {
            return clubList;
        }
        return null;
    }

    @Override
    public List<PlayedMatch> getMatchListByDate() {
        Collections.sort(playedmatches, new Comparator3());
        for (PlayedMatch match2 : playedmatches) {
            return playedmatches;
        }
        return null;
    }



    @Override
    public List<FootballClub>getClubListByGoals(){
        Collections.sort(clubList,new Comparator1());
            for (FootballClub club3 : clubList) {
                return clubList;
            }
            return null;
        }


    @Override
    public List<FootballClub>getClubListByWins(){
        Collections.sort(clubList,new Comparator2());
        for (FootballClub club4 : clubList) {
            return clubList;
        }
        return null;
    }

  @Override
    public void random(){

        Random random2=new Random();
      int randomitem=random2.nextInt(clubList.size());
      FootballClub randomClub=clubList.get(randomitem);

    System.out.println(""+ randomClub.getClubName());
    boolean same = true;
    FootballClub randomClub2 = null;
    while(same){
        int randomitem2=random2.nextInt(clubList.size());
        if(randomitem2 == randomitem){
            same= true;
        }else{
            same = false;
            randomClub2=clubList.get(randomitem2);
            System.out.println(""+ randomClub2.getClubName());
        }
    }
        int score1=random2.nextInt(clubList.size());
        int score2=random2.nextInt(clubList.size());
        System.out.println(score1);
        System.out.println(score2);



    }}




