import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Zahlenraten3 {
    //Ai-Variablen
    static boolean aiWinToken = false;
    static int aiCounter = 0;
    static int high;
    static int low = 0;

    //Player-Variablen
    static boolean guessedPLayer = false;
    static int playerNum = 0;
    static ArrayList<Integer> tippsPlayer = new ArrayList<>();

    //Ordianary-Variablen
    static Scanner s = new Scanner(System.in);
    static int randNum = 0;
    static int numRoom = 0;
    static int counter;

    public static void main(String[] args) {
        int roundCounter = 0;
        String again = "Y";
        Random random = new Random();
        System.out.println("""
                *****Willkommen zum Zahlen raten!*****
                In welchem Zahlenraum möchtest du spielen?""");
        numRoom = Integer.parseInt(s.nextLine());
        randNum = random.nextInt(numRoom);
        high = numRoom;
        System.out.println("""
                Ich habe mir nun eine Zufallszahl in deinem Zahlenraum ausgedacht!
                Deine Aufgabe ist es in 9 Versuchen zu erraten, welche es ist!""");
        System.out.println();

        while (again.equalsIgnoreCase("y")) { //Hauptschleife
            tippsPlayer.clear();
            counter = 9;
            guessedPLayer = false;
            aiWinToken = false;
            low = 0;
            high = numRoom;
            int coinToss = random.nextInt(2);
            if (roundCounter > 0) {
                System.out.println();
                System.out.println("Dann beginnen wir von vorne!");
                System.out.println();
            }
            while (counter > 0 && !guessedPLayer && !aiWinToken) { //Spielschleife
                if (coinToss == 1) {
                    if (counter == 9) {
                        System.out.println("Ich fange an! Bereite dich darauf vor zu verlieren!");
                    }
                    aiLogic();
                    if(!aiWinToken){
                        playerLogic();
                    }
                } else {
                    if (counter == 9) {
                        System.out.println("Du fängst zwar an, aber das wird dir nichts nützen");
                    }
                    playerLogic();
                    if(!guessedPLayer){
                        aiLogic();
                    }
                }


            }
            if (guessedPLayer) {
                System.out.println("""
                        Super! Du hast mich geschlagen und und bist ein würdiger gegner.
                        Beim nächsten mal werde ich mich mehr anstrengen!
                        Gibst du mir noch eine Chance?""");
            } else if (aiWinToken) {
                System.out.println("""
                        Ich habe dich besiegt!
                        Du bist meiner eindeutig nicht würdig!
                        Vielleicht schaffst du es ja beim nächsten mal! ;)
                        Willst du nochmal probieren mich zu schlagen?""");
            } else {
                System.out.println("""
                        Unentschieden! Wir haben beide die Lösung nicht gefunden!
                        Willst du es nochmal probieren?""");
            }

            again = "x";
            while (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("n")) {
                again = s.nextLine();
            }
            roundCounter++;
        }
    }

    public static void playerLogic() {
        System.out.println("Deine bisherigen Tipps : " + tippsPlayer);
        System.out.println("Du hast noch " + counter + " Versuche!");
        System.out.print("Bitte gib deinen Tipp ein: ");
        System.out.println();
        playerNum = Integer.parseInt(s.nextLine());

        if (playerNum <= randNum - 1 && playerNum >= randNum - 5) {
            System.out.println("Knapp drunter!");
        } else if (playerNum >= randNum + 1 && playerNum <= randNum + 5) {
            System.out.println("Knapp drüber!");
        } else if (playerNum <= randNum - 5 && playerNum >= randNum - 20) {
            System.out.println("Zu tief!");
        } else if (playerNum >= randNum + 5 && playerNum <= randNum + 20) {
            System.out.println("Zu hoch!");
        } else if (playerNum <= randNum - 20 && playerNum >= randNum - 30) {
            System.out.println("Weit drunter!");
        } else if (playerNum >= randNum + 20 && playerNum <= randNum + 30) {
            System.out.println("Weit drüber!");
        } else if (playerNum < randNum || playerNum > randNum) {
            System.out.println("Komplett weit weg");
        } else {
            System.out.println("Erraten!");
            guessedPLayer = true;
        }
        tippsPlayer.add(playerNum);
        counter--;
        System.out.println();
    }

    public static void aiLogic() {
        int x = aiNumberFinder();
        try {
            System.out.println("Jetzt bin ich an der Reihe!");
            Thread.sleep(3000);
            System.out.println("..hmmm...");
            Thread.sleep(3000);
            System.out.println("Ahja! Ich nehme: " + x);
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }


        if (x < randNum) {
            low = x;
            System.out.println("Oh! das war daneben!");
        } else if (x > randNum) {
            high = x;
            System.out.println("Oh! das war daneben!");
        } else if (x == randNum) {
            aiWinToken = true;
        }
    }


    public static int aiNumberFinder() {
        int x = 0;
        if (aiCounter == 0) {
            Random random = new Random();
            x = random.nextInt(numRoom);
            aiCounter++;

        } else {
            x = (high + low) / 2;
        }
        return x;
    }


}
