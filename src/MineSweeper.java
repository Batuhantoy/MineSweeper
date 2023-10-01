import java.util.*;

public class MineSweeper {
    private String[][] fieldVisible;
    private String[][] fieldHidden;
    private Player player;
    private int emptyPointCount;
    Scanner sc = new Scanner(System.in);


    public MineSweeper(Player player) {
        this.player = player;
    }

    // Oyun alanlarımızı hazırlayan metod
    public void newGame(int rowNumber, int colNumber){
        fieldVisible = new String[rowNumber][colNumber];
        fieldHidden = new String[rowNumber][colNumber];
        int mineCount = Math.round((rowNumber*colNumber) / 4);

        for (String[] row : fieldVisible) {
            Arrays.fill(row, "-");
        }
        for (String[] row : fieldHidden) {
            Arrays.fill(row, "0");
        }

        Random random = new Random();
        for(int i=0;i<mineCount;i++) {
            int a = random.nextInt(rowNumber);
            int b = random.nextInt(colNumber);
            fieldHidden[a][b] = "*";
        }
        emptyPointCount = ((colNumber*rowNumber)-howMany("*")); //Mayınsız kaç tane nokta var onu hesaplayıp değişkene atıyoruz
    }

    // Oyunu başlatmamızı sağlayan metod
    public void start(){
        //printField(fieldVisible);
        System.out.println("Oyunu kaydetmek için satır ve sütun sayısı olarak -1 yazmanız yeter");
        System.out.println("---------GAME İS LİVE---------");

        boolean isGameContinues = true; //while döngümüzü bitirecek durumlar oldugunda flag=false yapıyoruz bu sayede döngü bozuluyor ve oyun bitiyor
        int ones = howMany("1"); //Açılan nokta sayısını tutuyoruz, mayına basılmazsa yani boş yerler açıldıgında bu değikşeni bir arttırıyoruz
        while(isGameContinues) {
            System.out.println("------Hidden------");
            printField(fieldHidden);
            System.out.println("------Visible------");
            printField(fieldVisible);
            System.out.println("------------");

            System.out.println("PUAN : "+player.getPoint());
            System.out.print("Satır : ");
            int row = sc.nextInt();
            System.out.print("Sütun : ");
            int col = sc.nextInt();

            if(row==-1 || col==-1){
                SavedGame.saveGame(this.player,fieldVisible,fieldHidden);
                break;
            }
            while(row<0 || col<0 || row>=fieldHidden.length || col>=fieldHidden[0].length){
                System.out.println("Hatalı Satır veya sütun numarası girdiniz, lütfen tekrar giriniz!!!");
                System.out.print("Satır : ");
                row = sc.nextInt();
                System.out.print("Sütun : ");
                col = sc.nextInt();
            }

            if(fieldHidden[row][col] == "*") {
                System.out.println("Oyun bitti, kaybettin!!!");
                System.out.println("-----------");
                printField(fieldHidden);
                System.out.println("-----------");
                isGameContinues=false;
            }
            else {
                // Mayına basmadıysa o noktanın etrafındaki mayın sayısını o noktaya yazdırıyoruz ve mayın sayısına göre puan veriyoruz
                if(fieldHidden[row][col].equals("0")) {
                    int neighbourMines = findNeighbors(row, col);
                    fieldVisible[row][col]=Integer.toString(neighbourMines);
                    player.addPoint(2 + neighbourMines * 3);
                    fieldHidden[row][col]="1";
                    ones++;

                    // Kullanıcının sorunsuz açtığı nokta sayısı mayınsız nokta sayısına eşitse oyun başarıyla bitiyor
                    if(ones == emptyPointCount) {
                        System.out.println("Tebrikler, kazandınız!!!");
                        System.out.println("-----------");
                        printField(fieldHidden);
                        System.out.println("-----------");
                        isGameContinues = false;
                    }
                }
            }
            System.out.println();
        }
    }

    // Kaydedilen oyuna kaldığımız yerden devam etmemizi sağlayan metod
    public void start(SavedGame savedGame){
        this.fieldHidden = savedGame.getFieldHidden();
        this.fieldVisible = savedGame.getFieldVisible();
        this.player.setPoint(savedGame.getPlayer().getPoint());
        emptyPointCount = ((savedGame.getFieldHidden().length * savedGame.getFieldHidden()[0].length) - howMany("*"));
        start();
    }

    //Alanlarımızı(fieldHidden, fieldVisible) console'a yazdıran metod
    void printField(String[][] field) {
        for(String[] row : field) {
            for(String str : row) {
                System.out.print(str+" ");
            }
            System.out.println();
        }
    }
    // Mayın tarlasındaki bir noktanın komşularında mayın varsa, toplam mayın sayısını bulup döndüren metod
    int findNeighbors(int row,int col){
        int count=0;

        int rStartIndex = (row-1<0) ? 0 : row-1;
        int rEndIndex = (row+1>=fieldHidden.length) ? row : row+1;

        int cStartIndex = (col-1<0) ? 0 : col-1;
        int cEndIndex = (col+1>=fieldHidden[row].length) ? col : col+1;

        for(int i=rStartIndex;i<=rEndIndex;i++) {
            for(int j=cStartIndex;j<=cEndIndex;j++) {
                if(fieldHidden[i][j].equals("*")) {
                    count++;
                }
            }
        }
        return count;
    }

    // Parametre olarak verilen değerin fieldHidden içinde kaç adet olduğunu hesaplayıp geri döndüren metod
    int howMany(String s) {
        int count=0;
        for(int i=0;i<fieldHidden.length;i++) {
            for(int j=0;j<fieldHidden[i].length;j++) {
                if(fieldHidden[i][j].equals(s)) {
                    count++;
                }
            }
        }
        return count;
    }
}