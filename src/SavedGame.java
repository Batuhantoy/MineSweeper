import java.util.ArrayList;

public class SavedGame {
    private int id;
    private final Player player;
    private String[][] fieldVisible;
    private String[][] fieldHidden;

    static ArrayList<SavedGame> savedGames= new ArrayList<>();
    public SavedGame(int id,Player player, String[][] fieldVisible, String[][] fieldHidden) {
        this.id = id;
        this.player = player;
        this.fieldVisible = fieldVisible;
        this.fieldHidden = fieldHidden;
    }

    public int getID(){
        return this.id;
    }
    public static SavedGame getSavedGameByID(int id){
        return SavedGame.savedGames.get(id);
    }
    public Player getPlayer() {
        return player;
    }

    public String[][] getFieldVisible() {
        return fieldVisible;
    }

    public String[][] getFieldHidden() {
        return fieldHidden;
    }

    public static void saveGame(Player player, String[][] Visible, String[][] Hidden){
        savedGames.add(new SavedGame(savedGames.size(),player,Visible,Hidden));
    }
    public static ArrayList<SavedGame> getSavedGameList(){
        return savedGames;
    }
    public static void printSavedGames(){
        for(SavedGame save : SavedGame.getSavedGameList()){
            System.out.println("("+save.getID()+")"+" Kullanıcı ismi: "+save.getPlayer().getName()+" - PUAN : "+save.getPlayer().getPoint());
        }
    }

}
