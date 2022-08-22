import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
* Así mismo, cuando se realice el registro de los N visitantes, por medio de un cuadro
de dialogo, se le debe mostrar al usuario un resumen del total que debe pagar por
los N visitantes registrados, e indicar el número de personas que son mayores de
edad, menores de edad, el nombre de cada uno de los visitantes y valor de entrada
de cada uno.

* */
public class Principal extends JFrame implements ActionListener {
    private File file;
    private String extension;

    private String nombre, numIdentificacion, edad;
    private int altoJTextField = 20;
    private int anchoJTextField = 20;

    private String valorPagar;
    private JLabel lblTitulo, lblTitulo2;
    private JLabel lblNombre;
    private JLabel lblNumIdentificacion;
    private JLabel lblEdad;
    private JLabel lblCategoriaAfilicion;
    private JLabel lblValorPagar;
    private JLabel lblGuardarEn;

    private JTextField txtNombre, txtNumIdentificacion, txtEdad;

    private JComboBox txtCategoriaAfilicion;
    private JTextArea txtValorPagar;
    private JFileChooser archivoGuardar;
    private JButton btnGuardar, btnLimpiar, btnSalir, btnSeleccionarArchivo, btnResumen, btnCreditos;


    private static Font fuenteTitulos = new Font("Arial", Font.BOLD, 20);
    private static Font fuenteTextos = new Font("Arial", Font.PLAIN, 13);


    GridBagConstraints constraints;

    /*TEMPORAL*/
    String nombreTemp = "";
    String valorTemp = "";
    String edadTemp = "";


    public Principal() {
        lblTitulo = new JLabel("Visitantes Parque Central", SwingConstants.CENTER);
        lblTitulo2 = new JLabel("Compensar", SwingConstants.CENTER);
        lblNombre = new JLabel("Nombre del Visitante");
        lblNumIdentificacion = new JLabel("Numero de Identificacion");
        lblEdad = new JLabel("Edad del Visitante");
        lblCategoriaAfilicion = new JLabel("Categoria de Afilicion");
        lblValorPagar = new JLabel("Valor a Pagar");
        lblGuardarEn = new JLabel("<html>Guardar en: <br/>\"[Sin Seleccionar]\"</html>");


        txtNombre = new JTextField();
        txtNumIdentificacion = new JTextField();
        txtEdad = new JTextField();
        txtCategoriaAfilicion = new JComboBox();
        txtValorPagar = new JTextArea();
        archivoGuardar = new JFileChooser();
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");
        btnResumen = new JButton("Resumen");
        btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
        btnCreditos = new JButton("Creditos");

        constraints = new GridBagConstraints();

        //setLayout(new GridLayout(8, 2, 10, 10));f
        setLayout(new GridBagLayout());

        /* TITULO */

        ubicarGrilla(0, 0, 2);
        constraints.fill = GridBagConstraints.BOTH;

        lblTitulo.setFont(fuenteTitulos);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setVerticalAlignment(SwingConstants.CENTER);
        add(lblTitulo, constraints);

        ubicarGrilla(0, 1, 2);

        lblTitulo2.setFont(fuenteTitulos);
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo2.setVerticalAlignment(SwingConstants.CENTER);
        add(lblTitulo2, constraints);


        /* NOMBRE */

        ubicarGrilla(0, 2, 1);

        lblNombre.setFont(fuenteTextos);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNombre, constraints);

        /* INPUT NOMBRE */

        ubicarGrilla(1, 2, 1);

        txtNombre.setFont(fuenteTextos);
        //txtNombre.setSize(anchoJTextField, altoJTextField);
        txtNombre.setPreferredSize(new Dimension(anchoJTextField, altoJTextField));
        add(txtNombre, constraints);


        /*NUMERO DE IDENTIFICACION */
        ubicarGrilla(0, 3, 1);

        lblNumIdentificacion.setFont(fuenteTextos);
        lblNumIdentificacion.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNumIdentificacion, constraints);

        /*INPUT NUMERO DE IDENTIFICACION */

        ubicarGrilla(1, 3, 1);

        txtNumIdentificacion.setFont(fuenteTextos);
        txtNumIdentificacion.setSize(anchoJTextField, altoJTextField);
        add(txtNumIdentificacion, constraints);

        /*EDAD */

        ubicarGrilla(0, 4, 1);

        lblEdad.setFont(fuenteTextos);
        lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblEdad, constraints);

        /*INPUT EDAD */

        ubicarGrilla(1, 4, 1);

        txtEdad.setFont(fuenteTextos);
        add(txtEdad, constraints);

        /*CATEGORIA DE AFILICION */

        ubicarGrilla(0, 5, 1);

        lblCategoriaAfilicion.setFont(fuenteTextos);
        lblCategoriaAfilicion.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCategoriaAfilicion, constraints);

        /*INPUT CATEGORIA DE AFILICION */

        ubicarGrilla(1, 5, 1);

        txtCategoriaAfilicion.addItem("Categoria A");
        txtCategoriaAfilicion.addItem("Categoria B");
        txtCategoriaAfilicion.addItem("Categoria C");
        txtCategoriaAfilicion.addItem("No aplica");
        add(txtCategoriaAfilicion, constraints);

        /*VALOR A PAGAR */

        ubicarGrilla(0, 6, 1);

        lblValorPagar.setFont(fuenteTextos);
        lblValorPagar.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblValorPagar, constraints);

        /*INPUT VALOR A PAGAR */

        ubicarGrilla(1, 6, 1);

        txtValorPagar.setFont(fuenteTextos);
        txtValorPagar.setEditable(false);
        add(txtValorPagar, constraints);

        /*Archivo Guardar */

        ubicarGrilla(0, 7, 1);

        lblGuardarEn.setFont(fuenteTextos);
        lblGuardarEn.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblGuardarEn, constraints);

        /*INPUT Archivo Guardar */

        ubicarGrilla(1, 7, 1);

        archivoGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        archivoGuardar.setDialogTitle("Guardar");
        /*add(archivoGuardar, constraints);*/
        btnSeleccionarArchivo.addActionListener(this);
        add(btnSeleccionarArchivo, constraints);

        /*Boton Guardar */

        ubicarGrilla(0, 8, 1);

        btnGuardar.setFont(fuenteTextos);
        btnGuardar.setSize(100, 100);
        btnGuardar.addActionListener(this);
        add(btnGuardar, constraints);

        /*Boton Resumen */

        ubicarGrilla(1, 8, 1);

        btnResumen.setFont(fuenteTextos);
        btnResumen.addActionListener(this);
        add(btnResumen, constraints);

        /*Boton Limpiar */

        ubicarGrilla(0, 9, 1);

        btnLimpiar.setFont(fuenteTextos);
        btnLimpiar.addActionListener(this);
        add(btnLimpiar, constraints);

        /*Boton Salir */

        ubicarGrilla(1, 9, 1);

        btnSalir.setFont(fuenteTextos);
        btnSalir.addActionListener(this);
        add(btnSalir, constraints);

        /*Boton Creditos */

        ubicarGrilla(0, 10, 2);
        btnCreditos.setFont(fuenteTextos);
        btnCreditos.addActionListener(this);
        add(btnCreditos, constraints);

        setTitle("Visitantes Parque Central Compensar");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void ubicarGrilla(int a, int b, int c) {
        constraints.gridx = a;
        constraints.gridy = b;
        constraints.gridwidth = c;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
    }

    public double calcularValorPagar() {
        int edad = Integer.parseInt(this.txtEdad.getText());
        String tipoAfiliacion = null;
        try {
            tipoAfiliacion = this.txtCategoriaAfilicion.getSelectedItem().toString();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria de afiliacion");
        }


        if (edad < 18) {
            return 5000;
        }

        return switch (tipoAfiliacion) {
            case "Categoria A" -> 30000 - (30000 * 0.15);
            case "Categoria B" -> 30000 - (30000 * 0.30);
            case "Categoria C" -> 30000 - (30000 * 0.50);
            default -> 30000;
        };
    }

    public static void main(String[] args) {
        Principal p = new Principal();
    }

    public boolean validarDatos() {
        boolean valido = true;

        String regexName = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        //String regexName = "^[a-zA-Z].*[\s\.]*$";
        String regexNumIdentificacion = "^[0-9]{1,10}$";
        String regexEdad = "^[0-9]{1,3}$";

        String name = this.txtNombre.getText();
        String numIdentificacion = this.txtNumIdentificacion.getText();
        String edad = this.txtEdad.getText();
        JComboBox categoriaAfilicion = this.txtCategoriaAfilicion;

        Boolean validName = txtNombre.getText().matches(regexName);
        Boolean validNumIdentificacion = txtNumIdentificacion.getText().matches(regexNumIdentificacion);
        Boolean validEdad = txtEdad.getText().matches(regexEdad);

        if (!validName && name.length() > 0) {
            valido = false;
            JOptionPane.showMessageDialog(null, "El nombre debe comenzar con mayúscula y solo puede contener letras");
        }

        if (!validNumIdentificacion && numIdentificacion.length() > 0) {
            valido = false;
            JOptionPane.showMessageDialog(null, "El número de identificación debe contener 10  y solo puede contener números");
        }

        if (!validEdad && edad.length() > 0) {
            valido = false;
            JOptionPane.showMessageDialog(null, "La edad debe contener 3 dígitos y solo puede contener números");
        }
        return valido;
    }

    public boolean ingresoValores() {
        String name = this.txtNombre.getText();
        String numIdentificacion = this.txtNumIdentificacion.getText();
        String edad = this.txtEdad.getText();
        JComboBox categoriaAfilicion = this.txtCategoriaAfilicion;

        JFrame frame = new JFrame();
        frame.setTitle("Faltan campos por ingresar");
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);


        if (edad.equals("") || name.equals("") || numIdentificacion.equals("") || categoriaAfilicion.getSelectedItem().equals("")) {
            frame.setVisible(true);
        }
        JLabel lblInformacion, lblEdad, lblName, lblNumIdentificacion, lblCategoriaAfilicion;
        lblInformacion = new JLabel("Debe ingresar información en los siguientes campos:\n\n");
        lblInformacion.setBounds(10, 10, 300, 20);
        frame.add(lblInformacion);


        boolean valido = true;

        if (edad.equals("")) {
            valido = false;
            //JOptionPane.showMessageDialog(null, "Debe ingresar una edad");
            lblEdad = new JLabel("• Edad del Visitante");
            lblEdad.setBounds(10, 60, 300, 20);
            frame.add(lblEdad);
        }

        if (name.equals("")) {
            valido = false;
            //JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            lblName = new JLabel("• Nombre del Visitante");
            lblName.setBounds(10, 80, 300, 20);
            frame.add(lblName);
        }

        if (numIdentificacion.equals("")) {
            valido = false;
            //JOptionPane.showMessageDialog(null, "Debe ingresar un numero de identificacion");
            lblNumIdentificacion = new JLabel("• Número de Identificación");
            lblNumIdentificacion.setBounds(10, 100, 300, 20);
            frame.add(lblNumIdentificacion);
        }

        if (categoriaAfilicion.getSelectedItem().equals("")) {
            valido = false;
            //JOptionPane.showMessageDialog(null, "Debe seleccionar una categoria de afilicion");
            lblCategoriaAfilicion = new JLabel("• Categoria de Afilicion");
            lblCategoriaAfilicion.setBounds(10, 120, 300, 20);
            frame.add(lblCategoriaAfilicion);

        }
        return valido;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            ejecutarBtnGuardar();
            //ejecutarBtnLimpiar();
        }
        if (e.getSource() == btnResumen) {
            ejecutarBtnResumen();
        }
        if (e.getSource() == btnLimpiar) {
            ejecutarBtnLimpiar();
        }
        if (e.getSource() == btnSalir) {
            ejecutarBtnSalir();
        }
        if (e.getSource() == btnSeleccionarArchivo) {
            ejecutarBtnSeleccionarArchivo();
        }
        if (e.getSource() == btnCreditos) {
            ejecutarBtnCreditos();
        }
    }

    private void ejecutarBtnCreditos() {
        InformacionCreditos creditos = new InformacionCreditos();
    }

    public void ejecutarBtnGuardar() {
        if (this.extension == null || !this.extension.equals("txt")) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!ingresoValores()) return;
        if (!validarDatos()) return;

        valorPagar = String.valueOf(calcularValorPagar());
        txtValorPagar.setText(valorPagar);

        try {
            FileWriter fileWriter = new FileWriter(file, true); // Segundo argumento: append = true para agregar al final del archivo.
            fileWriter.write("Nombre: " + txtNombre.getText() + "\n");
            fileWriter.write("Numero de Identificacion: " + txtNumIdentificacion.getText() + "\n");
            fileWriter.write("Edad: " + txtEdad.getText() + "\n");
            fileWriter.write("Categoria de Afilicion: " + txtCategoriaAfilicion.getSelectedItem() + "\n");
            fileWriter.write("Valor a Pagar: " + txtValorPagar.getText() + "\n");
            fileWriter.write("-\n");
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Información guardada correctamente", "Archivo guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void ejecutarBtnResumen() {
        if (this.extension == null || !this.extension.equals("txt")) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File archivo = new File(file.getAbsolutePath());
        int i = 0;
        ArrayList contenido = new ArrayList<String>();
        ArrayList nombresVisitantes = new ArrayList<String>();
        ArrayList valoresPagados = new ArrayList<String>();
        ArrayList edades = new ArrayList<String>();
        Scanner scan;
        try {
            /*Método que retorne en el archivo file todo el contenido.*/
            scan = new Scanner(archivo);
            while (scan.hasNextLine()) {
                contenido.add(i, scan.nextLine());
                i++;
            }
            i = 0;
            while (i < contenido.size()) {
                /* Método que retorne el arreglo de los valores según la propiedad (Nombre, pago, edad)
                * valoresArreglo(contenido, "Nombre");
                * */
                if (contenido.get(i).toString().contains("Nombre")) {
                    //System.out.println("Se ha encontrado un nombre");
                    int i2 = 8;
                    while (i2 < contenido.get(i).toString().length()) {
                        //System.out.println(contenido.get(i).toString());
                        //System.out.println(contenido.get(i).toString().charAt(i2));
                        nombreTemp = nombreTemp + contenido.get(i).toString().charAt(i2);
                        i2++;
                    }
                    nombresVisitantes.add(nombreTemp);
                    nombreTemp = "";
                }
                if (contenido.get(i).toString().contains("Valor a Pagar")) {
                    //System.out.println("Se ha encontrado un valor a pagar");
                    int i2 = 14;
                    while (i2 < contenido.get(i).toString().length()) {
                        //System.out.println(contenido.get(i).toString());
                        //System.out.println(contenido.get(i).toString().charAt(i2));
                        valorTemp = valorTemp + contenido.get(i).toString().charAt(i2);
                        i2++;
                    }
                    valoresPagados.add(valorTemp);
                    //System.out.println(valorTemp);
                    valorTemp = "";
                }
                if (contenido.get(i).toString().contains("Edad")) {
                    int i2 = 5;
                    while (i2 < contenido.get(i).toString().length()) {
                        edadTemp = edadTemp + contenido.get(i).toString().charAt(i2);
                        i2++;
                    }
                    edades.add(edadTemp.replaceAll("\\s", ""));
                    //System.out.println(edadTemp);
                    edadTemp = "";
                }
                i++;
            }


            /*i = 0;
            while (i < contenido.size()) {
                System.out.println(contenido.get(i));
                i++;
            }*/
            /*while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("El valor a pagar es: " + totalPagar(valoresPagados));
        System.out.println("El número de personas mayores es: " + mayoresEdad(edades));
        System.out.println("El número de personas menores es: " + menoresEdad(edades));

        for (int i3 = 0; i3 < nombresVisitantes.size(); i3++) {
            System.out.println("Nombre: " + nombresVisitantes.get(i3).toString());
            System.out.println("Valor a Pagar: " + valoresPagados.get(i3).toString());
            System.out.println("-");
        }

        //JOptionPane.showMessageDialog(null, "Resumen de visitantes \n Prueba", "Resumen", JOptionPane.INFORMATION_MESSAGE);
        InformacionResumen informacionResumen = new InformacionResumen(totalPagar(valoresPagados), mayoresEdad(edades), menoresEdad(edades), nombresVisitantes, valoresPagados);

    }

    public void ejecutarBtnLimpiar() {
        txtNombre.setText("");
        txtNumIdentificacion.setText("");
        txtEdad.setText("");
        txtCategoriaAfilicion.setSelectedIndex(0);
        txtValorPagar.setText("");
        lblGuardarEn.setText("<html>Guardar en: <br/>\"[Sin Seleccionar]\"</html>");
    }

    public void ejecutarBtnSalir() {
        System.exit(0);
        //this.dispose();
    }

    public void ejecutarBtnSeleccionarArchivo() {
        String folder = System.getProperty("user.dir");

        JFileChooser fileChooser = new JFileChooser(folder);
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Archivos de Texto (.TXT)", "txt");
        fileChooser.setFileFilter(fileNameExtensionFilter);

        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            this.file = fileChooser.getSelectedFile();
            System.out.println("Archivo Seleccionado: " + file.getAbsolutePath());

            String fileName = file.toString();
            int index = fileName.lastIndexOf(".");
            this.extension = fileName.substring(index + 1);
            System.out.println("Extension: " + extension);
            try {
                if (!this.extension.equals("txt")) {
                    JOptionPane.showMessageDialog(this, "El archivo no es un archivo de texto (.TXT)", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new Exception("El archivo no es un archivo de texto (.TXT)");

                } else {
                    lblGuardarEn.setText("<html>Guardar en: <br/>" + file.getAbsolutePath() + "</html>");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            System.out.println("No se selecciono ningun archivo");
        }
    }
    public float totalPagar(ArrayList valoresPagados) {
        float total = 0;
        for (int i = 0; i < valoresPagados.size(); i++) {
            total = total + Float.parseFloat(valoresPagados.get(i).toString());
        }
        return total;
    }

    public int mayoresEdad(ArrayList personasMayores){
        int mayores = 0;
        for (int i = 0; i < personasMayores.size(); i++) {
            if (Integer.parseInt(personasMayores.get(i).toString()) >= 18) {
                mayores++;
            }
        }
        return mayores;
    }

    public int menoresEdad(ArrayList personasMenores){
        int menores = 0;
        for (int i = 0; i < personasMenores.size(); i++) {
            if (Integer.parseInt(personasMenores.get(i).toString()) < 18) {
                menores++;
            }
        }
        return menores;
    }

}

class InformacionCreditos extends JFrame {
    private JLabel lblDescripcion, lblNombre, lblCorreo;

    public InformacionCreditos() {
        super("Créditos del Trabajo");
        setSize(400, 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);

        lblDescripcion = new JLabel("Trabajo elaborado por el estudiante:", SwingConstants.CENTER);
        lblDescripcion.setBounds(10, 10, 400, 20);
        add(lblDescripcion);
        lblNombre = new JLabel("Christian Camilo Rubio Ortiz", SwingConstants.CENTER);
        lblNombre.setBounds(10, 30, 400, 20);
        add(lblNombre);
        lblCorreo = new JLabel("ccrubio@ucompensar.edu.co", SwingConstants.CENTER);
        lblCorreo.setBounds(10, 50, 400, 20);
        add(lblCorreo);
    }
}
class InformacionResumen extends JFrame {
      private JLabel lblTotalPagar, lblMayores, lblMenores;
      private JTextArea txtInformacion;
      private static JScrollPane scrollPane;

    public InformacionResumen(float totalPagar, int mayoresEdad, int menoresEdad, ArrayList nombresVisitantes, ArrayList valoresPagados) {
        super("Resumen de Visitantes");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);



        lblTotalPagar = new JLabel("Total a Pagar: " + totalPagar, SwingConstants.CENTER);
        lblTotalPagar.setBounds(10, 10, 380, 20);
        //lblTotalPagar.setSize(300, 20);
        add(lblTotalPagar);


        lblMayores = new JLabel("Mayores de Edad: " + mayoresEdad, SwingConstants.CENTER);
        lblMayores.setBounds(10, 30, 380, 20);
        //lblMayores.setSize(300, 20);
        add(lblMayores);


        lblMenores = new JLabel("Menores de Edad: " + menoresEdad, SwingConstants.CENTER);
        lblMenores.setBounds(10, 50, 380, 20);
        //lblMenores.setSize(300, 20);
        add(lblMenores);

        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        txtInformacion = new JTextArea();
        txtInformacion.setBounds(10, 70, 370, 130);
        //txtInformacion.setSize(300, 80);
        //txtInformacion.add(new JScrollBar());
        txtInformacion.setEditable(false);

        for (int i = 0; i < nombresVisitantes.size(); i++) {
            txtInformacion.append("Nombre: " + nombresVisitantes.get(i).toString() + "\n");
            txtInformacion.append("Valor a Pagar: " + valoresPagados.get(i).toString() + "\n");
            txtInformacion.append("-\n");
        }


        scrollPane.setBounds(10, 70, 370, 130);
        //scrollPane.setSize(300, 80);
        scrollPane.setViewportView(txtInformacion);
        add(scrollPane);
        //add(txtInformacion);


    }
}
