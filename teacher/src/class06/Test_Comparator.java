package class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Test_Comparator {
    public static class Student{
        public int age;
        public int id;
        public String name;

        public Student(int age,int id,String name){
            this.age = age;
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    // 通过实现并重写比较器来实现
    public static class IdShengAgeJiangOrder implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id-o2.id) : (o1.age-o2.age);
        }
    }



    public static void main(String[] args) {
        Student student1 = new Student(15,5,"A");
        Student student2 = new Student(16,4,"B");
        Student student3 = new Student(16,3,"C");
        Student student4 = new Student(15,3,"D");
        Student student5 = new Student(15,1,"E");

        Student[] students = new Student[]{student1, student2, student3, student4, student5 };
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("------");
        Arrays.sort(students,new IdShengAgeJiangOrder());
        for (Student student : students) {
            System.out.println(student);
        }

        TreeMap<Student,String> treeMap = new TreeMap<>((a,b) -> (a.id != b.id ? (a.id-b.id) : (a.age - b.age)));
    }

}
