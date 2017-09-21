import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.*;

public class MainController {
    @FXML private Label fileNameLabel;
    @FXML private TextArea membersTextArea;

    @FXML private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        try {
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file != null) {
                ArrayList<String[]> parsedFile = parseTxtFile(file);
                ArrayList<String[]> antiSorted = antiSorting(parsedFile);
                printToTextArea(antiSorted);
            }
        } catch (NullPointerException e) {
            txtAlert("Выберите TXT файл!!!");
            e.getStackTrace();
        }catch (Exception e) {
            txtAlert("Ошибочный формат файла!");
            e.printStackTrace();
        }
    }

    private ArrayList<String[]> antiSorting(ArrayList<String[]> IncomingArrayList) {
        ArrayList<String[]> result = new ArrayList<>();
        ArrayList<String[]> tmp = new ArrayList<>();

        tmp.add(IncomingArrayList.get(0));
        result.add(IncomingArrayList.get(0));
        IncomingArrayList.remove(0);

        while (IncomingArrayList.size() > 0) {
            for (int i = 0; i < IncomingArrayList.size(); i++) {
                if (!IncomingArrayList.get(i)[1].equals(tmp.get(0)[1])) {
                    tmp.clear();
                    tmp.add(IncomingArrayList.get(i));
                    result.add(IncomingArrayList.get(i));
                    IncomingArrayList.remove(i);
                }
            }
        }
        return result;
    }

    private void printToTextArea(ArrayList<String[]> IncomingArrayList) {
        membersTextArea.setText("");
        StringBuilder sb = new StringBuilder();

        for (String[] str : IncomingArrayList) {
            sb.append(str[0]).append(" - ").append(str[1]).append("\n");
        }
        membersTextArea.setText(sb.toString());
    }

    private ArrayList<String[]> parseTxtFile(File file) {
        ArrayList<String[]> result = new ArrayList<>();
        fileNameLabel.setText(file.getName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s = reader.readLine()) != null) {
                String[] tmp = s.trim().split(",");
                result.add(tmp);
            }
        } catch (FileNotFoundException e) {
            txtAlert("Файл не найден!");
            e.printStackTrace();
        } catch (IOException e) {
            txtAlert("Ошибка чтения!");
            e.printStackTrace();
        }
        return result;
    }

    private void txtAlert(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorText);
        fileNameLabel.setText("ERROR");
        alert.showAndWait();
    }
}
