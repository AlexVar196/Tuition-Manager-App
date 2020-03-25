
/**
 * The International class defines the properties
 * and methods of an International student and extends the Student class.
 * International students have a first name, last name, the number of credits they take and
 * they can be also exchange students.
 * Students can print info and calculate tuition due.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class International extends Student {

    private boolean exchange;

    /**
     * This is the International constructor. Takes in a string first name, last
     * name, an int of number of credits, and a boolean that indicates if they
     * are exchange students.
     *
     * @param fname The student first name.
     * @param lname The student last name.
     * @param credit Number of credits taken by student.
     * @param isExchange True or False.
     */
    public International(String fname, String lname, int credit, boolean isExchange) {
        super(fname, lname, credit);
        this.exchange = isExchange;
    }

    /**
     * The tuitionDue method calculates the tuition due of a student.
     * Calculation is based on the number of credits and if student paying only
     * exchange student specific fees.
     *
     * @return an integer which is the final tuition due in dollars.
     */
    public int tuitionDue() {
        if (credit >= 9) {
            if (exchange == true) {
                return FULL_TIME_FEE + INTERNATIONAL_FEE;
            } else if (exchange == false) {
                if (credit >= 9 && credit < 12) {
                    return ((credit * INTERNATIONAL_PPC) + PART_TIME_FEE + INTERNATIONAL_FEE);
                } else if (credit >= 12 && credit <= 15) {
                    return ((credit * INTERNATIONAL_PPC) + FULL_TIME_FEE + INTERNATIONAL_FEE);
                } else if (credit > MAX_CREDITS) {
                    return ((MAX_CREDITS * INTERNATIONAL_PPC) + FULL_TIME_FEE + INTERNATIONAL_FEE);
                }
            }
        } else {
            System.out.println("Not enough credits taken for international student. Must be at least 9.");
        }
        return -1;
    }

    /**
     * Returns a string with fname, lname and credit hours with the tuition due.
     *
     * @return string.
     */
    public String toString() {
        String string = "International student: " + super.toString() + this.tuitionDue();
        return string;
    }

    public static void main(String[] args) {

        //LESS THAN 9 Credits which is not allowed
        International illegal = new International("Wise", "Guy", 8, false);

        //System.out.println(illegal);
        //System.out.println(illegal.tuitionDue());
        System.out.println(illegal.toString());

        System.out.println("\n");

        //EQUAL 9 Credits, Part-Time Fee, NOT an exchange
        International studentOne = new International("Mike", "Flores", 9, false);

        //EQUAL 9 Credits, Part-Time Fee, YES an exchange
        International studentOneSame = new International("Mike", "Flores", 9, true);

        //System.out.println(studentOne);
        //System.out.println(studentOne.tuitionDue());
        System.out.println(studentOne.toString());

        System.out.println("\n");

        //System.out.println(studentOneSame);
        //System.out.println(studentOneSame.tuitionDue());
        System.out.println(studentOneSame.toString());

        System.out.println("\n");

        //EQUAL 12 Credits, Full-Time Fee, NOT an exchange
        International studentTwo = new International("Alex", "Var", 12, false);

        //EQUAL 12 Credits, Full-Time Fee, YES an exchange
        International studentTwoSame = new International("Alex", "Var", 12, true);

        //System.out.println(studentTwo);
        //System.out.println(studentTwo.tuitionDue());
        System.out.println(studentTwo.toString());

        System.out.println("\n");

        //System.out.println(studentTwoSame);
        //System.out.println(studentTwoSame.tuitionDue());
        System.out.println(studentTwoSame.toString());

        System.out.println("\n");

        //EQUAL 15 Credits, Full-Time Fee, NOT an exchange
        International studentThree = new International("John", "Doe", 15, false);

        //EQUAL 15 Credits, Full-Time Fee, YES an exchange
        International studentThreeSame = new International("John", "Doe", 15, true);

        //System.out.println(studentThree);
        //System.out.println(studentThree.tuitionDue());
        System.out.println(studentThree.toString());

        System.out.println("\n");

        //System.out.println(studentThreeSame);
        //System.out.println(studentThreeSame.tuitionDue());
        System.out.println(studentThreeSame.toString());

        System.out.println("\n");

        //OVER 15 Credits, Full-Time Fee, NOT an exchange
        International studentFour = new International("April", "Young", 17, false);

        //OVER 15 Credits, Full-Time Fee, YES an exchange
        International studentFourSame = new International("April", "Young", 17, true);

        //System.out.println(studentFour);
        //System.out.println(studentFour.tuitionDue());
        System.out.println(studentFour.toString());

        System.out.println("\n");

        //System.out.println(studentFourSame);
        //System.out.println(studentFourSame.tuitionDue());
        System.out.println(studentFourSame.toString());

    }
}
