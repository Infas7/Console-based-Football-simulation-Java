import java.util.Random;
import java.util.Scanner;

public class Team {
    private Player [] team = new Player[11];
    private String teamName, coach, doctor;
    private int totalGoalsScored;
    private int currentBallPosition;
    private Scanner sc = new Scanner(System.in);


    Team(String teamName, String coach, String dotor){
        this.teamName = teamName;
        this.coach = coach;
        this.doctor = dotor;
        this.currentBallPosition = 0;
    }

    //Fucntion to players' names and their team name
    public void setPlayers(){
        String playerName;
        System.out.println("\nPlease enter players names one by one,");

        for (int i = 0; i < 11; i++) {
            team[i] = new Player();
            team[i].setTeam(teamName);
            team[i].setPower();
            System.out.print("\tPlayer " + (i+1) + " - " ); playerName = sc.nextLine();
            team[i].setName(playerName);
        
            //Assigning the very first player (0th index) in the team array as a goalie
        }
    }


    //funtion to perform attack..will return true if attack is success else will return false.
    public boolean attack(){
        System.out.println("Attacking team (now) : " + teamName);
        System.out.println();

       for(int i = currentBallPosition ; i < 10; i++ ){
           if(team[i].getPlayerPower() > 0.55){
               System.out.println(team[i].getName() + " with the ball...Passing to " + team[i+1].getName());
               currentBallPosition++;
               
           }else if(team[i].getPlayerPower() > 0.2){
               System.out.println("Poor pass by " + team[i].getName()+ "..." + teamName + " Lost position...");
               currentBallPosition = 0;
               return false;
           }else{
               System.out.println("Ball gone out for throwing !");
               currentBallPosition = 0;
               return false;
           }
       }
       return true; 
    }

    //Reset the current power of every player in the team.
    public void resetPower(){
        for (int i = 0; i < 11; i++) {
            team[i].setPower();
        }
    }

    public String getPlayerWhoHasTheBall(){
        return team[currentBallPosition].getName();
    }

    //To get player's name at specific array index
    public String getPlayerAtIndex(int i){
        return team[i].getName();
    }

    //To print all the player names of the current team
    public void printPlayers(){
        System.out.println();
        for( int i = 0; i < 11; i++){
            System.out.println(team[i].getName());
        }
        System.out.println();
    }

    //To increase the goal count by one for the current goal scorer
    public void updateGoalScorer(){
        team[10].setGoalScored();
    }

    public void setCurrentBallPosition(int pos){
        this.currentBallPosition = pos;
    }

    public void setTotalGoalScored(){
        totalGoalsScored++;
    }

    public float getGoliesPower(){
        return team[0].getPlayerPower(); //Goalie is always placed at index 0 and player at the index won't change
    }

    public float getShootersPower(){
        return team[10].getPlayerPower(); //Shooter always placed at index 10 but the player at the index will change
    }

    public String getCoach(){
        return this.coach;
    }

    public String getDoctor(){
        return this.doctor;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public int getCurrentBallPosition(){
        return this.currentBallPosition;
    }

    public int getTotalGoalScored(){
        return this.totalGoalsScored;
    }

    //Fuction to print all the goal scorers of the current team if any
    public void getAllTheGoalScorers(){
        for(int i = 1; i < 11; i++){
            if(team[i].getGoalScored() != 0){
                System.out.println( "\t" + team[i].getName() + " - " + team[i].getGoalScored());
            }
        }
    }

    //Function to get the name of highest goal scorer of the current team
    public String getHighestGoalScorer(){
        String manOfTheMatch = null;
        int goalCount = 0;
        for(int i = 1; i < 11; i++){
            if((team[i].getGoalScored() != 0)  && (team[i].getGoalScored() > goalCount)){
                goalCount = team[i].getGoalScored();
                manOfTheMatch = team[i].getName();
            }
        }
        return manOfTheMatch;
    }
    

    public void shufflePlayerPossitions(){
        // Creating a object for Random class
        Random r = new Random();
        int n = team.length;

        // Start from the last element and swap one by one.
        for (int i = n-1; i > 1; i--) {
              
            // Pick a random index from 1 to i
            int j = r.nextInt(i) + 1;
              
            // Swap team[i] with the element at random index
            Player temp = team[i];
            team[i] = team[j];
            team[j] = temp;
        }
    }

}
