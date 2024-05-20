module com.csiiproject.m3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires junit;
    requires javafx.media;

    opens com.csiiproject.javaFX to javafx.fxml;
    exports com.csiiproject.javaFX;
}