import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UpdateThread extends Thread{
    public void run(){
        System.out.println("Hi I'm a thread");
        //Command texts
        String ifconfig_down = "ifconfig wlan0 down";
        String ifconfig_up = "ifconfig wlan0 up";
        String iwconfig_mode_managed = "iwconfig wlan0 mode managed";
        String iwconfig_mode_monitor = "iwconfig wlan0 mode monitor";
        String capture = "airodump-ng -w /root/IdeaProjects/ADKE_Application/docs/temporary --output-format csv wlan0";
        String restart_network = "systemctl restart NetworkManager.service";

        //Call prepper to prepare WiFi card for capturing
        try {
            CardPrepper.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start capturing
        System.out.println("Starting capture!");
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(capture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Capturing...");

        //Wait a set time before killing the capturing process
        try {
            process.waitFor(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Capture complete !");

            try {
                process = Runtime.getRuntime().exec(ifconfig_down);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                process = Runtime.getRuntime().exec(iwconfig_mode_managed);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                process = Runtime.getRuntime().exec(ifconfig_up);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                process = Runtime.getRuntime().exec(restart_network);
            } catch (IOException e) {
                e.printStackTrace();
            }
            process.destroy();
    }
}
