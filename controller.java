
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;

public class controller {

    StudentList studentList = new StudentList();

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

    //When the funding checkbox is selected
    public void check() {
        if (funding.isSelected()) {
            funds.setEditable(true);
        } else {
            funds.setEditable(false);
            funds.clear();
        }
    }

    //When certain radio buttons are selected disable others
    public void instateSelect() {
        funding.setDisable(false);
        funds.clear();

        tristate.setDisable(true);
        tristate.setSelected(false);

        exchange.setDisable(true);
        exchange.setSelected(false);
    }

    public void outstateSelect() {
        funding.setDisable(true);
        funding.setSelected(false);
        funds.setEditable(false);
        funds.clear();

        tristate.setDisable(false);

        exchange.setDisable(true);
        exchange.setSelected(false);
    }

    public void internationalSelect() {
        funding.setDisable(true);
        funding.setSelected(false);
        funds.setEditable(false);
        funds.clear();

        tristate.setDisable(true);
        tristate.setSelected(false);

        exchange.setDisable(false);
    }

    public void add() {
        //Get input from user
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
            int noc = checkParse(numberOfCredits.getText());
            if (noc != -1) {
                boolean isTriState;
                boolean isExchange;

                if (instate.isSelected()) {
                    if (funding.isSelected()) {
                        if (funds.getText().trim().equals("")) {
                            theTextArea.appendText("You selected funds, so you must enter Funds value!\n");
                        } else {

                            int fund = checkParse(funds.getText());
                            if (fund != -1) {
                                if (fund >= 0 && noc > 0) {
                                    if (noc < 12) {
                                        fund = 0;
                                    }
                                    Instate student = new Instate(fName, lName, noc, fund);
                                    if (studentList.contains(student)) {  // checks if student already exists.
                                        theTextArea.appendText("Student already exists in the database!\n");
                                    } else {
                                        if (noc < 12) {
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
                        if (noc > 0) {
                            Instate student = new Instate(fName, lName, noc, fund);
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
                        if (noc > 0) {
                            Outstate student = new Outstate(fName, lName, noc, isTriState);
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
                        if (noc > 0) {
                            Outstate student = new Outstate(fName, lName, noc, isTriState);
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
                    if (noc > 8) {

                        if (exchange.isSelected()) {
                            isExchange = true;
                            International student = new International(fName, lName, noc, isExchange);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else if (!exchange.isSelected()) {
                            isExchange = false;
                            International student = new International(fName, lName, noc, isExchange);
                            if (studentList.contains(student)) {  // checks if student already exists.
                                theTextArea.appendText("Student already exists in the database!\n");
                            } else {
                                studentList.add(student);   //adds student to the next available index in array.
                            }
                        } else {
                            theTextArea.appendText("Something went wrong with the input.\n");
                        }
                    } else if (noc > 0) {
                        theTextArea.appendText("International students must take at least 8 credits.\n");
                    } else {
                        theTextArea.appendText("Number of credits must be a positive number!");
                    }
                }
            } else {
                theTextArea.appendText("Number of credits must be a positive number! You can't include characters here\n");
            }
        }
    }

    public void remove() {

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

    public void print() {
        if (studentList.isEmpty()) {
            theTextArea.appendText("We have 0 students!\n");
        } else {
            theTextArea.appendText(studentList.toString());
        }
    }

    public int checkParse(String s) {
        try {
            int num = Integer.parseInt(s);
            return num;
        } catch (Exception e) {
            return -1;
        }
    }

}
