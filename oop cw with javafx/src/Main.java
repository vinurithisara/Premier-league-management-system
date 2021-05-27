import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Application {


    private static PremierLeagueManager manager=null;



    @Override
    public void start(Stage primaryStage) throws Exception{

        File name = new File("./premierLeague.ser");
        if(name.exists()) {
            try
            {

                FileInputStream savefile = new FileInputStream(name);
                ObjectInputStream inputStream = new ObjectInputStream(savefile);


                this.manager = (PremierLeagueManager)inputStream.readObject();

                inputStream.close();
                savefile.close();

                System.out.println("Deserialized ");


            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException");
            }


        }else{
            this.manager = new PremierLeagueManager();
        }


        boolean quit=false;
        do{
            Scanner sc=new Scanner(System.in); //Menu bar
            System.out.println("Premier League Management System");
            System.out.println("**********************************");
            System.out.println("Enter 1 to create a new football club");
            System.out.println("Enter 2 to Delete an existing club");
            System.out.println("Enter 3 to Display the various statistics for selected club");
            System.out.println("Enter 4 to Display the Premier League table");
            System.out.println("Enter 5 to Add score for played match");
            System.out.println("Enter 6 to open GUI");

            System.out.println("-----------------------------------------");

            int input1 = intValidation(sc, "Enter the number");
            switch (input1){
                case 1:
                    manager.insertDetails();
                    break;
                case 2:
                    manager.deleteClub();
                    break;
                case 3:
                    manager.displayStat();
                    break;
                case 4:
                    manager.clubList();
                    break;
                case 5:
                    manager.addMatch();
                    break;
                case 6:
                    Gui();
                    break;

                default:
                    System.out.println("Invalid Input!!!Please try again");

            }

        }while(quit !=true);

    }
    public static void main(String[] args) throws IOException {
        launch();
    }
    private static int intValidation(Scanner sc, String label) {
        int number;
        System.out.println(label);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid Input type.Give valid input!");
            sc.next();
        }
        number=sc.nextInt();
        return number;
    }

    public static void saveFile(){//save in a file
        File name = new File("./premierLeague.ser");

        try
        {

            FileOutputStream savefile = new FileOutputStream(name);
            ObjectOutputStream outputStream = new ObjectOutputStream(savefile);

            outputStream.writeObject(manager);
            outputStream.close();
            savefile.close();
            System.out.println("Object has been serialized");

        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    public static int random(int lowerBound, int upperBound) {//to random date and month
        return (lowerBound + (int) Math.round(Math.random()
                * (upperBound - lowerBound)));
    }

    public static boolean LeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        if (noOfDays > 365) {
            return true;
        }

        return false;
    }

    //-------------------------------------------
    private void newaddmethod(Date date1, FootballClub club1, int score1, FootballClub club2, int score2) {

        Date date=date1;
        FootballClub first = club1;
        Integer fscore = score1;
        FootballClub second = club2;
        Integer sscore = score2;

        PlayedMatch m = new PlayedMatch();
        m.setDate(date);
        m.setAteamName(first.getClubName());
        m.setBteamName(second.getClubName());
        m.setAteam(first);
        m.setAScore(fscore);
        m.setBteam(second);
        m.setBScore(sscore);
        manager.getMList().add(m);
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


    private void Gui() {  //to open the gui
          Stage stage = new Stage();

          TableView t1 = new TableView();//Club and statistic table

          TableColumn<String, PremierLeagueManager> c1 = new TableColumn<>("Club name");
          c1.setCellValueFactory(new PropertyValueFactory<>("clubName"));

          TableColumn<String, PremierLeagueManager> c2 = new TableColumn<>("Wins");
          c2.setCellValueFactory(new PropertyValueFactory<>("wins"));

          TableColumn<String, PremierLeagueManager> c3 = new TableColumn<>("Draws");
          c3.setCellValueFactory(new PropertyValueFactory<>("draws"));

          TableColumn<String, PremierLeagueManager> c4 = new TableColumn<>("Defeats");
          c4.setCellValueFactory(new PropertyValueFactory<>("defeats"));

          TableColumn<String, PremierLeagueManager> c5 = new TableColumn<>("ScoredGoals");
          c5.setCellValueFactory(new PropertyValueFactory<>("ScoredGoals"));

          TableColumn<String, PremierLeagueManager> c6 = new TableColumn<>("Points");
          c6.setCellValueFactory(new PropertyValueFactory<>("points"));


          t1.getColumns().addAll(c1, c2, c3, c4, c5, c6);//add all columns to the table


          for (FootballClub club : this.manager.getClubList()) {
              t1.getItems().add(club);

          }
        /*
        Display the gui.Here displayes the statistics foe relevent club like wins,draws,defeats,scored goals
         */
          Label l1 = new Label("01.Display the list");//first table
          l1.setLayoutY(50);
          l1.setLayoutX(500);
          l1.setPrefSize(100,10);

          Button btn1 = new Button("*According to points");
          btn1.setPrefSize(150,10);
          btn1.setLayoutX(520);
          btn1.setLayoutY(90);
          btn1.setStyle("-fx-background-color: #8395a7;-fx-font-size:1.2em;-fx-background-radius: 20; -fx-text-fill: white;");

        Button btn2 = new Button("*According to goals");
        btn2.setPrefSize(150,10);
          btn2.setLayoutX(520);
          btn2.setLayoutY(130);
          btn2.setStyle("-fx-background-color: #8395a7;-fx-font-size:1.2em;-fx-background-radius: 20; -fx-text-fill: white;");

        Button btn3 = new Button("*According to wins");
        btn3.setPrefSize(150,10);
          btn3.setLayoutX(520);
          btn3.setLayoutY(170);
          btn3.setStyle("-fx-background-color: #8395a7;-fx-font-size:1.2em;-fx-background-radius: 20; -fx-text-fill: white;");


        Button b2 = new Button("02.Generate a match");
        b2.setPrefSize(180,10);
          b2.setLayoutY(230);
          b2.setLayoutX(500);
            b2.setStyle("-fx-background-color: #8395a7;-fx-font-size:1.2em;-fx-background-radius: 20; -fx-text-fill: white;");

        Button b3 = new Button("03.Display the match list");
          b3.setLayoutY(290);
          b3.setLayoutX(500);
          b3.setPrefSize(180,10);
          b3.setStyle("-fx-background-color: #8395a7;-fx-font-size:1.2em;-fx-background-radius: 20; -fx-text-fill: white;");



        btn1.setOnAction(event -> { //to get club list
              t1.getItems().clear();
              for (FootballClub club : this.manager.getClubList()) {
                  t1.getItems().add(new FootballClub(club.getClubName(), club.getWins(), club.getDraws(), club.getDefeats(), club.getScoredGoals(), club.getPoints()));

              }

          });

          btn2.setOnAction(event -> {   //to get club list according to the goals
              t1.getItems().clear();
              for (FootballClub club : this.manager.getClubListByGoals()) {
                  t1.getItems().add(new FootballClub(club.getClubName(), club.getWins(), club.getDraws(), club.getDefeats(), club.getScoredGoals(), club.getPoints()));
              }
          });

          btn3.setOnAction(event -> {       ///to get list according to wins
              t1.getItems().clear();
              for (FootballClub club : this.manager.getClubListByWins()) {
                  t1.getItems().add(new FootballClub(club.getClubName(), club.getWins(), club.getDraws(), club.getDefeats(), club.getScoredGoals(), club.getPoints()));
              }
          });

          b2.setOnAction(event -> {     //generate a match.this includes date 2 clubs and 2 scores
              Label datelbl = new Label("Date");
              datelbl.setLayoutX(60);
              datelbl.setLayoutY(50);
              Label genlbl1 = new Label("Club names");
              genlbl1.setLayoutY(50);
              genlbl1.setLayoutX(150);
              Label genlbl2 = new Label("Scores");
              genlbl2.setLayoutX(300);
              genlbl2.setLayoutY(50);

              TextField genclub1 = new TextField(); //textfield foe diaplay random club one

              genclub1.setLayoutY(80);
              genclub1.setLayoutX(150);
              TextField genclub2 = new TextField();//textfield foe diaplay random club two
              genclub2.setLayoutX(150);
              genclub2.setLayoutY(110);
              TextField genscore1 = new TextField();
              genscore1.setLayoutX(300);
              genscore1.setLayoutY(80);
              TextField genscore2 = new TextField();
              genscore2.setLayoutX(300);
              genscore2.setLayoutY(110);
              Label datelbl2 = new Label();
              datelbl2.setLayoutX(40);
              datelbl2.setLayoutY(80);
              Button btnadd=new Button("Add");  //to add match
              btnadd.setPrefSize(60,10);
              btnadd.setLayoutX(370);
              btnadd.setLayoutY(150);
//----------------------------------------------------------------------------------------

              Random random2 = new Random();    //to generate random 2 clubs in the club list
              int randomitem = random2.nextInt(manager.getClubList().size());
              FootballClub randomClub = manager.getClubList().get(randomitem);


              genclub1.setText(randomClub.getClubName());
              boolean same = true;
              FootballClub randomClub2 = null;
              while (same) {
                  int randomitem2 = random2.nextInt(manager.getClubList().size());
                  if (randomitem2 == randomitem) {
                      same = true;
                  } else {
                      same = false;
                      randomClub2 = manager.getClubList().get(randomitem2);

                      genclub2.setText(randomClub2.getClubName());
                  }
              }
              int score1 = random2.nextInt(manager.getClubList().size());//to generate 2 scores for 2 clubs
              int score2 = random2.nextInt(manager.getClubList().size());

              genscore1.setText(String.valueOf(score1));
              genscore2.setText(String.valueOf(score2));

              int yyyy = random(2015, 2020);
              int mm = random(1, 12);
              int dd = 0;

              switch (mm) {
                  case 2:
                      if (LeapYear(yyyy)) {
                          dd = random(1, 29);
                      } else {
                          dd = random(1, 28);
                      }
                      break;

                  case 1:
                  case 3:
                  case 5:
                  case 7:
                  case 8:
                  case 10:
                  case 12:
                      dd = random(1, 31);
                      break;

                  default:
                      dd = random(1, 30);
                      break;
              }

              String year = Integer.toString(yyyy);
              String month = Integer.toString(mm);
              String day = Integer.toString(dd);

              Date d = new Date(year+"/"+month+"/"+day);

              if (mm < 10) {
                  month = "0" + mm;
              }

              if (dd < 10) {
                  day = "0" + dd;
              }

              datelbl2.setText(year+ "/"+month + "/" + day);
              FootballClub finalRandomClub = randomClub2;
              btnadd.setOnAction(event1 -> {
                newaddmethod(d, randomClub, score1, finalRandomClub, score2);//call newaddmethod method to add a match and update new statistics
            });


              Pane pane1 = new Pane();
              pane1.getChildren().addAll(genlbl1, genlbl2, genclub1, genclub2, genscore1, genscore2, datelbl, datelbl2,btnadd);
              Stage stage1 = new Stage();
              stage1.setScene(new Scene(pane1, 600, 300));
              stage1.showAndWait();
          });

          b3.setOnAction(event -> { //match table
              TableView t2 = new TableView();
                t2.setMinWidth(450);

              TableColumn co1=new TableColumn("Date");
              co1.setCellValueFactory(new PropertyValueFactory<>("date"));
              co1.setCellFactory(column -> {
                  TableCell<PlayedMatch, Date> cell = new TableCell<PlayedMatch, Date>() {
                      private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

                      @Override
                      protected void updateItem(Date part, boolean nothing) {
                          super.updateItem(part, nothing);
                          if(nothing) {
                              setText(null);
                          }
                          else {
                              this.setText(format.format(part));

                          }
                      }
                  };

                  return cell;
              });

    /*
    for display a match list like a table.here this includes date of the match played, two clubs and two scores of the relevent clubs
     */

              TableColumn<String, PremierLeagueManager> co2 = new TableColumn<>("First Club");
              co2.setCellValueFactory(new PropertyValueFactory<>("c1name"));

              TableColumn<String, PremierLeagueManager> co4 = new TableColumn<>("FirstScore");
             co4.setCellValueFactory(new PropertyValueFactory<>("AScore"));

              TableColumn<String, PremierLeagueManager> co3 = new TableColumn<>("Second Club");
              co3.setCellValueFactory(new PropertyValueFactory<>("c2name"));

              TableColumn<String, PremierLeagueManager> co5 = new TableColumn<>("SecondScore");
             co5.setCellValueFactory(new PropertyValueFactory<>("BScore"));

             t2.getColumns().addAll(co1, co2, co4, co3, co5);//to add colums to the second table

              for (PlayedMatch m : this.manager.getMatchListByDate()) {//get match list according to date played matches
                 t2.getItems().add(m);

              }
              Label label1=new Label("Enter the date:");
              label1.setLayoutX(100);
              label1.setLayoutY(440);
              TextField textField1=new TextField();
              textField1.setLayoutY(460);
              textField1.setLayoutX(100);
              Button search=new Button("Search");   //find matches for relevent date
              search.setLayoutY(460);
              search.setLayoutX(300);
              Label show=new Label();
              show.setLayoutY(510);
              show.setLayoutX(300);
              search.setOnAction(event1 -> {
                  t2.getItems().clear();
                  Date day=null;
                  SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                  String day2=textField1.getText();
                  try {
                      day=format.parse(day2);
                  } catch (ParseException e) {
                      e.printStackTrace();
                  }


                  for(PlayedMatch Match2:this.manager.getMList()){
                      if(day.equals(Match2.getDate())){
                          t2.getItems().add(Match2);
                      }

                      }

              });


              Pane pane2 = new Pane();
              pane2.getChildren().addAll(t2,label1,textField1,search,show);
              Stage stage2 = new Stage();
              stage2.setScene(new Scene(pane2, 530, 550));
              stage2.setTitle("Match table");
              stage2.showAndWait();

          });

          Pane pane = new Pane();
          pane.getChildren().addAll(t1, l1, b2, b3, btn1, btn2, btn3);
          stage.setScene(new Scene(pane, 800, 450));
          stage.setTitle("Premier league table");
          stage.showAndWait();

      }
}