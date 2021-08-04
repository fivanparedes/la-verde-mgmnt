package edu.unam.vistas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class VistaUtils {
    public static void mostrarAlerta(AlertType at, String t, String h, String c) {
        Alert a = new Alert(at);
        a.setTitle(t);
        a.setHeaderText(h);
        a.setContentText(c);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.show();
    }
}
