import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int bestIndex = -1;
        int bestLength = 0;
        int currentLength = 1;


        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                currentLength++;
                if (bestLength < currentLength) {
                    bestLength = currentLength;
                    bestIndex = i;
                }
            } else {
                currentLength = 1;
            }
        }

        for (int i = bestIndex - bestLength + 2; i <= bestIndex+1; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

