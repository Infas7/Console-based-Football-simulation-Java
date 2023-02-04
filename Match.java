
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Match{
    private Team team1;
    private Team team2;
    private String tossWinTeam;
    private String previousGoalScorer = null;
    private String previousGoalScoredTeam = null;
    private String currentMatchStage;
    private int timer = 0;

    //Starting point of the function
    public void start() throws InterruptedException, IOException{
        
        gameSetUp();
        
        currentMatchStage = "1st HALF";
        int timeLimit = 45;
        play(timeLimit);

        breakTimeDisplay();

        currentMatchStage = "2nd HALF";
        timeLimit = 90;
        play(timeLimit);

        if(team1.getTotalGoalScored() == team2.getTotalGoalScored()){
            System.out.println("\t\t M A T C H   D R A W N");
            breakTimeDisplay();
            System.out.println("\nHeading to EXTRA TIME........");

            timeLimit = 105;
            currentMatchStage = "Extra time - 1st half";
            play(timeLimit);

            breakTimeDisplay();

            currentMatchStage = "Extra time - 2nd half";
            timeLimit = 120;
            play(timeLimit);

        }

        if(team1.getTotalGoalScored() > team2.getTotalGoalScored()){
            System.out.println("\n\nThe WINNER is "  + team1.getTeamName() + "...Congradulations!!!");
            System.out.println("\n\tMan of the match : " + team1.getHighestGoalScorer());
            System.out.println("\n\n************** Final Score **************\n");
            System.out.println( "\t" + team1.getTeamName() + " - " + team1.getTotalGoalScored() + "  | coach : " + team1.getCoach() + "  | Doctor : " + team1.getDoctor());
            System.out.println( "\t" + team2.getTeamName() + " - " + team2.getTotalGoalScored() + "  | coach : " + team2.getCoach() + "  | Doctor : " + team2.getDoctor());

        }else if(team1.getTotalGoalScored() < team2.getTotalGoalScored()){
            System.out.println("\n\nThe WINNER is "  + team2.getTeamName() + "...Congradulations!!!");
            System.out.println("Man of the match : " + team2.getHighestGoalScorer());
            System.out.println("\n\n************** Final Score **************\n");
            System.out.println( "\t" + team1.getTeamName() + " - " + team1.getTotalGoalScored() + "  | coach : " + team1.getCoach() + "  | Doctor : " + team1.getDoctor());
            System.out.println( "\t" + team2.getTeamName() + " - " + team2.getTotalGoalScored() + "  | coach : " + team2.getCoach() + "  | Doctor : " + team2.getDoctor());
        }else{
            System.out.println("\n\nOops!!! Match drawn even after extra time...");
            System.out.println("\n\n************** Final Score **************\n");
            System.out.println( "\t" + team1.getTeamName() + " - " + team1.getTotalGoalScored() + "  | coach : " + team1.getCoach() + "  | Doctor : " + team1.getDoctor());
            System.out.println( "\t" + team2.getTeamName() + " - " + team2.getTotalGoalScored() + "  | coach : " + team2.getCoach() + "  | Doctor : " + team2.getDoctor());
        }


        if(team1.getTotalGoalScored() != 0){
            System.out.println("\n Goal scorers from " + team1.getTeamName());
            team1.getAllTheGoalScorers();
        }
        System.out.println();

        if(team2.getTotalGoalScored() != 0){
            System.out.println("\n Goal scorers from " + team2.getTeamName());
            team2.getAllTheGoalScorers();
        }
        
    }


    //Function to display scores during break time
    public void breakTimeDisplay() throws InterruptedException{

        System.out.println("\n\t--- H A L F  T I M E ---");
        System.out.println("\nBreak time - 15 (Please be patience)");
        System.out.println("\nTeam 1 : " + team1.getTeamName() + "     Vs     " + "Team 2 : " + team2.getTeamName() );
        System.out.println("       " + team1.getTotalGoalScored() + "                       " + team2.getTotalGoalScored());

        if(team1.getTotalGoalScored() != 0){
            System.out.println("\nGoal scorers from " + team1.getTeamName());
            team1.getAllTheGoalScorers();
        }else{
            System.out.println("\nNo Goals from " + team1.getTeamName() + " yet.");
        }

        System.out.println();

        if(team2.getTotalGoalScored() != 0){
            System.out.println("\nGoal scorers from " + team2.getTeamName());
            team2.getAllTheGoalScorers();
        }else{
            System.out.println("\nNo Goals from " + team2.getTeamName() + " yet.");
        }
        TimeUnit.SECONDS.sleep(15); //Pause the console window for 15s
}


    //Funtion to perform coin toss and decide the starting team
    public void coinToss(){
        Random rand = new Random();
        boolean toss = rand.nextBoolean();
        if(toss) {
            tossWinTeam = "team 1";
        }else{
            tossWinTeam = "team 2";
        } 
    }

    //Function to configure the initial settings
    public void gameSetUp() throws IOException{
        Scanner scan = new Scanner(System.in);
        String ipTeamName, ipCoachName, ipDoctorName;


        System.out.println("\n\tW E L C O M E   T O   F O O T B A L L   A R E N A");
        System.out.println("\t---------------------------------------------------\n");
        System.out.println("Please enter following details to start the match,");

        System.out.println("\n\t\t TEAM 1");
        System.out.println("\t\t________");
        System.out.print("\nTeam name : "); ipTeamName = scan.nextLine();
        System.out.print("Coach name : "); ipCoachName = scan.nextLine();
        System.out.print("Doctor name : "); ipDoctorName = scan.nextLine();
        this.team1 = new Team(ipTeamName, ipCoachName, ipDoctorName);
        team1.setPlayers();
       
        
        System.out.println("\n\n\t\t TEAM 2");
        System.out.println("\t\t________");
        System.out.print("\nTeam name : "); ipTeamName = scan.nextLine();
        System.out.print("Coach name : "); ipCoachName = scan.nextLine();
        System.out.print("Doctor name : "); ipDoctorName = scan.nextLine();
        this.team2 = new Team(ipTeamName, ipCoachName, ipDoctorName);
        team2.setPlayers();
        

        System.out.println("\n");
        coinToss();
        System.out.println( tossWinTeam + " Won the coin toss...And will Start the game.");
        System.out.println("\n   " + team1.getTeamName() + "\t\t     " + team2.getTeamName());
        System.out.println("------------\t\t------------");
        for(int i = 0; i < 11; i++){
            System.out.println(team1.getPlayerAtIndex(i) + "\t\t\t" + team2.getPlayerAtIndex(i));
        }
        System.out.println("\nCoach : \t\tCoach : ");
        System.out.println(team1.getCoach() + "\t\t\t" + team2.getCoach());

        System.out.println("\nDoctor : \t\tDoctor : ");
        System.out.println(team1.getDoctor() + "\t\t\t" + team2.getDoctor());

        System.out.println("\tPress any key to continue.....");
        System.in.read();

        scan.close();
    }

    //Function to output scoreboard to the console.
    public void displayScoreBoard(){
        System.out.println("\n___________ S C O R E  B O A R D ___________");
        System.out.println("\n\t\t" + currentMatchStage);
        System.out.println("\n\t     GAME TIME - " + timer );
        System.out.println("\nTeam 1 : " + team1.getTeamName() + "     Vs     " + "Team 2 : " + team2.getTeamName() );
        System.out.println("       " + team1.getTotalGoalScored() + "                       " + team2.getTotalGoalScored());
        if(previousGoalScorer == null){
            System.out.println("\t\nPrevious Goal Scored By : ------ ");
        }else{
            System.out.println("\t\nPrevious Goal Scored By : " + previousGoalScorer + " (" + previousGoalScoredTeam + ")");
        }
       
    }


    //Funtion to start playing the game.
    public void play(int timeLimit) throws InterruptedException{

        Team attackingTeam;
        Team defendingTeam;
        Team temp; //for swapping places (attack /defend)

        //Assigning the roles(attaking / defending) according to the coin toss
        if(tossWinTeam == "team 1"){
                attackingTeam = team1;
                defendingTeam = team2;
        }else{
                attackingTeam = team2;
                defendingTeam = team1;
        }

        attackingTeam.setCurrentBallPosition(1);
        defendingTeam.setCurrentBallPosition(0);
        Random rand  = new Random();

        while(timer <= timeLimit){
            
            temp = null;
            displayScoreBoard();
            
            //if attacking fails switching attacking and defending roles and resetting power of the players
            if(!attackingTeam.attack()){
                
                temp = attackingTeam;
                attackingTeam = defendingTeam;
                defendingTeam = temp;
                
                attackingTeam.resetPower();
                defendingTeam.resetPower();
                attackingTeam.shufflePlayerPossitions();
                defendingTeam.shufflePlayerPossitions();
                int randPos = rand.nextInt(10) + 1;
                System.out.println(attackingTeam.getPlayerAtIndex(randPos) + " from " + attackingTeam.getTeamName() + " recieved the ball...");
                attackingTeam.setCurrentBallPosition(randPos);
                defendingTeam.setCurrentBallPosition(0);
                
            }else{//if ball went to the opposition box ready to being shoot towards the goal

                //Comparing the current power of the shooting and opposition goalies power and deciding the goal
                if(attackingTeam.getShootersPower() > defendingTeam.getGoliesPower()){
                    System.out.println(attackingTeam.getPlayerWhoHasTheBall() + " Shooting !!!!");
                    System.out.println("\n\t\t G O A L !!!");
                    System.out.println("\tWhat a strike by : " + attackingTeam.getPlayerWhoHasTheBall());
                    attackingTeam.setTotalGoalScored();
                    attackingTeam.updateGoalScorer();
                    previousGoalScorer = attackingTeam.getPlayerWhoHasTheBall();
                    previousGoalScoredTeam = attackingTeam.getTeamName();

                    //Switching roles after successfull attack (goal)
                    temp = attackingTeam;
                    attackingTeam = defendingTeam;
                    defendingTeam = temp;
                    
                    attackingTeam.resetPower();
                    defendingTeam.resetPower();
                    attackingTeam.shufflePlayerPossitions();
                    defendingTeam.shufflePlayerPossitions();
                    attackingTeam.setCurrentBallPosition(1);
                    defendingTeam.setCurrentBallPosition(0);
                    
                }else{
                    System.out.println(attackingTeam.getPlayerWhoHasTheBall() + " Shooting !!!!");
                    System.out.println("\n\tWhat a  SAVE  by " + defendingTeam.getPlayerWhoHasTheBall() + " (GK)");
                    System.out.println("\tShot missed by " + attackingTeam.getPlayerWhoHasTheBall());
                    
                    //Switching roles after opposition's save and resetting the power
                    temp = attackingTeam;
                    attackingTeam = defendingTeam;
                    defendingTeam = temp;

                    attackingTeam.resetPower();
                    defendingTeam.resetPower();
                    attackingTeam.shufflePlayerPossitions();
                    defendingTeam.shufflePlayerPossitions();
                    attackingTeam.setCurrentBallPosition(rand.nextInt(10) + 1);
                    defendingTeam.setCurrentBallPosition(0);
                    
                }
            }
            timer++;
            TimeUnit.SECONDS.sleep(3);
            cls();
        }
    }

    //Function to clear console screen 
    public static void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}