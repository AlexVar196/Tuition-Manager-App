
/**
 *  The container StudentList class which holds an array of
 *  students that has methods add, remove,
 *  and find specific students.
 *  The array is initially size GROW_SIZE and grows by GROW_SIZE every time we run out of space.
 *  Removed item is replaced by the last item of the array.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class StudentList {

    private final int NOT_FOUND = -1;
    private final int GROW_SIZE = 5; //initial and grow size
    private Student[] studentList;
    private int numStudents;

    //This is the default constructor
    public StudentList() {
        numStudents = 0;
        studentList = new Student[GROW_SIZE];
    }

    /**
     * The find method searches for a specific Student at a given index.
     *
     * @param s The given student to find.
     * @return The index the member is found at or -1 if not found.
     */
    private int find(Student s) {
        int element; //Index the member is found at

        for (int i = 0; i < studentList.length; i++) {
            if (studentList[i] != null) {
                if (studentList[i].compareTo(s) == 0) {
                    element = i;
                    return element;
                }
            }
        }
        return NOT_FOUND;
    }

    /**
     * The grow method adds GROW_SIZE slots to the array each time the team
     * appears to be full.
     */
    private void grow() {

        //Create a new array with GROW_SIZE extra slots each time grow is invoked
        Student[] biggerStudentList = new Student[studentList.length + GROW_SIZE];
        for (int i = 0; i < studentList.length; i++) {
            biggerStudentList[i] = studentList[i];
        }
        studentList = biggerStudentList;  //The bigger array becomes the new team array
    }

    /**
     * Checks if the student list is empty.
     *
     * @return true if empty and false if not empty.
     */
    public boolean isEmpty() {
        if (numStudents == 0) {
            return true;
        }
        return false;
    }

    /**
     * The add method appends a new student to the end of the array. If the
     * array is full, the grow method is called to allocate more spaces.
     *
     * @param s The member to be added.
     */
    public void add(Student s) {
        if (numStudents % 4 != 0 || numStudents == 0) {
            studentList[numStudents] = s;
            numStudents++;
        } else {
            grow();  //grows array by GROW_SIZE if full.
            studentList[numStudents] = s;
            numStudents++;

        }

    }

    /**
     * The remove method removes a student at an index. The last item fills the
     * deleted slot and then becomes null.
     *
     * @param s The student to be removed.
     * @return Whether it is successfully removed true or false if wasn't
     * removed.
     */
    public boolean remove(Student s) {
        if (!isEmpty()) {
            int storedIndex = find(s);
            if (storedIndex >= 0) {
                studentList[storedIndex] = studentList[numStudents - 1]; //Last item in array fills slot
                studentList[numStudents - 1] = null; //Last item becomes null
                numStudents--; //Number of students decreases
                return true;
            }
        }
        return false;
    }

    /**
     * The contains method checks if the student is in the list already.
     *
     * @param s The student to be checked.
     * @return Whether true if it contains the student and false if not.
     */
    public boolean contains(Student s) {
        for (int i = 0; i < studentList.length; i++) {
            if (studentList[i] != null) {
                if (studentList[i].compareTo(s) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints the students.
     */
    public void print() {
        System.out.println("The Students are:\n-----------------------------------");
        for (int i = 0; i < studentList.length; i++) {
            if (studentList[i] == null) {
            } else {
                System.out.println((i + 1) + ". " + studentList[i].toString() + "\n-----------------------------------");
            }
        }

        System.out.println(">--- end of the list ---<");
    }
}
