import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ProjectQuiz {
    public static void main(String args[]) {
        Lifelines obj = new Lifelines();
        obj.Ask();
    }
}

class Questions {
    static Scanner sc = new Scanner(System.in);
    ArrayList<String> ansList = new ArrayList<>();
    int reward = 1000;

    // Overriding method for lifelines
    void life(int val) {
        System.out.println("Move to child class life method");
    }

    void Ask() {
        Random ran = new Random();
        String questionFile = "questions.txt";
        String answerFile = "answers.txt";
        ArrayList<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(questionFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(line);  // Add each question to the list
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the questions file.");
            e.printStackTrace();
        }

        System.out.println("ENTER 1 TO START THE QUIZ!!");
        int val = sc.nextInt();
        if (val == 1) {
            for (int i = 0; i < questions.size(); i++) {
                int nxt = ran.nextInt(questions.size()) + 1;
                System.out.println(questions.get(nxt - 1));

                if (!Answer(nxt, answerFile)) {
                    System.out.println("Incorrect answer , Better luck next time...");
                    System.out.format("YOUR FINAL SCORE: %d\n", reward);
                    return; // End the quiz after incorrect answer
                }
            }
        }
    }

    boolean Answer(int questionNum, String answerFile) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(answerFile))) {
            int currentLine = 0;
            while ((line = br.readLine()) != null) {
                currentLine++;
                if (currentLine == questionNum) {
                    String[] parts = line.split(";");
                    ansList = new ArrayList<>(Arrays.asList(parts[0].split(","))); // Extract options
                    char correctAnswer = parts[1].trim().charAt(0); // Correct answer
                    for (String option : ansList) {
                        System.out.println(option.trim());
                    }

                    System.out.println("Select the correct option");
                    System.out.println("Select 'l' for lifeline:");
                    char option = sc.next().charAt(0);

                    if (option == 'l') {
                        life(questionNum);
                        System.out.println("Select your final answer:");
                        option = sc.next().charAt(0);
                    }

                    // Check if the selected answer is correct
                    if (checkAnswer(correctAnswer, option)) {
                        reward += 1000;
                    } else {
                        reward -= 1000;
                    }
                    return checkAnswer(correctAnswer, option);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the answers file.");
            e.printStackTrace();
        }
        return false;
    }

    boolean checkAnswer(char correctAnswer, char selectedAnswer) {
        return correctAnswer == selectedAnswer;
    }
}

class Lifelines extends Questions {
    int count = 3;

    void life(int life50) {
        count--;
        if (count == 0) {
            System.out.println("No more lifelines!");
            return;
        }
        System.out.println("Which lifeline to use? Enter --> 'f' -> 50-50  AND  'p' -> Public vote");
        char c = sc.next().charAt(0);

        if (c == 'f') {
            switch (life50) {
                case 1:
                    ansList.clear();
                    ansList.add("1. Paris");
                    ansList.add("4. Madrid");
                    break;
                case 2:
                    ansList.clear();
                    ansList.add("2. 1950");
                    ansList.add("4. 1945");
                    break;
                case 3:
                    ansList.clear();
                    ansList.add("2. William Shakespeare");
                    ansList.add("3. Mark Twain");
                    break;
                case 4:
                    ansList.clear();
                    ansList.add("3. Jupiter");
                    ansList.add("4. Saturn");
                    break;
                case 5:
                    ansList.clear();
                    ansList.add("2. Indian Ocean");
                    ansList.add("4. Pacific Ocean");
                    break;
            }
            for (String option : ansList) {
                System.out.println(option);
            }
        } else if (c == 'p') {
            reward += 1000;
            System.out.println("Public vote suggests the correct answer is: " + getCorrectAnswer(life50));
        }
    }

    String getCorrectAnswer(int life50) {
        switch (life50) {
            case 1: return "Paris";
            case 2: return "1945";
            case 3: return "William Shakespeare";
            case 4: return "Jupiter";
            case 5: return "Pacific Ocean";
            default: return "Unknown";
        }
    }
}
