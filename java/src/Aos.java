package java.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Aos {
    static boolean hadError = false; // Ensures incorrect code is not run

    /**
     * This is the entry point
     * The usage is 
     * If provided with more arguments, the user will ve prompted with an error, indicating the correct syntax
     * Provided the program is given an argument (path) indicating the script to be run, it will process the file
     * If given no path, an interactive command prompt will appear
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: aos [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    /**
     * Given a path, it will digest it and get an array of all the bytes in the file, which convert into a String
     * the run function is then given the array, so it can be interpreted
     * @param path The path to the file to be compiled and run
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) {
            System.exit(65);
        }
    }

    /**
     * When the main program is not given a path, it will display an interactive prompt.
     * To exit gracefully, the prompt listens for an end of file character.
     * This can be achieved by using "Ctrl + D"
     */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        //Will read one line at a time and run it. the line is processed as a byte array, transformed into a String and fed to run()
        while (true) {
            System.out.print("> ");
            String line = reader.readLine(); // returns a byte array
            if (line == null)
                break;
            run(line);
            hadError = false; // If there has been an error, do not exit the prompt, only show error
        }
    }

    /**
     * Given a String, call a function to turn that into discernible tokens.
     * @param source Code to execute
     */
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens(); //breaks the source into a list of Token Objects

        // For now, just print the tokens, one on each line
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    /**
     * Indicate there has been an error on a given line.
     * @param line where did the error occur
     * @param message what to display to the user when this error is detected
     */
    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,
            String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}
