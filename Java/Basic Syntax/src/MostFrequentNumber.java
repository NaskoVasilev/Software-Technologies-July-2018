import java.util.Arrays;
import java.util.Scanner;

public class MostFrequentNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int currentCount = 1;
        int bestCount=0;
        int bestNum=0;
        for (int i = 0; i < arr.length; i++) {
            currentCount=1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    currentCount++;
                }
            }

            if(currentCount>bestCount){
                bestCount=currentCount;
                bestNum=arr[i];
            }
        }

        System.out.println(bestNum);
    }
}
