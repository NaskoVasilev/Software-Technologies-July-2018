import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class AverageGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split(" ");

            String name = tokens[0];
            Student student = new Student(name);

            for (int j = 1; j < tokens.length; j++) {
                student.addGrade(Double.parseDouble(tokens[j]));
            }

            students.add(student);
        }

        students.stream().filter(s -> s.averageGrades() >= 5.00).sorted((s1, s2) ->
        {
            int value = s1.getName().compareTo(s2.getName());
            if (value == 0) {
                return s2.averageGrades().compareTo(s1.averageGrades());
            }
            return value;
        })
                .forEach(s -> System.out.println(String.format("%s -> %.2f", s.getName(), s.averageGrades())));
    }
}

class Student {
    private String name;
    private ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGrade(double grade) {
        this.grades.add(grade);
    }

    public Double averageGrades() {
        double sum = 0;
        int length = 0;
        for (Double grade : this.grades) {
            sum += grade;
            length++;
        }
        return sum / length;
    }
}



