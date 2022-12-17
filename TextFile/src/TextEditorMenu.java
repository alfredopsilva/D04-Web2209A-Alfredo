import utility.console.ConsoleMenu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    private static void writeTextFile() throws IOException
    {
        try(FileWriter writer = new FileWriter("Files/text.txt",true))
        {
            writer.write(getUserInput());
            writer.flush();
        }

    }

    public static String getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type : ");
        return scanner.nextLine() + "\n";
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
