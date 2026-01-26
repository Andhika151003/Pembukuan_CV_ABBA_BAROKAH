module com.pembukuan_cv_abba_barokah {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.pembukuan_cv_abba_barokah.Controller to javafx.fxml;
    opens com.pembukuan_cv_abba_barokah to javafx.graphics, javafx.fxml;
}