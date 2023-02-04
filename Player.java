import java.util.Random;


public class Player {
    
    private String teamName, playerName;
    private float power;
    private int goalsScored = 0;

    public void setPower(){
        Random random = new Random();
        this.power = random.nextFloat();
    }

    public void setTeam(String name){
        this.teamName = name;
    }

    public void setName(String name){
        this.playerName = name;
    }

    public void setGoalScored(){
        goalsScored++;
    }

    public String getName(){
        return this.playerName;
    }

    public String getTeam(){
        return this.teamName;
    }
    
    public float getPlayerPower(){
        return this.power;
    }

    public int getGoalScored(){
        return this.goalsScored;
    }
}
