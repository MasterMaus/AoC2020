package aoc2020.day4;

import java.util.HashMap;

public class Passport {

    private String ppFields[] = {"byr","iyr","eyr","hgt","hcl","ecl","pid"};
    private HashMap<String, String> passportFields = new HashMap<>();

    public Passport (String data) {
        if(!data.isEmpty()) {
            String[] datafields = data.split(" ");
            for (String d : datafields) {
                String[] temp = d.split(":");
                passportFields.put(temp[0], temp[1]);
            }
        }
    }

    Boolean isValidV1() {
        for(String i : ppFields) {
            if(!passportFields.containsKey(i)) {
                return false;
            }
        }
        return true;
    }

    Boolean isValidV2() {
        if (isValidV1()) {
            String byr = passportFields.get(ppFields[0]);
            int birthyear = Integer.parseInt(byr);
            if (birthyear < 1920 || birthyear > 2002) {
                return false;
            }
            String iyr = passportFields.get(ppFields[1]);
            int issueyear = Integer.parseInt(iyr);
            if (issueyear < 2010 || issueyear > 2020) {
                return false;
            }

            String eyr = passportFields.get(ppFields[2]);
            int expirationyear = Integer.parseInt(eyr);
            if (expirationyear < 2020 || expirationyear > 2030) {
                return false;
            }
            String hgt = passportFields.get(ppFields[3]);
            if (hgt.contains("cm")) {
                hgt = hgt.replace("cm", "");
                int height = Integer.parseInt(hgt);
                if (height < 150 || height > 193) {
                    return false;
                }
            } else if (hgt.contains("in")) {
                hgt = hgt.replace("in", "");
                int height = Integer.parseInt(hgt);
                if (height < 59 || height > 76) {
                    return false;
                }
            } else {
                return false;
            }
            String hcl = passportFields.get(ppFields[4]);
            if (!hcl.matches("#[0-9, a-f]{6}")) {
                return false;
            }
            String eyc = passportFields.get(ppFields[5]);
            if (!(eyc.equals("amb") || eyc.equals("blu") || eyc.equals("brn") || eyc.equals("gry") || eyc.equals("grn") || eyc.equals("hzl") || eyc.equals("oth"))) {
                return false;
            }
            String pid = passportFields.get(ppFields[6]);
            if (!pid.matches("[0-9]{9}")) {
                return false;
            }
            return true;
        }
        return false;
    }
}
