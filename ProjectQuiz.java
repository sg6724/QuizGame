import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class ProjectQuiz{
    public static void main(String args[]){
        Lifelines obj = new Lifelines();
        obj.Ask();
    }
}

class Questions {
    static Scanner sc = new Scanner(System.in);
    ArrayList<String> ansList = new ArrayList<String>();
    String[] Question = new String[10];
    int reward = 1000;

    //Method overidding of life method
    void life(int val) {
        System.out.println("Move to child class life method");
    }

    void Ask() {
        Random ran = new Random();
        String fileName = "questions.txt";
        ArrayList<String> questions = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(line);  // Add each question to the list
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        System.out.println("ENTER 1 TO START THE QUIZ!!");
        int val = sc.nextInt();
        if (val == 1) {
            for (int i = 0; i < questions.size(); i++) {
                int nxt = ran.nextInt(5) + 1;
                switch (nxt) {
                    case 1:
                        System.out.println(questions.get(0));
                        break;
                    case 2:
                        System.out.println(questions.get(1));
                        break;
                    case 3:
                        System.out.println(questions.get(2));
                        break;
                    case 4:
                        System.out.println(questions.get(3));
                        break;
                    case 5:
                        System.out.println(questions.get(4));
                        break;
                    default:
                        System.out.println("No question");
                        break;
                }
                if (!Answer(nxt)) {
                    System.out.println("Incorrect answer , Better luck next time...");
                    System.out.format("YOUR FINAL SCORE: %d\n", reward);
                    return; // End the quiz after incorrect answer
                }
            }
        }
    }

    boolean Answer(int num) {
        switch (num) {
            case 1:
                ansList = new ArrayList<String>(Arrays.asList("1. Paris", "2. Rome", "3. Berlin", "4. Madrid"));
                break;
            case 2:
                ansList = new ArrayList<String>(Arrays.asList("1. 1955", "2. 1950", "3. 1960", "4. 1945"));
                break;
            case 3:
                ansList = new ArrayList<String>(Arrays.asList("1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. James Austin"));
                break;
            case 4:
                ansList = new ArrayList<String>(Arrays.asList("1. Mars", "2. Earth", "3. Jupiter", "4. Saturn"));
                break;
            case 5:
                ansList = new ArrayList<String>(Arrays.asList("1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"));
                break;
        }

        for (String option : ansList) {
            System.out.println(option);
        }

        System.out.println("Select the correct option");
        System.out.println("Select 'l' for lifeline:");
        char option = sc.next().charAt(0);

        if (option == 'l') {
            life(num);
            System.out.println("Select your final answer:");
            option = sc.next().charAt(0);
            // Ask for the final answer after lifeline
            if(checkAnswer(num,option)==true){
            reward = reward+1000;
        }
        else{
            reward = reward-1000;
        }
        }

        // return checkAnswer(num, option);
        else{
        if(checkAnswer(num,option)==true){
            reward = reward+1000;
        }
        else{
            reward = reward-1000;
        }
      }
        return checkAnswer(num,option);
    }

    boolean checkAnswer(int questionNum, char answer) {
        switch (questionNum) {
            case 1: return answer == '1';

            case 2: return answer == '4';
            case 3: return answer == '2';
            case 4: return answer == '3';
            case 5: return answer == '4';
            default: return false;
        }
    }
}

class Lifelines extends Questions {
    int count= 3;
    void life(int life50) {
        count--;
        if(count==0){
            System.out.println("No more lifelines!");
            return;
            
        }
        System.out.println("Which lifeline to use? Enter --> 'f' -> 50-50  AND  'p' -> Public vote");
        char c = sc.next().charAt(0);

        if (c == 'f') {
            // 50-50 logic
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
            reward=reward+1000;
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
