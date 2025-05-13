import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Kifejezes> kifejezesek = new ArrayList<>();

    public static void main(String[] args) {
        feltolt();
        feladat2();
        feladat3();
        System.out.println(feladat4());
        feladat5();
        feladat7();
        feladat8();
    }

    private static void feltolt() {
        File f = new File("kifejezesek.txt");
        try {
            Scanner be = new Scanner(f);
            while (be.hasNextLine()) {
                String sor = be.nextLine();
                String[] adatok = sor.split(" ");
                kifejezesek.add(new Kifejezes(
                        Integer.parseInt(adatok[0]),
                        adatok[1],
                        Integer.parseInt(adatok[2])
                ));
            }
        } catch (Exception e) {
            System.out.println("Hiba a fájl beolvasása során: " + e.getMessage());
        }
    }

    private static void feladat2() {
        System.out.println("-- 2. feladat --");
        System.out.println("Kifejezések száma: " + kifejezesek.size());
    }

    private static void feladat3() {
        System.out.println("-- 3. feladat --");
        int modCount = 0;
        for (Kifejezes k : kifejezesek) {
            if (k.operator.equals("mod")) {
                modCount++;
            }
        }
        System.out.println("Kifejezések maradékos osztással: " + modCount);
    }

    private static String feladat4() {
        System.out.println("-- 4. feladat --");
        for (Kifejezes k : kifejezesek) {
            if (k.operandus1 % 10 == 0 && k.operandus2 % 10 == 0) {
                return "Van ilyen kifejezés!";
            }
        }
        return "Nincs ilyen kifejezés!";
    }

    private static void feladat5() {
        System.out.println("-- 5. feladat --");
        int mod = 0, div = 0, plus = 0, minus = 0, multiply = 0, divide = 0;
        for (Kifejezes k : kifejezesek) {
            switch (k.operator) {
                case "mod" -> mod++;
                case "div" -> div++;
                case "+" -> plus++;
                case "-" -> minus++;
                case "*" -> multiply++;
                case "/" -> divide++;
            }
        }
        System.out.println("Statisztika:");
        System.out.println("mod -> " + mod + " db");
        System.out.println("div -> " + div + " db");
        System.out.println("+ -> " + plus + " db");
        System.out.println("- -> " + minus + " db");
        System.out.println("* -> " + multiply + " db");
        System.out.println("/ -> " + divide + " db");
    }

    private static void feladat7() {
        System.out.println("-- 7. feladat --");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Kérek egy kifejezést (pl.: 1 + 1): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("vége")) break;

            String[] adatok = input.split(" ");
            if (adatok.length == 3) {
                try {
                    int operandus1 = Integer.parseInt(adatok[0]);
                    String operator = adatok[1];
                    int operandus2 = Integer.parseInt(adatok[2]);
                    Kifejezes k = new Kifejezes(operandus1, operator, operandus2);
                    System.out.println(input + " = " + k.ertek());
                } catch (Exception e) {
                    System.out.println("Hibás formátum!");
                }
            } else {
                System.out.println("Hibás formátum!");
            }
        }
    }

    private static void feladat8() {
        System.out.println("-- 8. feladat --");
        try (java.io.PrintWriter pw = new java.io.PrintWriter("eredmenyek.txt")) {
            for (Kifejezes k : kifejezesek) {
                pw.println(k + " = " + k.ertek());
            }
            System.out.println("eredmenyek.txt elkészült.");
        } catch (Exception e) {
            System.out.println("Hiba az eredmények fájlba írása során: " + e.getMessage());
        }
    }
}

class Kifejezes {
    int operandus1;
    String operator;
    int operandus2;

    public Kifejezes(int operandus1, String operator, int operandus2) {
        this.operandus1 = operandus1;
        this.operator = operator;
        this.operandus2 = operandus2;
    }

    public String ertek() {
        try {
            return switch (operator) {
                case "+" -> String.valueOf(operandus1 + operandus2);
                case "-" -> String.valueOf(operandus1 - operandus2);
                case "*" -> String.valueOf(operandus1 * operandus2);
                case "/" -> operandus2 != 0 ? String.valueOf((double) operandus1 / operandus2) : "Egyéb hiba!";
                case "mod" -> operandus2 != 0 ? String.valueOf(operandus1 % operandus2) : "Egyéb hiba!";
                case "div" -> operandus2 != 0 ? String.valueOf(operandus1 / operandus2) : "Egyéb hiba!";
                default -> "Hibás operátor!";
            };
        } catch (Exception e) {
            return "Egyéb hiba!";
        }
    }

    @Override
    public String toString() {
        return operandus1 + " " + operator + " " + operandus2;
    }
}