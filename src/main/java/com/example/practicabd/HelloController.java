package com.example.practicabd;

import com.example.practicabd.modulos.Producto;
import com.example.practicabd.dao.ProductoDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.sql.Connection;

public class HelloController {
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    ProductoDAO productDAO = new ProductoDAO();
    Producto productoAux;
    ObservableList <Producto> datos;
    @FXML
    private Button ejecutar;
    @FXML
    private Button ejecutar1;
    @FXML
    private Button ejecutar11;
    @FXML
    private Button ejecutar111;
    @FXML
    private TextField nombre;
    @FXML
    private TextField vendedor;
    @FXML
    private TextField escala;
    @FXML
    private TextField linea;
    @FXML
    private TextField id;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField stock;
    @FXML
    private TextField precioCompra;
    @FXML
    private TextField precioVenta;
    @FXML
    private TableColumn idTabla;
    @FXML
    private TableColumn nombreTabla;
    @FXML
    private TableColumn lineaTabla;
    @FXML
    private TableColumn escalaTabla;
    @FXML
    private TableColumn vendedorTabla;
    @FXML
    private TableColumn descripcionTabla;
    @FXML
    private TableColumn stockTabla;
    @FXML
    private TableColumn precioCompraTabla;
    @FXML
    private TableColumn precioVentaTabla;
    @FXML
    private TableView <Producto> tvDatos;
    public void cargarDatos() {
        datos = productDAO.obtenerProductos();

        idTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productCode"));
        nombreTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productName"));
        lineaTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productLine"));
        escalaTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productScale"));
        vendedorTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productVendor"));
        descripcionTabla.setCellValueFactory(new PropertyValueFactory<Producto, String>("productDescription"));
        stockTabla.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("quantityInStock"));
        precioCompraTabla.setCellValueFactory(new PropertyValueFactory<Producto, Double>("buyPrice"));
        precioVentaTabla.setCellValueFactory(new PropertyValueFactory<Producto, Double>("MSRP"));

        tvDatos.setItems(datos);
    }

    /*public void intiliaze() {
        cargarDatos();
    }*/

    @FXML
    public void onEjecutarConsulta(ActionEvent actionEvent) {
        cargarDatos();

    }


}