import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class mainApp {
    private static final String COMMA_DELIMITER = ",";
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        System.out.println("Welcome!");
        try {
            //Start capturing
            Capturer.capture();

            //Read .csv file and put everything in a list
            List<Pairs.Pair<String,Integer>> records = new ArrayList<>();
            HashMap<String , Integer> map = new HashMap<String,Integer>();
            try (BufferedReader br = new BufferedReader(new FileReader("docs/output-01.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(COMMA_DELIMITER);
                    if(values.length>13 && !values[13].equals(" ")){
                        values[3]=values[3].replaceAll("\\s+","");
                        Pairs.Pair pair = new Pairs.Pair<>(values[13],values[3]);
                        records.add(pair);
                        if(!values[3].equals("channel")){
                            map.put(values[13].replaceAll("\\s+",""),Integer.parseInt(values[3]));
                        }
                    }

                }

                //Query user and print read ESSID's
                System.out.println("Show ESSID's found ?(Y/N)");
                Scanner scanner = new Scanner(System.in);
                String answer=scanner.nextLine();
                if(answer.equals("Y") || answer.equals("y")){
                    System.out.println("ESSID " + ": " + "Channel");
                    for (String keys : map.keySet())
                    {
                        System.out.println(keys + " : "+ map.get(keys));
                    }

                }

                //Query for ESSID to attack
                System.out.println("Give the name of the ESSID you want to attack: ");
                answer=scanner.nextLine();

                UpdateThread thread = new UpdateThread();
                while(flag){
                    if(thread.isAlive()){
                        thread.stop();
                    }
                    else{
                        thread.start();
                    }
                }

                //Query for quit
                System.out.println("Want to quit ? ");
                answer=scanner.nextLine();
                if(answer.equals("Y") || answer.equals("y")){
                    flag=false;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

