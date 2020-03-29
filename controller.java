
import java.text.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;

/**
 * The controller class acts as a GUI in order to handle user events.
 * This class is running until the program is terminated. This class
 * allows us to add and remove students as well as display all current
 * students with their calculated tuition.
 *
 * @author Michael Flores mof15
 * @author Alex Varshavsky av653
 */
public class controller {

    StudentList studentList = new StudentList();

    //All the FXML components to be displayed
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField numberOfCredits;
    @FXML
    private TextField funds;

    @FXML
    private RadioButton instate;
    @FXML
    private RadioButton outstate;
    @FXML
    private RadioButton international;

    @FXML
    private CheckBox funding;
    @FXML
    private CheckBox tristate;
    @FXML
    private CheckBox exchange;

    @FXML
    private TextArea theTextArea;

    /**
     * When the funding checkbox is selected allows editing the funds.
     */
    public void check() {
        if (funding.isSelected()) {
            funds.setEditable(true);
        } else {
            funds.setEditable(false);
            funds.clear();
        }
    }

    /**
     * When instate radio button is selected disable the rest.
     */
    public void instateSelect() {
        funding.setDisable(false);
        funds.clear();

        tristate.setDisable(true);
        tristate.setSelected(false);

        exchange.setDisable(true);
        exchange.setSelected(false);
    }

    /**
     * When outstate radio button is selected disable the rest.
     */
    public void outstateSelect() {
        funding.setDisable(true);
        funding.setSelected(false);
        funds.setEditable(false);
        funds.clear();

        tristate.setDisable(false);

        exchange.setDisable(true);
        exchange.setSelected(false);
    }

    /**
     * When international radio button is selected disable the rest.
     */
    public void internationalSelect() {
        funding.setDisable(true);
        funding.setSelected(false);
        funds.setEditable(false);
        funds.clear();

        tristate.setDisable(true);
        tristate.setSelected(false);

        exchange.setDisable(false);
    }

    /**
     *
     * The add function adds a student to the student list after checking that
     * all fields were correctly entered and that the student doesn't exist in
     * the list already.
     */
    public void add() {
        //Get input from user and check if its not empty
        if (firstName.getText().trim().equals("")) {
            theTextArea.appendText("You must enter First Name!\n");
        } else if (lastName.getText().trim().equals("")) {
            theTextArea.appendText("You must enter Last Name!\n");
        } else if (numberOfCredits.getText().trim().equals("")) {
            theTextArea.appendText("You must enter number of credits\n");
        } else if (!instate.isSelected() && !outstate.isSelected() && !international.isSelected()) {
            theTextArea.appendText("You must select a student type!\n");
        } else {

            String fName = firstName.getText();
            String lName = lastName.getText();
            int totalCredits = checkParse(numberOfCredits.getText()); // checks if input doesn't contain chars.
            if (totalCredits != -1) {
                boolean isTriState;
                boolean isExchange;

                if (instate.isSelected()) {
                    if (funding.isSelected()) {
                        if (funds.getText().trim().equals("")) { // checks if funding was selected but no value was entered
                            theTextArea.appendText("You selected funds, so you must enter Funds value!\n");
                        } else {

                            int fund = checkParse(funds.getText());
                            if (fund != -1) {
                                if (fund >= 0 && totalCredits > 0) {
                                    if (totalCredits < 12) {
                                        fund = 0;
                                    }
                                    Instate student = new Instate(fName, lName, totalCredits, fund);
                                    if (studentList.contains(student)) {  // checks if student already exists.
                                        theTextArea.appendText("Student already exists in the database!\n");
                                    } else {
                                        if (totalCredits < 12) {
                                            studentList.add(student);   //adds student to the next available index in array.
                                            theTextArea.appendText("Student added but funds were not applied because student is part time.\n");
                                        } else {
                                            studentList.add(student);   //adds student to the next available index in array.
                                        }
                                    }
                                } else {
                                    theTextArea.appendText("Credit and Funding must be positive!\n");
                                }
                            } else {
                                theTextArea.appendText("Funds must be a number! You can't include characters here\n");
                            }
                        }
                    } else {
                        int fund = 0;
                        if (totalCredits > 0) {
                            Instate student = new Instate(fName, lName, totalCredits, fund);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else {
                            theTextArea.appendText("Credit must be 1 or more!\n");
                        }

                    }
                }

                if (outstate.isSelected()) {
                    if (tristate.isSelected()) {
                        isTriState = true;
                        if (totalCredits > 0) {
                            Outstate student = new Outstate(fName, lName, totalCredits, isTriState);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else {
                            theTextArea.appendText("Credit must be 1 or more!\n");
                        }

                    } else if (!tristate.isSelected()) {
                        isTriState = false;
                        if (totalCredits > 0) {
                            Outstate student = new Outstate(fName, lName, totalCredits, isTriState);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else {
                            theTextArea.appendText("Credit must be 1 or more!\n");
                        }
                    } else {
                        theTextArea.appendText("Something went wrong with the input.\n");
                    }
                }

                if (international.isSelected()) {
                    if (totalCredits > 8) {

                        if (exchange.isSelected()) {
                            isExchange = true;
                            International student = new International(fName, lName, totalCredits, isExchange);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else if (!exchange.isSelected()) {
                            isExchange = false;
                            International student = new International(fName, lName, totalCredits, isExchange);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else {
                            theTextArea.appendText("Something went wrong with the input.\n");
                        }
                    } else if (totalCredits > 0) {
                        theTextArea.appendText("International students must take at least 8 credits.\n");
                    } else {
                        theTextArea.appendText("Number of credits must be a positive number!\n");
                    }
                }
            } else {
                theTextArea.appendText("Number of credits must be a positive number! You can't include characters here\n");
            }
        }
    }

    /**
     * The remove method removes a student from the student list, unless the
     * student is not an actual member. If member exists, it removes him and the
     * last item in the array replaces it's position.
     */
    public void remove() {
        //Get input from user and check if its not empty
        if (firstName.getText().trim().equals("")) {
            theTextArea.appendText("You must enter First Name!\n");
        } else if (lastName.getText().trim().equals("")) {
            theTextArea.appendText("You must enter Last Name!\n");
        } else {
            String fName = firstName.getText();
            String lName = lastName.getText();
            Student studentToRemove = new Instate(fName, lName, 0, 0);
            boolean wasRemoved = studentList.remove(studentToRemove);  //removes member.
            if (wasRemoved == true) {
                theTextArea.appendText("Student " + fName + " " + lName + " was removed from the list.\n");
            } else {
                theTextArea.appendText("Student " + fName + " " + lName + " was not found on the list.\n");
            }
        }
    }

    /**
     * The print method outputs all students in the list. If the team is empty
     * it displays that it has 0 students.
     */
    public void print() {
        if (studentList.isEmpty()) {
            theTextArea.appendText("We have 0 students!\n");
        } else {
            theTextArea.appendText(studentList.toString());
        }
    }

    /**
     * Checks if the string entered is in the correct format to be parsed as an
     * integer. If it is, returns the parsed int, if not returns -1.
     *
     * @param string The string to be checked. Catches NumberFormatException if
     * string contains chars
     * @return int => the parsed int, or -1 if not valid.
     */
    public int checkParse(String string) {
        try {
            int num = Integer.parseInt(string);
            return num;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
