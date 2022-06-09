/**
 * 
 * hecho por MANUEL MATUTE
 */

import javax.swing.*;
import java.awt.event.*;

public class Matute389Proy1 implements ActionListener {

    private final String info = "<html>"
            + "<br>Universidad Tecnologica de Panama<br>"
            + "<br>Ingenieria De Sistemas Computacionales<br><br>"
            + "<br>Licenciatura en Desarrollo de Software<br><br>"
            + "<br>Desarrollo de Software III"
            + "<br>Ricardo Chan<br><br>"
            + "<br>Manuel Matute"
            + "<br>20-70-5389<br><br>"
            + "<br>1LS221"
            + "<br>08 de Junio 2022"
            + "</html>";

    private JFrame minijuego;
    static JLabel portada, etiquetaXY, cN, cS, cE, cO, txt_botonCardumen, txt_numIntentos, txt_XYBotonCardumen,txt_XYBotonPresionado;
    static JButton boton, botonTemporal;
    static JTextField botonCardumen, xCardumen, yCardumen, numIntentos, xBoton, yBoton;
    private int aux, posicionX, posicionY;
    private String avisoPosicion;
    private boolean cardumenSeMovio;

    Peces cardumen;

    public static void main(String args[]) {
        new Matute389Proy1();
    }

    Matute389Proy1() {

        // Creacion de Ventana
        minijuego = new JFrame("GPS De Cardumen");
        minijuego.setSize(930, 600);
        minijuego.setLocationRelativeTo(null);
        minijuego.setLayout(null);
        minijuego.setResizable(false);
        minijuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Crear objeto Peces
        cardumen = new Peces();
        cardumen.moverCardumen();

        aux = 0;

        // Portada
        portada = new JLabel(info);
        portada.setBounds(30, 10, 300, 300);
        minijuego.add(portada);

        // Labels de informacion sobre cardumen e Intentos
        txt_botonCardumen = new JLabel("Boton del Cardumen:");
        txt_botonCardumen.setBounds(70, 453, 140, 30);

        txt_numIntentos = new JLabel("Intentos hasta ahora:");
        txt_numIntentos.setBounds(70, 319, 140, 30);

        txt_XYBotonCardumen = new JLabel("Coordenada Cardumen     x:                    y:");
        txt_XYBotonCardumen.setBounds(40, 410, 230, 30);

        txt_XYBotonPresionado = new JLabel("Coordenada Presionada   x:                    y:");
        txt_XYBotonPresionado.setBounds(40, 370, 230, 30);

        txt_botonCardumen.setVisible(false);
        txt_XYBotonCardumen.setVisible(false);

        minijuego.add(txt_botonCardumen);
        minijuego.add(txt_numIntentos);
        minijuego.add(txt_XYBotonCardumen);
        minijuego.add(txt_XYBotonPresionado);

        // Puntos cardinales
        cN = new JLabel("<html><h2>N</h2></html>");
        cS = new JLabel("<html><h2>S</h2></html>");
        cE = new JLabel("<html><h2>E</h2></html>");
        cO = new JLabel("<html><h2>O</h2></html>");
        cN.setBounds(616, 8, 30, 30);
        cS.setBounds(616, 515, 30, 30);
        cE.setBounds(870, 246, 30, 30);
        cO.setBounds(350, 246, 30, 30);
        minijuego.add(cN);
        minijuego.add(cS);
        minijuego.add(cE);
        minijuego.add(cO);

        // JTextFields
        botonCardumen = new JTextField();
        numIntentos = new JTextField("0");
        xCardumen = new JTextField();
        yCardumen = new JTextField();
        xBoton = new JTextField();
        yBoton = new JTextField();

        numIntentos.setBounds(200, 320, 30, 30);
        botonCardumen.setBounds(200, 450, 100, 40);
        xCardumen.setBounds(200, 410, 50, 30);
        yCardumen.setBounds(270, 410, 50, 30);
        xBoton.setBounds(200, 370, 50, 30);
        yBoton.setBounds(270, 370, 50, 30);

        botonCardumen.setEditable(false);
        numIntentos.setEditable(false);
        xCardumen.setEditable(false);
        yCardumen.setEditable(false);
        xBoton.setEditable(false);
        yBoton.setEditable(false);

        xCardumen.setVisible(false);
        yCardumen.setVisible(false);
        xBoton.setVisible(true);
        yBoton.setVisible(true);
        botonCardumen.setVisible(false);

        minijuego.add(botonCardumen);
        minijuego.add(xCardumen);
        minijuego.add(yCardumen);
        minijuego.add(xBoton);
        minijuego.add(yBoton);
        minijuego.add(numIntentos);

        // Creacion de matriz de botones
        for (int i = 0; i < 100; i++) {
            boton = new JButton();
            boton.setText("<html><h5>" + String.valueOf(i) + "</h5></html>");
            boton.setActionCommand(String.valueOf(i));
            boton.setBounds(400 + 45 * (i % 10), 40 + 45 * (i / 10), 40, 40);

            boton.addActionListener(this);
            minijuego.add(boton);
        }

        // Botones extra
        boton = new JButton();
        boton.setText("Mostrar/Ocultar ubicacion de Cardumen");
        boton.setBounds(60, 500, 270, 35);
        boton.setActionCommand("mostrar_ocultar");
        boton.addActionListener(this);
        minijuego.add(boton);

        // Creacion de etiquetas de botones
        for (int i = 10; i > 0; i--) {
            etiquetaXY = new JLabel(String.valueOf(i));
            etiquetaXY.setBounds(375, 41 + aux, 30, 30);
            minijuego.add(etiquetaXY);

            etiquetaXY = new JLabel(String.valueOf(i));
            etiquetaXY.setBounds(817 - aux, 490, 30, 30);
            minijuego.add(etiquetaXY);

            aux += 45;
        }

        // Mostrar info de objeto Peces
        botonCardumen.setText(String.valueOf(cardumen.getPunto()));
        xCardumen.setText(String.valueOf(cardumen.getPosicionX()));
        yCardumen.setText(String.valueOf(cardumen.getPosicionY()));

        minijuego.setVisible(true);
    }

    // Acciones al presionar botones
    public void actionPerformed(ActionEvent e) {

        // Boton temporal
        botonTemporal = (JButton) e.getSource();

        // Botones
        if (botonTemporal.getActionCommand().equals("mostrar_ocultar")) {
            txt_botonCardumen.setVisible(!txt_botonCardumen.isVisible());
            txt_XYBotonCardumen.setVisible(!txt_XYBotonCardumen.isVisible());
            botonCardumen.setVisible(!botonCardumen.isVisible());
            xCardumen.setVisible(!xCardumen.isVisible());
            yCardumen.setVisible(!yCardumen.isVisible());

        } else {
            // Contador de intentos
            numIntentos.setText(String.valueOf(Integer.parseInt(numIntentos.getText()) + 1));

            // Posicion boton presionado
            posicionX = botonTemporal.getLocation().x;
            posicionY = botonTemporal.getLocation().y;

            xBoton.setText(String.valueOf(posicionX));
            yBoton.setText(String.valueOf(posicionY));

            cardumenSeMovio = false;

            if (posicionX == cardumen.getPosicionX() && posicionY == cardumen.getPosicionY()) {

                JOptionPane.showMessageDialog(null, "Encontraste el Cardumen!","Felicidades", JOptionPane.INFORMATION_MESSAGE);

                //El usuario no puede escoger la X.
                do {
                    aux = JOptionPane.showConfirmDialog(null, "Quiere volver a empezar?","AVISO", JOptionPane.YES_NO_OPTION);
                } while (aux == -1);

                if (aux == 0) {
                    minijuego.dispose();
                    new Matute389Proy1();
                } else {
                    minijuego.dispose();
                    System.exit(0);
                }

            } else {

                for (int i = 0; i < 8; i++) {
                    
                    if(posicionX == cardumen.getPerimetro()[i][0] && posicionY == cardumen.getPerimetro()[i][1]){
                        cardumen.moverCardumen();
                        cardumenSeMovio = true;
                    }

                }

                if (cardumenSeMovio) {
                    
                    // Mostrar info de objeto Peces
                    botonCardumen.setText(String.valueOf(cardumen.getPunto()));
                    xCardumen.setText(String.valueOf(cardumen.getPosicionX()));
                    yCardumen.setText(String.valueOf(cardumen.getPosicionY()));
                    
                    JOptionPane.showMessageDialog(null, "El Cardumen se movio", "Oh No", JOptionPane.WARNING_MESSAGE);

                } else {
                    
                     // Avisos de Coordenada N, S, E, O
                    avisoPosicion = "Debe ir al";
                    if (posicionY > cardumen.getPosicionY()) {
                        avisoPosicion += " Norte";
                    }
                    if (posicionY < cardumen.getPosicionY()) {
                        avisoPosicion += " Sur";
                    }
                    if (posicionX > cardumen.getPosicionX()) {
                        avisoPosicion += " Oeste";
                    }
                    if (posicionX < cardumen.getPosicionX()) {
                        avisoPosicion += " Este";
                    }
                    JOptionPane.showMessageDialog(null, avisoPosicion);
                }
               
            }

        }

    }

}