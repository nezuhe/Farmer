package pl.piotrchowaniec;

import java.util.Scanner;

public class Security {
    private Scanner scanner;
    private String choiceString;

    public Security() {
        scanner = new Scanner(System.in);
    }

    public boolean isBarnNameAvaliable(Farmer farmer, String newBarnName) {
        if (!farmer.getBarnList().isEmpty() && newBarnName.equalsIgnoreCase(farmer.getBarnList().stream()
                .map(s -> s.getName())
                .findFirst()
                .get())) {
            return false;
        }
        return true;
    }

    public void choiceNumberValidation() {
        boolean isChoiceCorrect;
        do {
            isChoiceCorrect = true;
            choiceString = scanner.nextLine();
            if (!isANumber(choiceString)) {
                isChoiceCorrect = false;
            }
        } while (!isChoiceCorrect);
    }

    public void choiceNumberValidation(int range) {
        boolean isChoiceCorrect;
        do {
            isChoiceCorrect = true;
            choiceString = scanner.nextLine();
            if (!isANumber(choiceString)) {
                isChoiceCorrect = false;
            } else if (!isInListRange(range)) {
                isChoiceCorrect = false;
            }
        } while (!isChoiceCorrect);
    }

    public void switchNumberValidation(int range) {
        boolean isChoiceCorrect;
        do {
            isChoiceCorrect = true;
            choiceString = scanner.nextLine();
            if (!isANumber(choiceString)) {
                isChoiceCorrect = false;
            } else if (!isInSwitchRange(range)) {
                isChoiceCorrect = false;
            }
        } while (!isChoiceCorrect);
    }

    public boolean isANumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            printNotANumberText();
            return false;
        }
        return true;
    }

    private boolean isInListRange(int range) {
        int check = Integer.parseInt(choiceString);
        if (!(check > 0 && check <= range)) {
            printWrongChoiceText();
            return false;
        }
//        System.out.println("range = " + choiceString);
        return true;
    }

    private boolean isInSwitchRange(int range) {
        int check = Integer.parseInt(choiceString);
        if (!(check >= 0 && check <= range)) {
            printWrongChoiceText();
            return false;
        }
        return true;
    }

    public boolean yesNoChoiceValidation() {
        boolean isChoiceCorrect = true, result = false;
        do {
            String choice = scanner.next();
            scanner.nextLine();
            if (!(choice.equalsIgnoreCase("t") || choice.equalsIgnoreCase("n"))) {
                printWrongChoiceText();
                isChoiceCorrect = false;
            } else if (choice.equalsIgnoreCase("t")) {
                isChoiceCorrect = true;
                result = true;
            } else if (choice.equalsIgnoreCase("n")){
                isChoiceCorrect = true;
                result = false;
            }
        } while (!isChoiceCorrect);
        return result;
    }

    private void printNotANumberText() {
        System.out.print("Musisz podać liczbę. Spróbuj jeszcze raz: ");
    }

    private void printWrongChoiceText() {
        System.out.print("Wybór niepoprawny. Spróbuj jeszcze raz: ");
    }

    public String getChoiceString() {
        return choiceString;
    }

    public void setChoiceString(String choiceString) {
        this.choiceString = choiceString;
    }
}