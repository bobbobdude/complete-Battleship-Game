import java.util.Random;

public final class generalMethods {

    public static void printStringSlowlyToScreen(String toPrintToScreen) throws InterruptedException{

        char[] chars = toPrintToScreen.toCharArray();

        Random timeChanger = new Random();

        for (char c : chars){
            System.out.print(c);
            System.out.flush();
            if (c == '.'){
                Thread.sleep(1000);
                System.out.println();
            }
            else if (c == ','){
                Thread.sleep(100);
            }
            Thread.sleep(timeChanger.nextInt(70));
        }

        System.out.println();
    }

}
