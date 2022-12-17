import utility.console.ConsoleMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextEditorMenu {


    private final ConsoleMenu menu;
    TextEditorMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add(">  Write File");
        options.add(">  Read File");
        options.add(">  Exit File");

        menu = new ConsoleMenu("Text Editor", "Select an Option :", options);
    }

    public void start() throws IOException {
        while(true)
        {
            int selection = menu.displayAndGetSelection();
            switch (selection)
            {
                case 1: writeTextFile();  break;
                case 2: readTextFile();   break;
                case 3: return;
            }
        }
    }

    private static void writeTextFile() throws IOException {
        File file = new File(getUserPath());
        if(file.exists()){
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(getUserInput());
                writer.flush();
            }
        }else {
            System.out.println("This path doesn't exist.");
        }
    }

    public static String getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type : ");
        return scanner.nextLine() + "\n";
    }

    public static String getUserPath()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file's path : ");
        return scanner.nextLine();
        //throw = statment similiar to return
        //throws =
    }



    private static void readTextFile() throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader("Files/text.txt")))
        {
            while(true)
            {
                String line = reader.readLine();
                if(line != null)
                    System.out.println(line);
                else
                    break;
            }
        }
    }
}
