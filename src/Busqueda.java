import javax.swing.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Busqueda {
    public JPanel PanelBusqueda;
    private JTextArea BusquedaPlaca;
    private JTextField IngresoPlaca;
    private JButton BusquedaxPlaca;
    private JButton Regresar;
    private JTextArea DatosVehiculo;
    private JTextArea InformacionPlaca;
    private JTextArea InformacionMarca;
    private JTextArea InformacionCilindraje;
    private JTextArea InformacionCombustible;
    private JTextArea InformacionColor;
    private JTextArea InformacionPropietario;
    private JLabel salidaPlaca;
    private JLabel salidaMarca;
    private JLabel salidaCilindraje;
    private JLabel salidaCombustible;
    private JLabel salidaColor;
    private JLabel salidaPropietario;
    public Busqueda() {
        BusquedaxPlaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/basevehiculos";
                String usuario = "root";
                String contraseña = "";
                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("Conexión exitosa");
                    String query = "SELECT * FROM vehiculos WHERE placa='" + IngresoPlaca.getText()+"'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        salidaPlaca.setText(resultSet.getString("placa"));
                        salidaMarca.setText(resultSet.getString("marca"));
                        salidaCilindraje.setText(resultSet.getString("cilindraje"));
                        salidaCombustible.setText(resultSet.getString("tipo_combustible"));
                        salidaColor.setText(resultSet.getString("color"));
                        salidaPropietario.setText(resultSet.getString("propietario"));
                    }
                } catch (SQLException E){
                    System.out.println(E.getMessage());
                }
            }
        });
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame();
                frame2.setTitle("Volver");
                frame2.setContentPane(new Vehiculos().PanelVehiculos);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setSize(800, 600);
                frame2.pack();
                frame2.setVisible(true);
            }
        });
    }
}
