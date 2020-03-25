
/**
 * The Instate class defines the properties
 * and methods of an Instate student and extends the Student class.
 * Instate students have a first name, last name, the number of credits they take and
 * they can be also have funds.
 * Students can print info and calculate tuition due.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class Instate extends Student {

    private int funds;

    /**
     * This is the Instate constructor. Takes in a string first name, last name,
     * an int of number of credits, and an int that indicates how much funds
     * they have.
     *
     * @param fname The student first name.
     * @param lname The student last name.
     * @param credit Number of credits taken by student.
     * @param funds int that indicates how much funds they have.
     */
    public Instate(String fname, String lname, int credit, int funds) {
        super(fname, lname, credit);
        this.funds = funds;

    }

    /**
     * The tuitionDue method calculates the tuition due of a student.
     * Calculation is based on the number of credits and if student has funds.
     *
     * @return an integer which is the final tuition due in dollars.
     */
    public int tuitionDue() {
        if (credit < 12) {
            int sum = ((credit * INSTATE_PPC) + PART_TIME_FEE);
            return sum;
        } else if (credit >= 12 && credit <= 15) {
            int sum = (credit * INSTATE_PPC) + FULL_TIME_FEE - this.funds;
            return sum;
        } else if (super.credit > MAX_CREDITS) {
            int sum = (MAX_CREDITS * INSTATE_PPC) + FULL_TIME_FEE - this.funds;
            return sum;
        }
        return -1;
    }

    /**
     * Returns a string with fname, lname and credit hours with the tuition due.
     *
     * @return string.
     */
    public String toString() {
        String string = "In-State student: " + super.toString() + this.tuitionDue();
        return string;
    }

    public static void main(String[] args) {

        //UNDER 12 Credits, Part-Time Fee, No Funds
        Instate studentOne = new Instate("Mike", "Flores", 9, 0);

        //EQUAL 12 Credits, Full-Time, $1000 fund
        Instate studentTwo = new Instate("Alex", "Var", 12, 1000);

        //EQUAL 15 Credits, Full-Time, $1000 fund
        Instate studentThree = new Instate("John", "Doe", 15, 1000);

        //OVER 15 Credits, Full-Time, $1000 fund
        Instate studentFour = new Instate("April", "Young", 17, 1000);

        //System.out.println(studentOne);
        //System.out.println(studentOne.tuitionDue());
        System.out.println(studentOne.toString());

        System.out.println("\n");

        //System.out.println(studentTwo);
        //System.out.println(studentTwo.tuitionDue());
        System.out.println(studentTwo.toString());

        System.out.println("\n");

        //System.out.println(studentThree);
        //System.out.println(studentThree.tuitionDue());
        System.out.println(studentThree.toString());

        System.out.println("\n");

        //System.out.println(studentFour);
        //System.out.println(studentFour.tuitionDue());
        System.out.println(studentFour.toString());

    }
}
