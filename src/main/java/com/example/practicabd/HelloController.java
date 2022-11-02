package com.example.practicabd;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class HelloController {

    private ObservableList<ObservableList> data;

    private Connection conexionBBDD;

    @FXML
    private Button ejecutar;
    @FXML
    private TableView tabla;
    @FXML
    private Button ejecutar1;
    @FXML
    private Button ejecutar11;
    @FXML
    private Button ejecutar111;

    @Deprecated
    public void onEjecutarConsulta(ActionEvent actionEvent) throws SQLException {
        String SQL = "SELECT * "
                + "FROM products "
                + "ORDER By productName";

        // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
        ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

        // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
        // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
        while (resultadoConsulta.next()) {
            data.add(new Producto(
                    resultadoConsulta.getString("productCode"),
                    resultadoConsulta.getString("productName"),
                    resultadoConsulta.getString("productLine"),
                    resultadoConsulta.getString("productScale"),
                    resultadoConsulta.getString("productVendor"),
                    resultadoConsulta.getString("productDescription"),
                    resultadoConsulta.getInt("quantityInStock"),
                    resultadoConsulta.getDouble("buyPrice"),
                    resultadoConsulta.getDouble("MSRP"))
            );
            System.out.println("Row [1] added " + resultadoConsulta.toString());
        }
        conexionBBDD.close();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error:" + e.toString());
        conexionBBDD.close();
    } finally {
        return datosResultadoConsulta;
    }
}