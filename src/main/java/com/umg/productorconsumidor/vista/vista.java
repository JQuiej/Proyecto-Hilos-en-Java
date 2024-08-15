
package com.umg.productorconsumidor.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;



public class vista extends javax.swing.JFrame {

private final Queue<JLabel> clientes = new LinkedList<>();
    private static final int capacidad = 3;
    private JLabel sillaBar = new JLabel();
    private JLabel[] sillas = new JLabel[3];
    private JLabel[] clientesLabels = new JLabel[3];
    private Timer timer;
    private int clientesEnFila = 0;
    private volatile boolean running = true;

    public vista() {
        initComponents();
        this.setTitle("BARBERIA UMG");
        this.setLocationRelativeTo(null);
        INICIAR.addActionListener(e -> iniciarAnimacion());
        PARAR.addActionListener(e -> detenerAnimacion() );
        sillas[0] = s3;
        sillas[1] = s4;
        sillas[2] = s5;

        sillaBar = sb;
        
        clientesLabels[0] = client4;
        clientesLabels[1] = client3;
        clientesLabels[2] = client5;
    }

    private void iniciarAnimacion() {
        new Thread(() -> {
            try {
                producir();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                consumir();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    
    private void detenerAnimacion() {
    running = false; 
    }

    public synchronized void producir() throws InterruptedException {
        while (running) {
            while (clientesEnFila == capacidad) {
                wait();  // Espera si la fila está llena
            }

            int index = clientesEnFila;
            JLabel clienteLabel = clientesLabels[index];
            clientes.add(clienteLabel);
            clientesEnFila++;
            System.out.println("movemos a cola "  );
            moverCliente(clienteLabel, sillas[index]);

            notify();  // Notifica al consumidor que hay un cliente disponible
            Thread.sleep(3000);
        }
    }

    public synchronized void consumir() throws InterruptedException {
        while (running) {
            while (clientesEnFila == 0) {
                wait();  // Espera si no hay clientes en la fila
            }

            JLabel atendido = clientes.poll();
            clientesEnFila--;

            moverCliente(atendido, sillaBar); // Mueve el cliente a la silla del barbero

            // Espera un tiempo mientras el cliente es atendido
            Thread.sleep(3000);

            // Después de ser atendido, regresa el cliente a su posición inicial
            resetCliente(atendido);
            notify();  // Notifica al productor que hay espacio disponible en la fila
            Thread.sleep(3000);
        }
    }

    private void moverCliente(JLabel cliente, JLabel destino) {
        int startX = cliente.getX();
        int startY = cliente.getY();
        
        System.out.println("x " +startX +" y " + startY );

        int endX = destino.getX();
        int endY = destino.getY()-50;

        int delay = 10;
        int step = 5;

    timer = new Timer(delay, new ActionListener() {
        int x = startX;
        int y = startY;
        int tolerance = 1;  // Reduce la tolerancia para detener el movimiento más cerca del destino

        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < endX) x += step;
            if (y < endY) y += step;

            if (x > endX) x -= step;  // Ajusta si pasa el destino
            if (y > endY) y -= step;

            cliente.setLocation(x, y);

            if (Math.abs(x - endX) <= tolerance && Math.abs(y - endY) <= tolerance) {
               
                cliente.setLocation(endX, endY);  // Asegura que el JLabel llegue exactamente a la posición final
                timer.stop();
            }
        }
    });

    timer.start();
}

    private void resetCliente(JLabel cliente) {
        int originalX = 280; // Posición X original (puedes ajustar según tu diseño)
        int originalY = 360; // Posición Y original (puedes ajustar según tu diseño)

        cliente.setLocation(originalX, originalY);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vista().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        client4 = new javax.swing.JLabel();
        ador = new javax.swing.JLabel();
        PARAR = new javax.swing.JButton();
        INICIAR = new javax.swing.JButton();
        client3 = new javax.swing.JLabel();
        client5 = new javax.swing.JLabel();
        s3 = new javax.swing.JLabel();
        s5 = new javax.swing.JLabel();
        s4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 220));
        setName("Barberia"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1160, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        client4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hombre.png"))); // NOI18N
        getContentPane().add(client4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));

        ador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barbero (1).png"))); // NOI18N
        getContentPane().add(ador, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        PARAR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        PARAR.setText("PARAR");
        getContentPane().add(PARAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 150, 60));

        INICIAR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        INICIAR.setText("INICIAR");
        getContentPane().add(INICIAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 115, 150, 60));

        client3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hombre.png"))); // NOI18N
        getContentPane().add(client3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));

        client5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hombre.png"))); // NOI18N
        getContentPane().add(client5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, -1, -1));

        s3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/silla.png"))); // NOI18N
        getContentPane().add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 510, -1, -1));

        s5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/silla.png"))); // NOI18N
        getContentPane().add(s5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, -1, -1));

        s4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/silla.png"))); // NOI18N
        getContentPane().add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barbero.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, -1, -1));

        sb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sillabar.png"))); // NOI18N
        getContentPane().add(sb, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/4453.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton INICIAR;
    private javax.swing.JButton PARAR;
    private javax.swing.JLabel ador;
    private javax.swing.JLabel client3;
    private javax.swing.JLabel client4;
    private javax.swing.JLabel client5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel s3;
    private javax.swing.JLabel s4;
    private javax.swing.JLabel s5;
    private javax.swing.JLabel sb;
    // End of variables declaration//GEN-END:variables
}
