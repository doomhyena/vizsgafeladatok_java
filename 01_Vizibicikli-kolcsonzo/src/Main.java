import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Kolcsonzes> kolcsonzesek = new ArrayList<>();

        List<String> sorok = Files.readAllLines(Paths.get("kolcsonzesek.txt"));
        for (int i = 1; i < sorok.size(); i++) {
            String[] m = sorok.get(i).split(";");
            kolcsonzesek.add(new Kolcsonzes(
                    m[0].trim(), m[1].trim(),
                    Integer.parseInt(m[2].trim()), Integer.parseInt(m[3].trim()),
                    Integer.parseInt(m[4].trim()), Integer.parseInt(m[5].trim())
            ));
        }

        System.out.println("5. feladat: Kölcsönzések száma: " + kolcsonzesek.size());

        Scanner sc = new Scanner(System.in);
        System.out.print("6. feladat: Kérek egy nevet: ");
        String keresettNev = sc.nextLine();
        boolean talalt = false;
        for (Kolcsonzes k : kolcsonzesek) {
            if (k.nev.equalsIgnoreCase(keresettNev)) {
                System.out.println("\t" + k.toString());
                talalt = true;
            }
        }
        if (!talalt) {
            System.out.println("\tNem volt ilyen nevű kölcsönző!");
        }

        System.out.print("7. feladat: Kérek egy időpontot (óra:perc): ");
        String[] idopont = sc.nextLine().split(":");
        int ora = Integer.parseInt(idopont[0]);
        int perc = Integer.parseInt(idopont[1]);
        int keresettIdo = ora * 60 + perc;
        for (Kolcsonzes k : kolcsonzesek) {
            if (k.elvitelPercekben() <= keresettIdo && k.visszahozatalPercekben() > keresettIdo) {
                System.out.printf("\t%s: %s-ig volt vízen (%s)\n", k.jarmu, k.toString(), k.nev);
            }
        }

        int bevetel = 0;
        for (Kolcsonzes k : kolcsonzesek) {
            int idotartam = k.kolcsonzesHossz();
            int felorak = (idotartam + 29) / 30;
            bevetel += felorak * 2400;
        }
        System.out.println("8. feladat: Napi bevétel: " + bevetel + " Ft");

        PrintWriter pw = new PrintWriter("F.txt", "UTF-8");
        for (Kolcsonzes k : kolcsonzesek) {
            if (k.jarmu.equals("F")) {
                pw.printf("%s\t%02d:%02d-%02d:%02d\n", k.nev, k.elvitelOra, k.elvitelPerc, k.visszaOra, k.visszaPerc);
            }
        }
        pw.close();

        System.out.println("10. feladat: Statisztika");
        Map<String, Integer> stat = new TreeMap<>();
        for (Kolcsonzes k : kolcsonzesek) {
            stat.put(k.jarmu, stat.getOrDefault(k.jarmu, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue() + " db");
        }
    }
}