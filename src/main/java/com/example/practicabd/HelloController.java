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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.StringTokenizer;

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

    public void initialize() {
        cargarGestorDobleCLick();
    }

    public boolean darDeAlta(Producto producto) {
        int registrosAfectadosConsulta = 0;

        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "INSERT INTO products ("
                    + " productCode ,"
                    + " productName ,"
                    + " productLine ,"
                    + " productScale ,"
                    + " productVendor ,"
                    + " productDescription ,"
                    + " quantityInStock ,"
                    + " buyPrice ,"
                    + " MSRP  )"
                    + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setString(1, id.getText());
            st.setString(2, nombre.getText());
            st.setString(3, linea.getText());
            st.setString(4, escala.getText());
            st.setString(5, vendedor.getText());
            st.setString(6, descripcion.getText());
            st.setInt(7, (Integer.parseInt(stock.getText())));
            st.setDouble(8, (Double.parseDouble(precioCompra.getText())));
            st.setDouble(9, (Double.parseDouble(precioVenta.getText())));

            // Ejecutamos la consulta preparada (con las ventajas de seguridad y velocidad en el servidor de BBDD
            // nos devuelve el número de registros afectados. Al ser un Insert nos debe devolver 1 si se ha hecho correctamente
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }

    private void cargarGestorDobleCLick () {
        tvDatos.setRowFactory(tv -> {
            TableRow<Producto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    id.setText(row.getItem().getProductCode());
                    linea.setText(row.getItem().getProductLine());
                    nombre.setText(row.getItem().getProductName());
                    escala.setText(row.getItem().getProductScale());
                    vendedor.setText(row.getItem().getProductVendor());
                    descripcion.setText(row.getItem().getProductDescription());
                    precioCompra.setText(String.valueOf(row.getItem().getBuyPrice()));
                    precioVenta.setText(String.valueOf(row.getItem().getMSRP()));
                    stock.setText(String.valueOf(row.getItem().getQuantityInStock()));
                }
            });
            return row;
        });
    }

    // Actualizar un producto existente
    //
    public Boolean actualizarProducto(Producto producto) {

        int registrosAfectadosConsulta = 0;

        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "UPDATE products "
                    + " SET "
                    + " productName = ? ,"
                    + " productLine = ? ,"
                    + " productScale = ? ,"
                    + " productVendor = ? ,"
                    + " productDescription = ? ,"
                    + " quantityInStock = ? ,"
                    + " buyPrice = ? ,"
                    + " MSRP = ?  "
                    + " WHERE productCode = ? ";

            PreparedStatement st = conexionBBDD.prepareStatement(SQL);
            st.setString(1, nombre.getText());
            st.setString(2, linea.getText());
            st.setString(3, escala.getText());
            st.setString(4, vendedor.getText());
            st.setString(5, descripcion.getText());
            st.setInt(6, (Integer.parseInt(stock.getText())));
            st.setDouble(7, (Double.parseDouble(precioCompra.getText())));
            st.setDouble(8, (Double.parseDouble(precioVenta.getText())));
            st.setString(9, id.getText());

            // Ejecutamos la consulta preparada (con las ventajas de seguridad y velocidad en el servidor de BBDD
            // nos devuelve el número de registros afectados. Al ser un Update nos debe devolver 1 si se ha hecho correctamente
            registrosAfectadosConsulta = st.executeUpdate();
            st.close();
            conexionBBDD.close();

            if (registrosAfectadosConsulta == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
    }

    public boolean borrarProducto(Producto producto) {
        int registrosAfectadosConsulta = 0;
        Alert alert;

        try {

            if (tvDatos.getSelectionModel().getSelectedItem() != null) {
                Producto producto1 = tvDatos.getSelectionModel().getSelectedItem();
                System.out.println(producto1.getProductCode());

                alert = new Alert(Alert.AlertType.CONFIRMATION, "¿ Desea borrar el producto con el código '"
                        + producto1.getProductCode() + "' ?.", ButtonType.YES, ButtonType.NO);

                // Nos conectamos
                conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
                String SQL = "DELETE FROM products "
                        + " WHERE productCode = ? ";

                PreparedStatement st = conexionBBDD.prepareStatement(SQL);

                st.setString(1, producto1.getProductCode());

                // Ejecutamos la consulta preparada (con las ventajas de seguridad y velocidad en el servidor de BBDD
                // nos devuelve el número de registros afectados. Al ser un Delete nos debe devolver 1 si se ha hecho correctamente
                registrosAfectadosConsulta = st.executeUpdate();
                st.close();
                conexionBBDD.close();

                if (registrosAfectadosConsulta == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "No has seleccionado ninguna columna de la tabla");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            return false;
        }
        return false;
    }



    @FXML
    public void onEjecutarConsulta(ActionEvent actionEvent) {
        cargarDatos();
    }


    @FXML
    public void btnAniadir(ActionEvent actionEvent) {
        darDeAlta(productoAux);
        cargarDatos();
    }

    @FXML
    public void btnActualizar(ActionEvent actionEvent) {
        actualizarProducto(productoAux);
        cargarDatos();
    }

    @FXML
    public void btnBorrar(ActionEvent actionEvent) {

Producto p = tvDatos.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Quieres borrar el producto "+ p.getProductCode()+" ?");
        Optional<ButtonType> action = alert.showAndWait();



        if (action.get() == ButtonType.OK) {
            borrarProducto(productoAux);
            cargarDatos();
        }


    }
}