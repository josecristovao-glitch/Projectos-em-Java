public class BalanceTrimestral {

    public static void main(String[] args) {

        int[] PriTri = {15300, 23000, 11700};
        int[] AbMai = {21000, 14000};

        int total1 = PriTri[0] + PriTri[1] + PriTri[2];

        System.out.println("Gasto total 1º trimestre: "
                + total1 + " Kwanzas");

        double media1 = (total1 + AbMai[0] + AbMai[1]) / 5.0;

        System.out.println("Previsão Junho: "
                + media1 + " Kwanzas");

        System.out.println("A empresa EPDIGITAL gasta mensalmente "
                + media1 + " Kwanzas.");
    }
}