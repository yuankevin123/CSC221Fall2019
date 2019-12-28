package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.xml.bind.JAXB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class PhonebookController {

    private int currRecord = 0;

    private File fileStorage = null;
    private RecordList recordList = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField txtFldName;

    @FXML
    private TextField txtFldState;

    @FXML
    private TextField txtFldPhone;

    @FXML
    private Label lblCurrRecord;

    @FXML
    private Button btnNavDel;

    @FXML
    private Button btnNavAdd;

    @FXML
    private Button btnNavPrev;

    @FXML
    private Button btnNavNext;

    @FXML
    private Button btnSerialize;

    @FXML
    private Button btnLoad;

    @FXML
    private Label lblFilename;

    @FXML
    private Button btnExit;

    @FXML
    private void buttonClicked(ActionEvent event) {

        if (event.getSource() instanceof Button) {

            Button button = (Button) event.getSource();

            if (button.getId().equals(btnSerialize.getId())) {

                if (saveRecord())
                    serializeFile();
            }
            else if (button.getId().equals(btnLoad.getId()))
                setFile();
            else if (button.getId().equals(btnNavDel.getId()))
                delRecord();
            else if (button.getId().equals(btnNavAdd.getId())) {

                if (saveRecord())
                    newRecord();
            }
            else if (button.getId().equals(btnNavPrev.getId())) {

                if (saveRecord()) {

                    --currRecord;
                    updateRecrod(currRecord);
                }
            }
            else if (button.getId().equals(btnNavNext.getId())) {

                if (saveRecord()) {

                    ++currRecord;
                    updateRecrod(currRecord);
                }
            }
            else if (button.getId().equals(btnExit.getId()))
                exit();

            enableDisable();
        }
    }

    private boolean saveRecord() {

        boolean result = true;

        if (currRecord > 0) {

            if (validateFields()) {

                Record record = recordList.getLst().get(currRecord - 1);

                record.setName(txtFldName.getText());
                record.setState(txtFldState.getText());
                record.setPhone(txtFldPhone.getText());
            }
            else
                result = false;
        }

        return result;
    }

    private void newRecord() {

        recordList.createNew();
        updateRecrod(recordList.getLst().size());

        clearFields();

        txtFldName.requestFocus();
    }

    private void delRecord() {

        if (currRecord > 0) {

            recordList.getLst().remove(currRecord - 1);

            if (currRecord >= recordList.getLst().size())
                --currRecord;
            else if (currRecord >= recordList.getLst().size())

                clearFields();
            updateRecrod(currRecord);
        }
    }

    private void clearFields() {

        txtFldName.setText("");
        txtFldState.setText("");
        txtFldPhone.setText("");
    }

    private void updateRecrod(int currRecord) {

        this.currRecord = currRecord;

        if (currRecord == 0)
            lblCurrRecord.setText("? of ?");
        else {

            Record record = recordList.getLst().get(currRecord - 1);

            txtFldName.setText(record.getName());
            txtFldState.setText(record.getState());
            txtFldPhone.setText(record.getPhone());

            lblCurrRecord.setText(String.format("%d of %d", currRecord, recordList.getLst().size()));
        }
    }

    private void setFile() {

        File file = getFileChooser().showOpenDialog(borderPane.getScene().getWindow());

        if (file != null) {

            fileStorage = file;

            try (BufferedReader br = Files.newBufferedReader(Paths.get(fileStorage.getAbsolutePath()))) {

                recordList = JAXB.unmarshal(fileStorage, RecordList.class);

                if (recordList.getLst().size() > 0) {
                    currRecord = 1;
                    updateRecrod(currRecord);
                }
            }
            catch (Exception ex) {

                recordList = new RecordList();
            }
            finally {

                lblFilename.setText("File: " + fileStorage.getName());
            }
        }
    }

    private void enableDisable() {

        txtFldName.setDisable(true);
        txtFldState.setDisable(true);
        txtFldPhone.setDisable(true);
        btnNavAdd.setDisable(true);
        btnNavDel.setDisable(true);
        btnNavPrev.setDisable(true);
        btnNavNext.setDisable(true);
        btnSerialize.setDisable(true);

        btnNavAdd.setDisable(false);
        btnLoad.setDisable(false);

        if (currRecord > 0) {

            txtFldName.setDisable(false);
            txtFldState.setDisable(false);
            txtFldPhone.setDisable(false);

            btnNavDel.setDisable(false);
        }

        if (currRecord > 1)
            btnNavPrev.setDisable(false);

        if (currRecord <= recordList.getLst().size() - 1)
            btnNavNext.setDisable(false);

        if (fileStorage != null)
            btnSerialize.setDisable(false);
    }

    private void serializeFile() {

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileStorage.getAbsolutePath()))) {

            JAXB.marshal(recordList, fileStorage);
            bw.flush();
        }
        catch (Exception ex) {

            showAlert("Failed to Serialize File");
        }
    }

    private FileChooser getFileChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an XML file");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML File", "*.xml"));

        return fileChooser;
    }

    private void showAlert(String message) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid value");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void exit() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK)
            System.exit(0);
    }

    private final Pattern patternName = Pattern.compile("([A-Z]+[a-zA-Z]{2,}\\s*)+");
    private final Pattern patternState = Pattern.compile("[A-Z][a-zA-Z]{2,}|([A-Z]+[a-zA-Z]*\\s[A-Z][a-zA-Z]{2,})");
    private final Pattern patternPhone = Pattern.compile("\\([1-9]\\d{2}\\)\\s[1-9]\\d{2}\\s-\\s\\d{4}");

    private boolean validateFields() {

        boolean result = true;

        if (!patternName.matcher(txtFldName.getText()).matches()) {

            showAlert("Invalid Name. Names should start with an uppercase letter followed by at least two characters");
            txtFldName.requestFocus();
            result = false;
        }
        else if (!patternState.matcher(txtFldState.getText()).matches()) {

            showAlert("Invalid State. States should consist of one or two words");
            txtFldState.requestFocus();
            result = false;
        }
        else if (!patternPhone.matcher(txtFldPhone.getText()).matches()) {

            showAlert("Invalid Phone number. Ex (212) 555 - 1234");
            txtFldPhone.requestFocus();
            result = false;
        }

        return result;
    }

    @FXML
    void initialize() {

        assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert txtFldName != null : "fx:id=\"txtFldName\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert txtFldState != null : "fx:id=\"txtFldState\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert txtFldPhone != null : "fx:id=\"txtFldPhone\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert lblCurrRecord != null : "fx:id=\"lblCurrRecord\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnNavDel != null : "fx:id=\"btnNavDel\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnNavAdd != null : "fx:id=\"btnNavAdd\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnNavPrev != null : "fx:id=\"btnNavPrev\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnNavNext != null : "fx:id=\"btnNavNext\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnSerialize != null : "fx:id=\"btnSerialize\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnLoad != null : "fx:id=\"btnLoad\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert lblFilename != null : "fx:id=\"lblFilename\" was not injected: check your FXML file 'Phonebook.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Phonebook.fxml'.";

        // addFocusPropert(txtFldName);
        // addFocusPropert(txtFldState);
        // addFocusPropert(txtFldPhone);
    }

    // private void addFocusPropert(TextField txtFld) {
    //
    // txtFld.focusedProperty().addListener(new ChangeListener<Boolean>() {
    //
    // @Override
    // public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    //
    // if (!newValue)
    // validateFields();
    // }
    // });
    // }
}
