import java.util.*;

class Task {
    String name;
    String priority;

    Task(String name, String priority) {
        this.name=name;
        this.priority=priority;
    }
}


public class TaskManager {

   static void showTasks(ArrayList<Task> tasks) {
       if(tasks.size()==0) {
        System.out.println("No tasks available\n");
        return;
       }
System.out.println("Here are your tasks\n");
       for(int i=0; i<tasks.size(); i++) {
        Task t = tasks.get(i);
        System.out.println((i+1) + ". " + t.name + " [" + t.priority + "]");
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

       System.out.println("Deleted task " + tasks.get(index-1));
       tasks.remove(index-1);
       System.out.println();
    }

   public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);

    ArrayList<Task> tasks= new ArrayList<>();
while(true) {
    System.out.println("Enter task (-1 to stop): ");
    String taskName = sc.nextLine();

    if(taskName.equals("-1"))  break;

System.out.println("Enter task Priority: ");
String priority = sc.nextLine();

    tasks.add(new Task(taskName, priority));
}

    while(true) {
        System.out.println("Enter your choice: " + "\n 1->Show all tasks" + "\n 2->Delete task" + "\n 3->Exit");
        //int ch = sc.nextInt(); --why wrong?
        int ch = Integer.parseInt(sc.nextLine());

        if(ch==1) {
              // 🔥 ADD THIS HERE
    Collections.sort(tasks, (a, b) -> {
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