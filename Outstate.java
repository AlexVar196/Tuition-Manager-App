
/**
 * The Outstate class defines the properties
 * and methods of an outstate student and extends the Student class.
 * Outstate students have a first name, last name, the number of credits they take and
 * they can be from tri-state area or not.
 * Students can print info and calculate tuition due.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class Outstate extends Student {

    private boolean tristate;

    /**
     * This is the Outstate constructor. Takes in a string first name, last
     * name, an int of number of credits, and a boolean that indicates if they
     * are from Tri-state.
     *
     * @param fname The student first name.
     * @param lname The student last name.
     * @param credit Number of credits taken by student.
     * @param isTriState True or False.
     */
    public Outstate(String fname, String lname, int credit, boolean isTriState) {
        super(fname, lname, credit);
        this.tristate = isTriState;
    }

    /**
     * The tuitionDue method calculates the tuition due of a student.
     * Calculation is based on the number of credits and if student getting a
     * tri-state discount.
     *
     * @return an integer which is the final tuition due in dollars.
     */
    public int tuitionDue() {
        if (credit < 12) {
            return ((credit * OUTSTATE_PPC) + PART_TIME_FEE);
        } else if (credit >= 12 && credit <= 15) {
            if (tristate == false) {
                return ((credit * OUTSTATE_PPC) + FULL_TIME_FEE);
            } else if (tristate == true) {
                return ((credit * TRISTATE_PPC) + FULL_TIME_FEE);
            }
        } else if (credit > MAX_CREDITS) {
            if (tristate == false) {
                return ((MAX_CREDITS * OUTSTATE_PPC) + FULL_TIME_FEE);
            } else if (tristate == true) {
                return ((MAX_CREDITS * TRISTATE_PPC) + FULL_TIME_FEE);
            }
        }
        return -1;
    }

    /**
     * Returns a string with fname, lname and credit hours with the tuition due.
     *
     * @return string.
     */
    public String toString() {
        String string = "Out-of-state student: " + super.toString() + this.tuitionDue();
        return string;
    }

    public static void main(String[] args) {

        //UNDER 12 Credits, Part-Time Fee, Tristate doesn't apply
        Outstate studentOne = new Outstate("Mike", "Flores", 8, false);

        //UNDER 12 Credits, Part-Time Fee, Tristate doesn't apply
        Outstate studentOneSame = new Outstate("Mike", "Flores", 8, true);

        //System.out.println(studentOne);
        //System.out.println(studentOne.tuitionDue());
        System.out.println(studentOne.toString());

        System.out.println("\n");

        //System.out.println(studentOneSame);
        //System.out.println(studentOneSame.tuitionDue());
        System.out.println(studentOneSame.toString());

        System.out.println("\n");

        //EQUAL 12 Credits, Full-Time Fee, NOT in tristate
        Outstate studentTwo = new Outstate("Alex", "Var", 12, false);

        //EQUAL 12 Credits, Full-Time Fee, YES in tristate
        Outstate studentTwoSame = new Outstate("Alex", "Var", 12, true);

        //System.out.println(studentTwo);
        //System.out.println(studentTwo.tuitionDue());
        System.out.println(studentTwo.toString());

        System.out.println("\n");

        //System.out.println(studentTwoSame);
        //System.out.println(studentTwoSame.tuitionDue());
        System.out.println(studentTwoSame.toString());

        System.out.println("\n");

        //EQUAL 15 Credits, Full-Time Fee, NOT in tristate
        Outstate studentThree = new Outstate("John", "Doe", 15, false);

        //EQUAL 15 Credits, Full-Time Fee, YES in tristate
        Outstate studentThreeSame = new Outstate("John", "Doe", 15, true);

        //System.out.println(studentThree);
        //System.out.println(studentThree.tuitionDue());
        System.out.println(studentThree.toString());

        System.out.println("\n");

        //System.out.println(studentThreeSame);
        //System.out.println(studentThreeSame.tuitionDue());
        System.out.println(studentThreeSame.toString());

        System.out.println("\n");

        //OVER 15 Credits, Full-Time Fee, NOT in tristate
        Outstate studentFour = new Outstate("April", "Young", 17, false);

        //OVER 15 Credits, Full-Time Fee, YES in tristate
        Outstate studentFourSame = new Outstate("April", "Young", 17, true);

        //System.out.println(studentFour);
        //System.out.println(studentFour.tuitionDue());
        System.out.println(studentFour.toString());

        System.out.println("\n");

        //System.out.println(studentFourSame);
        //System.out.println(studentFourSame.tuitionDue());
        System.out.println(studentFourSame.toString());

    }
}
