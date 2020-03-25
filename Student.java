
/**
 * The Student class defines the properties
 * and methods of a student and implements the Comparable class.
 * Students have a first name, last name and the number of credits they take.
 * Students can compare names and calculate tuition due.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public abstract class Student implements Comparable {

    private String fname;
    private String lname;
    protected final int INSTATE_PPC = 433;
    protected final int OUTSTATE_PPC = 756;
    protected final int TRISTATE_PPC = 556;
    protected final int INTERNATIONAL_PPC = 945;
    protected final int INTERNATIONAL_FEE = 350;
    protected final int PART_TIME_FEE = 846;
    protected final int FULL_TIME_FEE = 1441;
    protected final int MAX_CREDITS = 15;
    protected int credit;

    /**
     * This is the Student constructor. Takes in a string first name and last
     * name, an int of number of credits.
     *
     * @param fname The student first name.
     * @param lname The student last name.
     * @param credit Number of credits taken by student.
     */
    public Student(String fname, String lname, int credit) {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }

    /**
     * Takes in an object and compares them. Checks if the two are the same
     * students that have same first and last names.
     *
     * @param obj takes in any object.
     * @return -1 if fname or last name are smaller than object's.
     * @return 1 if fname or last name are bigger than object's.
     * @return 0 if fname and last name are the same as the object's.
     */
    public int compareTo(Object obj) {
        if (obj == null) {
            return 0;  // in case of an empty object, don't add it.
        }

        Student student = (Student) obj;
        //First name and Last name must both be equal
        if (student.fname.compareToIgnoreCase(this.fname) == 0) {
            if (student.lname.compareToIgnoreCase(this.lname) == 0) {
                return 0;
            } else {
                return student.lname.compareToIgnoreCase(this.lname);  //returns negative number, positive number or 0. 
            }
        } else {
            return student.fname.compareToIgnoreCase(this.fname);  //returns negative number, positive number or 0. 
        }
    }

    /**
     * Returns a string with fname, lname and credit hours; subclasses will be
     * using this method to add more information.
     *
     * @return string.
     */
    public String toString() {
        return (this.fname + " " + this.lname + "\nTuition Due for " + credit + " credits: $");
    }

    //Concrete implementation is in the subclasses.
    public abstract int tuitionDue();
}
