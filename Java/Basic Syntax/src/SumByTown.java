import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SumByTown {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n=Integer.parseInt(scanner.nextLine());

        TreeMap<String,Double>towns=new TreeMap<>();
        for (int i = 0; i <n; i++) {
            String[] tokens=scanner.nextLine().split("\\|");
            String town=tokens[0].trim();
            double income=Double.parseDouble(tokens[1].trim());

            if(!towns.containsKey(town)){
                towns.put(town,income);
            }
            else{
                towns.put(town,towns.get(town)+income);
            }
        }

        for (String key : towns.keySet()) {
            System.out.println(key+" -> "+towns.get(key));
        }
    }
}
