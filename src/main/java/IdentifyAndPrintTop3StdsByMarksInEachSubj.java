
import java.util.*;
import java.util.stream.Collectors;

public class IdentifyAndPrintTop3StdsByMarksInEachSubj
{

   public static void main(String[] args)
   {
   
      final List<Student> studentList =
         List
            .of
            (
               new Student(1001, "sn1", List.of(new Subject(1001, "Maths", 87.0), new Subject(1001, "English", 72.0))),
               new Student(1002, "sn2", List.of(new Subject(1002, "Maths", 77.0), new Subject(1002, "English", 83.0))),
               new Student(1003, "sn3", List.of(new Subject(1003, "Maths", 91.0), new Subject(1003, "English", 68.0))),
               new Student(1004, "sn4", List.of(new Subject(1004, "Maths", 76.0), new Subject(1004, "English", 51.0))),
               new Student(1005, "sn5", List.of(new Subject(1005, "Maths", 51.0), new Subject(1005, "English", 65.0))),
               new Student(1006, "sn6", List.of(new Subject(1006, "Maths", 57.0), new Subject(1006, "English", 55.0))),
               new Student(1007, "sn7", List.of(new Subject(1007, "Maths", 98.0), new Subject(1007, "English", 92.0))),
               new Student(1008, "sn8", List.of(new Subject(1008, "Maths", 45.0), new Subject(1008, "English", 48.0)))
            )
            ;
      
      final List<String> actualSubjects =
         studentList
            .stream()
            .map(Student::subjectList)
            .flatMap(List::stream)
            .map(Subject::subjectName)
            .distinct()
            .toList()
            ;
   
      System.out.println(actualSubjects);
   
      final Map<String, List<Student>> top3StudentsPerSubject =
         actualSubjects
            .stream()
            .map
            (
               eachSubject -> 
                  Map
                     .entry
                     (
                        eachSubject, 
                        studentList
                           .stream()
                           .filter
                           (
                              eachStudent -> 
                                 eachStudent
                                    .subjectList()
                                    .stream()
                                    .map(Subject::subjectName)
                                    .anyMatch(eachSubject::equals)
                           )
                           .sorted
                           (
                              Comparator
                                 .<Student>comparingDouble
                                 (
                                    singleStudent ->
                                       singleStudent
                                          .subjectList()
                                          .stream()
                                          .filter(eachSubject2 -> eachSubject2.subjectName().equals(eachSubject))
                                          .mapToDouble(Subject::marks)
                                          .findFirst()
                                          .orElseThrow()
                                 )
                                 .reversed()
                           )
                           .limit(3)
                           .toList()
                     )
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
            ;
   
      top3StudentsPerSubject
         .forEach
         (
            (subject, listOfStudents) ->
            {
            
               System.out.println(subject);
            
               listOfStudents
                  .stream()
                  .map(Student::name)
                  .map("\t"::concat)
                  .forEach(System.out::println)
                  ;
            
            }
         )
         ;
   
   }

}

record Student (int studentId, String name, List<Subject> subjectList)
{

   Student
   {
   
      if (studentId <= 0)
      {
      
         throw new IllegalArgumentException("(" + studentId + ") is a bad value for a studentId");
      
      }
   
      Objects.requireNonNull(name);
   
      if (name.isBlank())
      {
      
         throw new IllegalArgumentException("name cannot be blank");
      
      }
   
      Objects.requireNonNull(subjectList);
   
      if (subjectList.isEmpty())
      {
      
         throw new IllegalArgumentException("subjectList cannot be empty");
      
      }
   
   }

}

record Subject (int studentId, String subjectName, double marks)
{

   Subject
   {
   
      if (studentId <= 0)
      {
      
         throw new IllegalArgumentException("(" + studentId + ") is a bad value for a studentId");
      
      }
   
      Objects.requireNonNull(subjectName);
   
      if (subjectName.isBlank())
      {
      
         throw new IllegalArgumentException("subjectName cannot be blank");
      
      }
   
      if (marks < 0)
      {
      
         throw new IllegalArgumentException("marks cannot be < 0");
      
      }
   
   }

}
