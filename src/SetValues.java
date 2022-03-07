public class SetValues {
    private static int M=0;
    private static int N;
    private static boolean x = true;
    public static Kasa[] kasa;
    public static int iloscKlientow = 0;

    public static void setIloscKlientow(){
        iloscKlientow++;
    }
    public static void setIloscKlientowMin(){
        iloscKlientow--;
    }
    public static int getIloscKlientow(){
        return iloscKlientow;
    }
    public static void setKasa(Kasa[] kasa1){
        kasa = kasa1;
    }
    public static Kasa[] getKasa(){
        return kasa;
    }
    public static void setM(int M1){
        M = M1;
    }
    public static void setN(int N1){
        N = N1;
    }
    public static int getM(){
       return M;
    }
    public static int getN(){
        return N;
    }
    public static void xchange(){
        x=!x;
    }
    public static boolean xch(){
        return x;
    }
}
