package yandex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TaskB {

    public static void main(String[] args) {

        //GET INITIAL DATA
        List<String> initialData = new LinkedList<>();

        try{
            FileReader reader = new FileReader("src/yandex/TaskBIn.txt");

            try {
                Scanner input = new Scanner(reader);

                while (input.hasNext()) {
                    initialData.add(input.nextLine());
                }
            }
            finally {
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //GET WORKING ARRAYS OF PHONES & TEMPLATES
        short amountN = 0;
        short amountM = 0;

        try {
            amountN = Short.valueOf(initialData.get(0));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[][] phones = new String[amountN][2];
        initialData.remove(0);

        for (short i = 0; i < amountN; i++) {
            String temp = initialData.get(0).replaceAll("[-+() ]","");
            phones[i][1] = temp;
            initialData.remove(0);
        }

        try {
            amountM = Short.valueOf(initialData.get(0));
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[][] templates = new String[amountM][4];
        initialData.remove(0);
        Collections.sort(initialData);

        for (short i = 0; i < amountM; i++) {
            String[] tempArray = initialData.get(0).split(" ",4);
            initialData.remove(0);

            for (byte j = 0; j < templates[i].length; j++) {

                if (j == 0) {
                    tempArray[j] = tempArray[j].replaceAll("[+]","");
                }
                if (j == 1) {
                    tempArray[j] = tempArray[j].replaceAll("[()]","");
                }
                templates[i][j] = tempArray[j];
            }
        }

        //SEARCH AND TRANSFORM PHONES BY TEMPLATES
        for (short i = 0; i < templates.length; i++) {
            String tempCode = templates[i][0] + templates[i][1];

            for (short j = 0; j < phones.length; j++) {

                if (phones[j][0] != null) {
                    continue;
                }

                if (phones[j][1].indexOf(tempCode) == 0) {
                    String phoneNumber = phones[j][1].substring(tempCode.length());

                    if (phoneNumber.length() == templates[i][2].length()) {
                        StringBuilder tempNumber = new StringBuilder();
                        int counter = 0;

                        while (Character.isDigit( templates[i][2].charAt(counter))){
                            tempNumber.append( templates[i][2].charAt(counter));
                            counter++;

                            if (counter >= templates[i][2].length()){
                                break;
                            }
                        }

                        if (tempNumber.length() == 0 || phoneNumber.indexOf(tempNumber.toString()) == 0){
                            phones[j][1] = "+" + templates[i][0] + " (" + templates[i][1] + ") "
                                    + phoneNumber + " " + templates[i][3];
                            phones[j][0] = "full";
                        }
                    }
                }
            }
        }

        //GET THE FINISED RESULT
        try {
            FileWriter writer = new FileWriter("src/yandex/TaskBOut.txt");

            try {
                for (short i = 0; i < phones.length; i++) {
                    writer.write(phones[i][1] + "\n");
                }
            }
            finally {
                writer.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
