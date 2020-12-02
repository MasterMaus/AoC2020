package aoc2020;

public class Password {

    String password;
    int min, max; //minimal and maximal occurence of letter in password to be valid
    char letter;


    public Password(String password, int min, int max, char letter) {
        this.password = password;
        this.min = min;
        this.max = max;
        this.letter = letter;
    }

    public boolean isValidPassword() {
        int occurance = countCharacter(letter);
        if (occurance >= min && occurance <= max) {
            return true;
        }
        return false;
    }

    public int countCharacter(char letter) {
        return (int) password.chars().filter(ch -> ch == letter).count();
    }

    public boolean isValidPasswordV2() {
       boolean letterAtMin = password.charAt(min -1) == letter;
       boolean letterAtMax = password.charAt(max-1) == letter;
       if (letterAtMin && !letterAtMax || !letterAtMin && letterAtMax) {
           return true;
       }
       return false;
    }

}
