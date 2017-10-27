package DAL.character.player;

import DAL.yaml.YamlParser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class QuizManager {
    private Scanner scanner;
    private List<Quiz> quizes;
    private Quiz currentQuiz;

    public QuizManager() {
        scanner = new Scanner(System.in);

        initalizeQuizes();
    }

    public String[] getUnoXMessage() {
        return new String[] {
                "Before leaving you make a quick stop at the local gas station Uno-X",
                "would you like to play a small quiz in order to win some gas for your ship?",
                "Enter Y/N"
        };
    }

    public boolean hasAcceptedOffer(char c) {
        return c == 'Y' || c == 'y';
    }

    public void pickRandomQuiz() {
        int index = (int) (Math.random() * quizes.size());
        currentQuiz = quizes.get(index);
    }

    public String[] getCurrentQuizMessage() {
        String[] strings = new String[1 + currentQuiz.getOptions().length];

        strings[0] = currentQuiz.getQuestion();
        for (int i = 0; i < currentQuiz.getOptions().length; i++) {
            strings[i + 1] = String.format("[%d] %s", i + 1, currentQuiz.getOptions()[i]);
        }

        return strings;
    }

    public int getOptionsSize() {
        return currentQuiz.getOptions().length;
    }

    public boolean isAnswerCorrect(int answer) {
        return currentQuiz.getAnswer() == answer - 1;
    }

    @SuppressWarnings("unchecked")
    private void initalizeQuizes() {
        YamlParser parser = new YamlParser(new File("./src/DAL/resource/quizdatabase.yaml"));

        Map<Integer, Map<String, Object>> database = null;

        try {
            database = parser.getYaml().load(new FileReader(parser.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(database != null) {
            quizes = new ArrayList<>(database.size());

            String question;
            List<String> opList;
            String[] options;
            Integer answer;

            for (Map<String, Object> map : database.values()) {
                question = (String) map.get("question");

                opList = (List<String>) map.get("options");
                options = opList.toArray(new String[opList.size()]);

                answer = (Integer) map.get("answer");

                quizes.add(new Quiz(question, options, answer));
            }
        }

    }

    /*
    Scanner input = new Scanner(System.in);
    String[] quiz1;
        System.out.println("Before leaving you make a quick stop at the local gas station Uno-X");
        System.out.println("would you like to play a small quiz in order to win some gas for your ship?");
        System.out.println("Enter Y/N");
    String answer = null;
    answer = input.nextLine();

        if (answer.equalsIgnoreCase("Y")) {

        quiz1 = new String[10];


        quiz1[0] = " Which one of these is not a primitive data type";
        quiz1[1] = " Java source code is stored in filed with what extension?";
        quiz1[2] = " The best enviroment for developing Java applications is?";
        quiz1[3] = " Which GUI toolkit comes included with Java?";
        quiz1[4] = " The current version of Java is?";
        quiz1[5] = " Java is a?";
        quiz1[6] = " The file extension for MyApp.Java after running javac MyApp.java is?";
        quiz1[7] = " In Java console output is achived by using?";
        quiz1[8] = " Java applications are executed in?";
        quiz1[9] = " Java can be used to write?";


        int i = new Random().nextInt(quiz1.length);
        System.out.println(quiz1[i]);
        if (quiz1[0].equals(i)) {
            System.out.println("A. integer");
            System.out.println("B. Char");
            System.out.println("C. String");
            System.out.println("choose A,B or C");
        }
        String uSelct1 = input.nextLine();

        switch (uSelct1.toUpperCase()) {
            case "A":
                uSelct1 = "Integer";
                System.out.println("Incorrect");
                System.out.println("unfortunatly you didnt win any gas this time better luck next time ");
                break;
            case "B":
                uSelct1 = "Char";
                System.out.println("Incorrect");
                System.out.println("unfortunatly you didnt win any gas this time better luck next time ");
                break;
            case "C":
                uSelct1 = "String";
                System.out.println("Correct");
                System.out.println("Congratulations you just won a free refill");
                break;

        }

    }
     */
}
