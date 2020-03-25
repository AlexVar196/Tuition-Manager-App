
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The TuitionManager class acts as a user interface while reading from standard
 * input and display standard output. This class is running until done = true
 * and keeps taking commands, validating them and executing them if they are
 * correct. This class allows us to add and remove students as well as display
 * all current students with their calculated tuition.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class TuitionManager {

    Scanner stdin;
    StudentList studentList;
    private final int IN_STATE = 1;
    private final int OUT_OF_STATE = 2;
    private final int INTERNATIONAL = 3;

    /**
     * The Run method start the interface and waits for commands. Initializes
     * the scanners and while done = false, it runs and takes the next command.
     * Checks if the command is valid after tokenizing the input.
     */
    public void run() {
        stdin = new Scanner(System.in); //init scanner.
        studentList = new StudentList();    // create new studentList object.
        char command;
        boolean done = false;
        String fname;
        String lname;
        int studentType; // IN_STATE, OUT_OF_STATE, or INTERNATIONAL.
        int credit;  // number of credits taken.
        int funding;
        char state; // 'T' = True / 'F' = False

        while (!done) {
            String input = stdin.nextLine();
            StringTokenizer string = new StringTokenizer(input, " ");
            command = string.nextToken().charAt(0);

            // any case besides 'I' , 'O', 'N', 'R', 'P' and 'Q' are invalid.
            switch (command) {
                case 'I':
                    fname = string.nextToken();
                    lname = string.nextToken();
                    studentType = IN_STATE;
                    credit = Integer.parseInt(string.nextToken());
                    funding = Integer.parseInt(string.nextToken());
                    add(fname, lname, studentType, credit, funding); // adds student.
                    break;

                case 'O':
                    fname = string.nextToken();
                    lname = string.nextToken();
                    studentType = OUT_OF_STATE;
                    credit = Integer.parseInt(string.nextToken());
                    state = string.nextToken().charAt(0);
                    add(fname, lname, studentType, credit, state);  // adds student.
                    break;

                case 'N':
                    fname = string.nextToken();
                    lname = string.nextToken();
                    studentType = INTERNATIONAL;
                    credit = Integer.parseInt(string.nextToken());
                    state = string.nextToken().charAt(0);
                    add(fname, lname, studentType, credit, state);  // adds student.
                    break;

                case 'R':
                    fname = string.nextToken();
                    lname = string.nextToken();
                    remove(fname, lname);  // removes student.
                    break;
                case 'P':  // Prints all students and tuition.
                    print();
                    break;
                case 'Q':  // Terminates program.
                    System.out.println("Program terminated");
                    done = true;
                    break;
                default:
                    System.out.println("Command '" + command + "'not supported!");
                    break;
            }
        }

        System.exit(0);
    }

    /**
     * The add method checks for duplication, and then adds the student to the
     * team if it doesn't exist.
     *
     * @param firstName The student first name.
     * @param lastName The student last name.
     * @param credit Number of credits taken by student.
     * @param studentType The student type: IN_STATE / OUT_OF_STATE /
     * INTERNATIONAL.
     * @param funds  The additional data provided. An Integer that
     * represents funds.
     */
    private void add(String firstName, String lastName, int studentType, int credit, int funds) {

        if (studentType == IN_STATE) {
            Instate student = new Instate(firstName, lastName, credit, funds);
            if (studentList.contains(student)) {  // checks if student already exists.
                System.out.println("Student already exists in the database!");
            } else {
                studentList.add(student);   //adds student to the next available index in array.
            }
        }
    }
    
        /**
     * The add method checks for duplication, and then adds the student to the
     * team if it doesn't exist.
     *
     * @param firstName The student first name.
     * @param lastName The student last name.
     * @param credit Number of credits taken by student.
     * @param studentType The student type: IN_STATE / OUT_OF_STATE /
     * INTERNATIONAL.
     * @param state  a Char representing true/false for
     * isTriState and isExchange. In a form of 'T' - true and 'F- false that's gonna be parsed later.
     */

    private void add(String firstName, String lastName, int studentType, int credit, char state) {

        boolean isTriState;
        boolean isExchange;
        if (studentType == OUT_OF_STATE) {
            if (state == 'T') {
                isTriState = true;
                Outstate student = new Outstate(firstName, lastName, credit, isTriState);
                if (studentList.contains(student)) {  // checks if student already exists.
                    System.out.println("Student already exists in the database!");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }

            } else if (state == 'F') {
                isTriState = false;
                Outstate student = new Outstate(firstName, lastName, credit, isTriState);
                if (studentList.contains(student)) {  // checks if student already exists.
                    System.out.println("Student already exists in the database!");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }
            } else {
                System.out.println("Something went wrong with the input.");
            }
        }

        if (studentType == INTERNATIONAL) {
            if (state == 'T') {
                isExchange = true;
                International student = new International(firstName, lastName, credit, isExchange);
                if (studentList.contains(student)) {  // checks if student already exists.
                    System.out.println("Student already exists in the databese!");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }
            } else if (state == 'F') {
                isExchange = false;
                International student = new International(firstName, lastName, credit, isExchange);
                if (studentList.contains(student)) {  // checks if student already exists.
                    System.out.println("Student already exists in the databese!");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }
            } else {
                System.out.println("Something went wrong with the input. ");
            }

        }
    }

    /**
     * The remove method removes a student from the student list, unless the
     * student is not an actual member. If member exists, it removes him and the
     * last item in the array replaces it's position.
     *
     * @param firstName The first name of the student to be removed.
     * @param lastName The last name of the student to be removed.
     */
    private void remove(String firstName, String lastName) {

        Student studentToRemove = new Instate(firstName, lastName, 0, 0);
        boolean wasRemoved = studentList.remove(studentToRemove);  //removes member.
        if (wasRemoved == true) {
            System.out.println("Student " + firstName + " " + lastName + " was removed from the list.");
        } else {
            System.out.println("Student " + firstName + " " + lastName + " was not found on the list.");
        }
    }

    /**
     * The print method outputs all students in the list. If the team is empty
     * it displays that it has 0 students.
     */
    private void print() {
        if (studentList.isEmpty()) {
            System.out.println("We have 0 students!");
        } else {
            studentList.print();
        }
    }
}
