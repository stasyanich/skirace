import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
                parseTxtFile(file);
            }
            membersTextArea.setText(skiRacers);

        }catch (NullPointerException e){
            txtAlert("Выберите TXT файл!!!");
            e.getStackTrace();
        }
    }

    private void parseTxtFile(File file){
        HashMap<String,String> result = new HashMap<>();
        fileNameLabel.setText(file.getName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] str;
            String s;
            while ((s=reader.readLine()) != null){
                str = s.split(",");
                result.put(str[0],str[1]);

            }
            skiRacers = printMap(result);
        }catch (FileNotFoundException e){
            txtAlert("Файл не найден!");
            e.printStackTrace();
        }catch (IOException e){
            txtAlert("Ошибка чтения!");
            e.printStackTrace();
        }
    }

    private void txtAlert(String errorText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(errorText);
        fileNameLabel.setText("ERROR");
        alert.showAndWait();
    }
    private String printMap(HashMap<String,String> hashMap){
        String result ="";

        for(Map.Entry<String, String> entry: hashMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            result += key + " - "+ value + "\n";
            System.out.println("Ключ: " + key + " - Значение: "+ value);

        }

        return result;
    }

    private HashMap<String, String> shuffle(HashMap<String,String> hashMap){

        HashMap<String, String> result = new HashMap<>();
        Iterator<Map.Entry<String,String>> it = hashMap.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, String> entry = it.next(); //текущий элемент который смотрим


            if (entry.getValue().equals("")){

            }
        }


        return result;
    }
}
