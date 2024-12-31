
import java.util.*;
import java.util.stream.Collectors;

public class IdentifyAndPrintTop3StdsByMarksInEachSubj_ATTEMPT_2
{

   record Student(int studentId, String name)
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
      
      }
   
   }

   record Subject(String subjectName)
   {
   
      Subject
      {
      
         Objects.requireNonNull(subjectName);
      
         if (subjectName.isBlank())
         {
         
            throw new IllegalArgumentException("subjectName cannot be blank");
         
         }
      
      }
   
   }

   record ClassParticipant(Subject subject, Student student)
   {
   
      ClassParticipant
      {
      
         Objects.requireNonNull(subject);
         Objects.requireNonNull(student);
      
      }
   
   }

   public static void main(String[] args)
   {
   
      final Student s1 = new Student(1001, "sn1");
      final Student s2 = new Student(1002, "sn2");
      final Student s3 = new Student(1003, "sn3");
      final Student s4 = new Student(1004, "sn4");
      final Student s5 = new Student(1005, "sn5");
      final Student s6 = new Student(1006, "sn6");
      final Student s7 = new Student(1007, "sn7");
      final Student s8 = new Student(1008, "sn8");
   
      final List<Student> students = List.of(s1, s2, s3, s4, s5, s6, s7, s8);
   
      final Subject subjectMaths = new Subject("Maths");
      final Subject subjectEnglish = new Subject("English");
   
      final List<Subject> subjects = List.of(subjectMaths, subjectEnglish);
   
      final Map<ClassParticipant, Double> finalGrades =
         Map
            .ofEntries
            (
               Map.entry(new ClassParticipant(subjectEnglish,  s1),     72.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s2),     83.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s3),     68.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s4),     51.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s5),     65.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s6),     55.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s7),     92.0),
               Map.entry(new ClassParticipant(subjectEnglish,  s8),     48.0),
               Map.entry(new ClassParticipant(subjectMaths,    s1),     87.0),
               Map.entry(new ClassParticipant(subjectMaths,    s2),     77.0),
               Map.entry(new ClassParticipant(subjectMaths,    s3),     91.0),
               Map.entry(new ClassParticipant(subjectMaths,    s4),     76.0),
               Map.entry(new ClassParticipant(subjectMaths,    s5),     51.0),
               Map.entry(new ClassParticipant(subjectMaths,    s6),     57.0),
               Map.entry(new ClassParticipant(subjectMaths,    s7),     98.0),
               Map.entry(new ClassParticipant(subjectMaths,    s8),     45.0)
            )
            ;
   
      record FinalGrade(Subject subject, Student student, double grade) {}
   
      final Map<Subject, List<Student>> top3StudentsPerSubject =
         finalGrades
            .entrySet()
            .stream()
            .map(entry -> new FinalGrade(entry.getKey().subject(), entry.getKey().student(), entry.getValue()))
            .collect
            (
               Collectors
                  .groupingBy
                  (
                     FinalGrade::subject,
                     Collectors
                        .collectingAndThen
                        (
                           Collectors.toList(),
                           list ->
                              list
                                 .stream()
                                 .sorted
                                 (
                                    Comparator
                                       .comparing(FinalGrade::grade)
                                       .reversed()
                                 )
                                 .map(FinalGrade::student)
                                 .limit(3)
                                 .toList()
                        )
                  )
            )
            ;
   
      top3StudentsPerSubject
         .forEach
         (
            (subject, listOfStudents) ->
            {
            
               System.out.println(subject.subjectName());
            
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
