import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Vehiculos {
    private JTextArea Placa;
    private JTextArea Marca;
    private JTextArea Cilindraje;
    private JTextArea TipoCombustible;
    private JTextArea Color;
    private JTextArea Propietario;
    private JTextField IngresoPlaca;
    private JTextField IngresoMarca;
    private JTextField IngresoCilindraje;
    private JTextField IngresoTipoCombustible;
    private JTextField IngresoColor;
    private JTextField IngresoPropietario;
    private JButton IngresoDatos;
    private JButton LimpiarFormulario;
    public JPanel PanelVehiculos;
    private JLabel SalidaMensaje;
    private JButton BusquedaDatos;
    String placa, marca, cilindraje, tipoCombustible, color, propietario;
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getCilindraje() {
        return cilindraje;
    }
    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }
    public String getTipoCombustible() {
        return tipoCombustible;
    }
    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getPropietario() {
        return propietario;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    public Vehiculos() {
            IngresoDatos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String url = "jdbc:mysql://localhost:3306/basevehiculos";
                    String usuario = "root";
                    String contraseña = "";
                    Vehiculos vehiculos = new Vehiculos();
                    placa = IngresoPlaca.getText();
                    marca = IngresoMarca.getText();
                    cilindraje = IngresoCilindraje.getText();
                    tipoCombustible = IngresoTipoCombustible.getText();
                    color = IngresoColor.getText();
                    propietario = IngresoPropietario.getText();
                    vehiculos.setPlaca(placa);
                    vehiculos.setMarca(marca);
                    vehiculos.setCilindraje(cilindraje);
                    vehiculos.setTipoCombustible(tipoCombustible);
                    vehiculos.setColor(color);
                    vehiculos.setPropietario(propietario);
                    String query = "INSERT INTO vehiculos (placa, marca, cilindraje, tipo_combustible, color, propietario) VALUES (?, ?, ?, ?, ?, ?)";
                    try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
                        System.out.println("Conexión realizada con éxito");
                        PreparedStatement cadenaPreparada = connection.prepareStatement(query);
                        cadenaPreparada.setString(1, placa);
                        cadenaPreparada.setString(2, marca);
                        cadenaPreparada.setString(3, cilindraje);
                        cadenaPreparada.setString(4, tipoCombustible);
                        cadenaPreparada.setString(5, color);
                        cadenaPreparada.setString(6, propietario);
                        cadenaPreparada.executeUpdate();
                        SalidaMensaje.setText("Datos ingresados con éxito");
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                }
            });
            LimpiarFormulario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    IngresoPlaca.setText("");
                    IngresoMarca.setText("");
                    IngresoCilindraje.setText("");
                    IngresoTipoCombustible.setText("");
                    IngresoColor.setText("");
                    IngresoPropietario.setText("");
                }
            });
        BusquedaDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Busqueda().PanelBusqueda);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.pack();
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(BusquedaDatos)).dispose();
            }
        });
    }
}
