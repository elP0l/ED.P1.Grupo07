module com.mycompany.ed.p1.grupo07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ed.p1.grupo07 to javafx.fxml;
    exports com.mycompany.ed.p1.grupo07;
}
