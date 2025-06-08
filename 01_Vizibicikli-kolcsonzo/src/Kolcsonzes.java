public class Kolcsonzes {
    String nev;
    String jarmu;
    int elvitelOra;
    int elvitelPerc;
    int visszaOra;
    int visszaPerc;

    public Kolcsonzes(String nev, String jarmu, int elvitelOra, int elvitelPerc, int visszaOra, int visszaPerc) {
        this.nev = nev;
        this.jarmu = jarmu.toUpperCase();
        this.elvitelOra = elvitelOra;
        this.elvitelPerc = elvitelPerc;
        this.visszaOra = visszaOra;
        this.visszaPerc = visszaPerc;
    }

    public int elvitelPercekben() {
        return elvitelOra * 60 + elvitelPerc;
    }

    public int visszahozatalPercekben() {
        return visszaOra * 60 + visszaPerc;
    }

    public int kolcsonzesHossz() {
        return visszahozatalPercekben() - elvitelPercekben();
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d - %02d:%02d", elvitelOra, elvitelPerc, visszaOra, visszaPerc);
    }
}
