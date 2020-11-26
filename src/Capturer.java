import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Capturer {
    public static void capture() throws IOException, InterruptedException {
        //Command texts
        String ifconfig_down = "ifconfig wlan0 down";
        String ifconfig_up = "ifconfig wlan0 up";
        String iwconfig_mode_managed = "iwconfig wlan0 mode managed";
        String iwconfig_mode_monitor = "iwconfig wlan0 mode monitor";
        String capture = "airodump-ng -w /root/IdeaProjects/ADKE_Application/docs/output --output-format csv wlan0";
        String restart_network = "systemctl restart NetworkManager.service";

        //Call prepper to prepare WiFi card for capturing
        CardPrepper.prepare();

        //Start capturing
        System.out.println("Starting capture!");
        Process process = Runtime.getRuntime().exec(capture);
        System.out.println("Capturing...");

        //Wait a set time before killing the capturing process
        process.waitFor(10, TimeUnit.SECONDS);
        System.out.println("Capture complete !");

        //Query user and return to managed mode
        Scanner scanner = new Scanner(System.in);
        System.out.println("Return to managed mode ?(Y/N)");
        String answer=scanner.nextLine();
        if(answer.equals("Y") || answer.equals("y")){
            process = Runtime.getRuntime().exec(ifconfig_down);
            process = Runtime.getRuntime().exec(iwconfig_mode_managed);
            process = Runtime.getRuntime().exec(ifconfig_up);
            process = Runtime.getRuntime().exec(restart_network);

        }

    }
}


