import java.util.Scanner;

public class SymetricNumbers {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n= Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <=n ; i++) {
            String numberAsString=i+"";
            if(isSymetric(numberAsString)){
                System.out.println(i+" ");
            }
        }
    }

    private static boolean isSymetric(String number){
        //String reversedNumber="";
        StringBuilder sb=new StringBuilder();
        for(int i=number.length()-1;i>=0;i--){
            sb.append(number.charAt(i));
        }

        if(number.equals(sb.toString())){
            return true;
        }
        return false;
    }
}
