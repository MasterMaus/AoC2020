package aoc2020.day4;

import java.util.HashMap;

public class Passport {

    String ppFields[] = {"byr","iyr","eyr","hgt","hcl","ecl","pid"};

    HashMap<String, String> passportFields = new HashMap<>();
    public Passport (String data) {
        String[] datafields = data.split(" ");
        for(String d : datafields) {
            String[] temp = d.split(":");
            //System.out.println(d);
            //System.out.println(temp.length);
            passportFields.put(temp[0], temp[1]);
        }
    }

    Boolean isValidV1() {
        if(!passportFields.containsKey((ppFields[0]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[1]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[2]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[3]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[4]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[5]))) {
            return false;
        }
        if(!passportFields.containsKey((ppFields[6]))) {
            return false;
        }
        return true;
    }

    Boolean isValidV2() {
        if(passportFields.containsKey(ppFields[0])) {
            String byr = passportFields.get(ppFields[0]);
            int year = Integer.parseInt(byr);
            if(year<1920 || year > 2002) {

                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[1])) {
            String iyr = passportFields.get(ppFields[1]);
            int year = Integer.parseInt(iyr);
            if(year<2010 || year > 2020) {

                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[2])) {
            String eyr = passportFields.get(ppFields[2]);
            int year = Integer.parseInt(eyr);
            if(year<2020 || year > 2030) {

                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[3])) {
            String hgt = passportFields.get(ppFields[3]);
            if (hgt.contains("cm")) {
                hgt = hgt.replace("cm", "");
                int height = Integer.parseInt(hgt);
                if(height<150 || height>193) {
                    return false;
                }
            } else if (hgt.contains("in")) {
                hgt = hgt.replace("in", "");
                int height = Integer.parseInt(hgt);
                if(height<59 || height>76) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[4])) {
            String hcl = passportFields.get(ppFields[4]);
            if (!hcl.matches("#[0-9, a-f]{6}")) {
                //System.out.println("false because of hcl: " + hcl);
                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[5])) {
            String eyc = passportFields.get(ppFields[5]);
            if (!(eyc.equals("amb") || eyc.equals("blu") || eyc.equals("brn") || eyc.equals("gry") || eyc.equals("grn") || eyc.equals("hzl") || eyc.equals("oth"))) {
                //System.out.println("false because of eye color: " + eyc);
                return false;
            }
        } else {
            return false;
        }
        if(passportFields.containsKey(ppFields[6])) {
            String pid = passportFields.get(ppFields[6]);
            if (!pid.matches("[0-9]{9}")) {
                System.out.println("false because of pid: " + pid);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
