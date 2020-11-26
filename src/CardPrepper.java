import java.io.IOException;

public class CardPrepper {
    public static void prepare() throws IOException {
        //Command texts
        String ifconfig_down = "ifconfig wlan0 down";
        String ifconfig_up = "ifconfig wlan0 up";
        String iwconfig_mode_managed = "iwconfig wlan0 mode managed";
        String iwconfig_mode_monitor = "iwconfig wlan0 mode monitor";
        String airmon_start = "airmon-ng start wlan0 ";
        String airmon_kill = "airmon-ng check kill ";
        String mon_ifconfig_down = "ifconfig wlan0 down";
        String change_name = "ip link set wlan0mon name wlan0 ";

        //Kill all conflicting processes and start wlan card in monitor mode
        Process process = Runtime.getRuntime().exec(airmon_kill);
        process = Runtime.getRuntime().exec(airmon_start);
        process = Runtime.getRuntime().exec(mon_ifconfig_down);
        process = Runtime.getRuntime().exec(change_name);
        process = Runtime.getRuntime().exec(ifconfig_up);

        System.out.println("WiFi card in monitor mode and ready to begin capturing...");

    }
}
