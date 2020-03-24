import javafx.fxml.FXML;
//import javafx.scene.control.Button;
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
    public void check(){
        if(funding.isSelected()) {
          funds.setEditable(true);
        }
        else{
            funds.setEditable(false);
        }
    }
    //When certain radio buttons are selected disable other
    public void instateSelect(){
        funding.setDisable(false);
        tristate.setDisable(true);
        exchange.setDisable(true);
    }
    public void outstateSelect(){
        funding.setDisable(true);
        funds.setEditable(false);
        tristate.setDisable(false);
        exchange.setDisable(true);
    }
    public void internationalSelect(){
        funding.setDisable(true);
        funds.setEditable(false);
        tristate.setDisable(true);
        exchange.setDisable(false);
    }
    public void add(){
        //Get input from user
        String fName = firstName.getText();
        String lName = lastName.getText();
        int noc = Integer.parseInt(numberOfCredits.getText());

        boolean isTriState;
        boolean isExchange;

        if (instate.isSelected()) {
            String str = funds.getText();
            int fund = Integer.parseInt(str);
            Instate student = new Instate(fName, lName, noc, fund);
            if (studentList.contains(student)) {  // checks if student already exists.
                theTextArea.appendText("Student already exists in the database!\n");
            } else {
                studentList.add(student);   //adds student to the next available index in array.
            }
        }

        if (outstate.isSelected()) {
            if (tristate.isSelected()) {
                isTriState = true;
                Outstate student = new Outstate(fName, lName, noc, isTriState);
                if (studentList.contains(student)) {  // checks if student already exists.
                    theTextArea.appendText("Student already exists in the database!\n");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }

            } else if (!tristate.isSelected()) {
                isTriState = false;
                Outstate student = new Outstate(fName, lName, noc, isTriState);
                if (studentList.contains(student)) {  // checks if student already exists.
                    theTextArea.appendText("Student already exists in the database!\n");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }
            } else {
                theTextArea.appendText("Something went wrong with the input.\n");
            }
        }

        if (international.isSelected()) {
            if (tristate.isSelected()) {
                isExchange = true;
                International student = new International(fName, lName, noc, isExchange);
                if (studentList.contains(student)) {  // checks if student already exists.
                    theTextArea.appendText("Student already exists in the database!\n");
                } else {
                    studentList.add(student);   //adds student to the next available index in array.
                }
            } else if (!tristate.isSelected()) {
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

        }
    }

    public void remove(){
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

    public void print(){
        if (studentList.isEmpty()) {
            theTextArea.appendText("We have 0 students!\n");
        } else {
            theTextArea.appendText("The Students are:\n-----------------------------------");
            /*for (int i = 0; i < studentList.length; i++) {
                if (studentList[i] == null) {
                } else {
                    System.out.println((i + 1) + ". " + studentList[i].toString() + "\n-----------------------------------");
                }
            }*/

            theTextArea.appendText(">--- end of the list ---<");
        }
    }
}

