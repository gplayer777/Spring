package com.example.Lingua;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class LinguController {

    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;


    private EntryRepository entryRepository;
    private FileService fileService;
    private Scanner scanner;
    private ConsoleOutputWriter consoleOutputWriter;

    @Autowired
    LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsoleOutputWriter consoleOutputWriter) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    void mainLoop(){
        consoleOutputWriter.println("Welcome to the language app!");
        int option = UNDEFINED;
        while(option!=CLOSE_APP){
            printMenu();
            option = chooseOption();
            executeOption(option);
        }

    }

    private void executeOption(int option){
        switch (option){
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                consoleOutputWriter.println("try again");
        }
    }


        private void test() {

        if (entryRepository.isEmpty()) {
            consoleOutputWriter.println("the database needs to contain at least one word");
            return;
        }

        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            consoleOutputWriter.println(String.format("translate :\"%s\"", entry.getOriginal()));
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                consoleOutputWriter.println("The answer is correct");
                score++;
            } else {
                consoleOutputWriter.println("Incorrect answer - " + entry.getTranslation());
            }

            consoleOutputWriter.println(String.format("your score: %d/%d\n", score, testSize));

        }

        }

        private void addEntry(){
            System.out.println("Enter a word");
            String original = scanner.nextLine();
            consoleOutputWriter.println("Enter a german translation");
            String translation = scanner.nextLine();
            Entry entry = new Entry(original, translation);
            entryRepository.add(entry);
        }

        private void close(){

            try{
                fileService.saveEntries(entryRepository.getAll());
                consoleOutputWriter.println("Changes have been saved");

            } catch (IOException e) {
                e.printStackTrace();
                consoleOutputWriter.println("Changes could not be saved");

            }
            consoleOutputWriter.println("closing");
        }



        private void printMenu(){

            consoleOutputWriter.println("Choose option:");
            consoleOutputWriter.println("0 - Add a word");
            consoleOutputWriter.println("1 - Test");
            consoleOutputWriter.println("2 - End");
        }

        private int chooseOption(){

            int option;

            try {
                option = scanner.nextInt();
            } catch(InputMismatchException e) {
                option = UNDEFINED;
            } finally {
                scanner.nextLine();
            }
            if(option>UNDEFINED&& option <=CLOSE_APP)
                return option;
            else
                return UNDEFINED;





        }











}
