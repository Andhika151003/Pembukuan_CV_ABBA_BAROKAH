module com.pembukuan_cv_abba_barokah {

    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Database
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    // === OPENS (sesuai struktur folder) ===
    opens com.pembukuan_cv_abba_barokah to javafx.fxml;
    opens com.pembukuan_cv_abba_barokah.Controller to javafx.fxml;
    opens com.pembukuan_cv_abba_barokah.View to javafx.fxml;

    // Model untuk TableView / binding
    opens com.pembukuan_cv_abba_barokah.Model to javafx.base;

    // === EXPORTS (template-style) ===
    exports com.pembukuan_cv_abba_barokah;
}