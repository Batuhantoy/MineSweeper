import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");
        System.out.println("Oyunun amacı basit, mayınlara basmadan oyunu tamamlamanız lazım");
        System.out.println("Seçtiğiniz noktaya yakın ne kadar çok mayın varsa o kadar fazla puan kazanırsınız");
        System.out.println("***********************************************************************************");
        System.out.print("İsminiz : ");
        String userName = sc.nextLine();

        // Oyuncumuzu MineSweeper sınıfına veriyoruz ve nesnemizi oluşturuyoruz
        MineSweeper game = new MineSweeper(new Player(userName));
        while (true){
            System.out.println("1 - Yeni Oyun\n2- Oyuna Devam Et\n0 - Çıkış");
            System.out.print("Tercihiniz : ");
            int selected = sc.nextInt();
            while(selected<0 || selected>2){
                System.out.print("Geçerli değer giriniz, seçiminiz : ");
                selected = sc.nextInt();
            }
            switch (selected){
                case 1:
                    System.out.print("Satır sayısı : ");
                    int row = sc.nextInt();
                    System.out.print("Sütun sayısı : ");
                    int col = sc.nextInt();
                    game = new MineSweeper(new Player(userName));
                    game.newGame(row,col);
                    game.start();
                    break;
                case 2:
                    // Kayıtlı oyun yoksa kullanıcıyı bilgilendiriyoruz
                    if(SavedGame.getSavedGameList().isEmpty()){
                        System.out.println("Kayıtlı oyun yok!!!");
                        break;
                    }
                    // Kaydedilmiş oyunları console ekranına yazan metod
                    SavedGame.printSavedGames();
                    System.out.print("Hangi kayıttan devam etmek istiyorsunuz?");
                    System.out.println();
                    int selectedGameID = sc.nextInt();
                    // ID'sine göre seçtiğimiz kaydı alıp start(SavedGame savedGame) metodumuza veriyoruz
                    game.start(SavedGame.getSavedGameByID(selectedGameID));
                    break;
                case 0:
                    System.out.println("Güle Güle, Yine Bekleriz :)))");
                    return;
            }
        }

    }
}