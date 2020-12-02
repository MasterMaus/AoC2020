package aoc2020;

public class Password {

    String password;
    char condition[] = new char[3]; // array with conditions for valid password. {min, max, character}

    public Password(String password, char condition[]) {
        this.password = password;
        this.condition = condition;
    }

}
