import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            nums.add(Integer.parseInt(input[i]));
        }

        String[] bombNumbers = scanner.nextLine().split(" ");
        int bombNumber = Integer.parseInt(bombNumbers[0]);
        int power = Integer.parseInt(bombNumbers[1]);

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == bombNumber) {
                int length = Math.min(i, power);
                int index = i - length;
                int endIndex = i;
                for (int j = i - length; j < endIndex; j++) {
                    nums.remove(index);
                    i--;
                }
                endIndex = Math.min(power, nums.size() - i - 1);
                for (int j = 0; j <= endIndex; j++) {
                    nums.remove(i);
                }
                i = 0;
            }
        }

        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        System.out.println(sum);

    }
}
