public class Player {
    private final int playerID;
    private String name;
    private static int countID=1;
    private int point = 0;
    public Player(String name) {
        this.playerID = countID;
        this.name = name;
        countID++;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getPoint(){
        return this.point;
    }
    public void setPoint(int point){
        this.point=point;
    }
    public void addPoint(int point){
        this.point += point;
    }
}
