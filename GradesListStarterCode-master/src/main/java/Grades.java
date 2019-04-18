import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grades {
    private List<Grade> grades;

    public Grades() {
        this.grades = new ArrayList<>();
    }

    public void readGrades(String fileName) {
        File file = new File(fileName);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String name = reader.next().replace(",", "");
                double grade = reader.nextDouble();
                Grade person = new Grade(name, grade);
                grades.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double calcAverage() {
        double total = 0.0;
        for ( int i = 0; i < grades.size(); i++) {
            total += grades.get(i).getGrade();           //Check this out later may not be the right style for list
        }
        total = total / grades.size();
        if (grades.size() == 0) {
            return 0.0;
        }
        return total;
    }

    public double dropLowest() {
        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < grades.size(); i++) {
            if (min > grades.get(i).getGrade()) {
                min = grades.get(i).getGrade();
                index = i;

            }
        }
        grades.remove(index);
        return min;
    }

    public void addGrade(double grade) {
        grades.add(new Grade("", grade));
    }

    public boolean removeAllGrades(double grade) {
        for (int i = 0; i < grades.size(); i++) {
            if(grades.get(i).getGrade() == grade){
                grades.remove(i);
                return true;
            }
        }
        return false;
    }

    public void printSortedGrades() {
        Collections.sort(grades);
        System.out.println(grades);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < grades.size() - 1; i++) {
            sb.append(grades.get(i));
            sb.append(", ");
        }
        sb.append(grades.get(grades.size() - 1));
        sb.append("]");
        return sb.toString();
//        return grades.toString();
    }

    public void printGradeBreakdown() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int f = 0;
        for (int i = 0; i < grades.size(); i++) {
            if (90 <= grades.get(i).getGrade()) {
                a++;
            } else if (80 <= grades.get(i).getGrade()) {
                b++;
            } else if (70 <= grades.get(i).getGrade()) {
                c++;
            } else if (40 <= grades.get(i).getGrade()) {
                d++;
            }
        }
        f = grades.size() + 1 - (a + b + c + d);
        System.out.println("A: " + a + ", B: " + b + ", C: " + c + "D: " + d + ", F: " + f);
    }

    public String getStudentWithHighestGrade() {
        List<Grade> compareable = new ArrayList<>(grades);
        Collections.sort(compareable);
        return compareable.get(compareable.size() - 1).getStudent();
    }
}

