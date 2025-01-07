public class PlayerControler {
    private boolean isALive;  //Player Status
    private int score; //Current score

    public PlayerControler() {
        this.isALive = true; //Player starts alive
        this.score = 0; //Start score
    }

    public void incrementScore() {
        this.score += 1; //Score change method
    }

    public void killPlayer() {
        this.isALive = false; //Player status chande
    }

    //Getter player status
    public boolean isALive() {
        return isALive; //
    }
    //Getter score status
    public int getScore() {
        return score;
    }
    //game restart
    public void restartGame() {
        this.isALive = true;
        this.score = 0;
    }

}
