module com.example.male_javafx {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.kordamp.bootstrapfx.core;

	opens com.example.male_javafx to javafx.fxml;
	exports com.example.male_javafx;
}