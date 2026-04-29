import java.time.LocalDateTime;
import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;

class Task {
    String name;
    String priority;
    LocalDateTime deadline;

    Task(String name, String priority, LocalDateTime deadline) {
        this.name=name;
        this.priority=priority;
        this.deadline=deadline;
    }
}

public class TaskManager {

    static String normalizePriority(String p) { 
    if(p == null || p.trim().isEmpty()) return "Low";                                 //revise
    p = p.trim().toLowerCase();
    return p.substring(0,1).toUpperCase() + p.substring(1);
}

    static void saveTasks(ArrayList<Task> tasks) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))){ 

            for(Task t : tasks) {
                writer.write(t.name + "|" + t.priority + "|" + t.deadline);
                writer.newLine();
            }
            

        } catch(IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
       
        try(BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
        
        String line;

        while((line = reader.readLine())!=null) {
          String[] parts = line.split("\\|");
          if(parts.length < 3) continue;
          String name = parts[0];
          String priority = parts[1];
          LocalDateTime deadLine = LocalDateTime.parse(parts[2]);

          tasks.add(new Task(name, priority, deadLine));
        }
        
        } catch(IOException e) {
            System.out.println("No previous tasks found ");
        }
        return tasks;
    }

   static void showTasks(ArrayList<Task> tasks) {
       if(tasks.size()==0) {
        System.out.println("No tasks available\n");
        return;
       }
LocalDateTime now = LocalDateTime.now();
    System.out.println("Here are your tasks\n");
       for(int i=0; i<tasks.size(); i++) {
        Task t = tasks.get(i);
        System.out.println((i+1) + ". " + t.name + " ["+ t.priority +"]" + " Deadline: " + t.deadline);
        if(t.deadline.isBefore(now)) {
            System.out.println("⚠ OVERDUE");
        } else {
            System.out.println("Upcoming\n");
        }
       }

       System.out.println();
    }
    
  static void delTask(ArrayList<Task> tasks, Scanner sc) {
       if(tasks.size()==0) {
        System.out.println("No tasks to delete\n");
        return;
       }
       System.out.println("Enter task number to delete: ");
       int index = Integer.parseInt(sc.nextLine());

       if(index<1 || index>tasks.size()) {
        System.out.println("Invalid task number\n");
        return;
       }

       System.out.println("Deleted task " + tasks.get(index-1).name);
       tasks.remove(index-1);
       saveTasks(tasks);
       System.out.println();
    }

   public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);

    ArrayList<Task> tasks= loadTasks();
while(true) {
    System.out.println("Enter task (-1 to stop): ");
    String taskName = sc.nextLine();

    if(taskName.equals("-1"))  break;

System.out.println("Enter task Priority: ");
String priority = normalizePriority(sc.nextLine());  //revise

System.out.println("Enter task DeadLine(yyyy-MM-dd HH:mm): ");
String deadlineStr = sc.nextLine();

try {
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
LocalDateTime deadline = LocalDateTime.parse(deadlineStr.trim(), formatter);

    tasks.add(new Task(taskName, priority, deadline));
    saveTasks(tasks);
}catch (Exception e) {                                                          // revise
    System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");    
continue;
}
}
   

    while(true) {
        System.out.println("Enter your choice: " + "\n 1->Show all tasks" + "\n 2->Delete task" + "\n 3->Exit");
        
        int ch = Integer.parseInt(sc.nextLine());

        if(ch==1) {
              // 🔥 ADD THIS HERE
Collections.sort(tasks, (a, b) -> {
    LocalDateTime now = LocalDateTime.now();

    // 🔴 1. Overdue first
    boolean aOverdue = a.deadline.isBefore(now);
    boolean bOverdue = b.deadline.isBefore(now);

    if (aOverdue && !bOverdue) return -1;
    if (!aOverdue && bOverdue) return 1;

    // 🟡 2. Earlier deadline first
    if (!a.deadline.equals(b.deadline)) {
        return a.deadline.compareTo(b.deadline);
    }

    // 🟢 3. Priority
    List<String> order = Arrays.asList("High", "Medium", "Low");
    return order.indexOf(a.priority) - order.indexOf(b.priority);
});
            showTasks(tasks);
        } else if(ch==2) {
            delTask(tasks, sc);
        } else if(ch==3){
            System.exit(0);
        } else {
            System.out.println("Invalid choice(Enter 1 or 2 or 3)");
            return;
        }

    }
   }
}