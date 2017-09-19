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
    private String skiRacers = "";

    @FXML private void handleLoad(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("txt files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        try {
            File file = fileChooser.showOpenDialog(Main.getStage());
            if (file != null){
                ArrayList<String[]> parsedFile = parseTxtFile(file);
                sortingArrayList(parsedFile);

            }
            membersTextArea.setText(skiRacers);

        }catch (NullPointerException e){
            txtAlert("Выберите TXT файл!!!");
            e.getStackTrace();
        }
    }

    private ArrayList<String[]> parseTxtFile(File file){
        ArrayList<String[]> result = new ArrayList<>();
        fileNameLabel.setText(file.getName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s;
            while ((s=reader.readLine()) != null){
                String[] tmp = s.split(",");
                result.add(tmp);
            }


            System.out.println(result);
        }catch (FileNotFoundException e){
            txtAlert("Файл не найден!");
            e.printStackTrace();
        }catch (IOException e){
            txtAlert("Ошибка чтения!");
            e.printStackTrace();
        }
        return result;
    }

    private void txtAlert(String errorText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorText);
        fileNameLabel.setText("ERROR");
        alert.showAndWait();
    }

    private void sortingArrayList(ArrayList<String[]> arrayList){
        ArrayList<String[]> tempArrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String[] tmp : arrayList) {
            set.add(tmp[1]);
        }

        for (int i = 0; i < arrayList.size();i++ ){
            String[] tmp = arrayList.get(i);
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()){

                if (iterator.next().equals(tmp[1])){
                    iterator.
                }
            }
        }

    }
}
