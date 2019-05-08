package appagenciaalquiler.vista;

import appagenciaalquiler.controlador.Controlador;
import appagenciaalquiler.modelo.Grupo;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Ventana extends javax.swing.JFrame {

    private Controlador controlador;
    private DialogoVehiculo dialogoVehiculo;

    public Ventana() {
        initComponents();
        initComponents2();
    }

    private void initComponents2() {
        Tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                TablaValueChanged(e);
            }
        });
        dialogoVehiculo = new DialogoVehiculo(this, true);
        Selector.addChoosableFileFilter(new FiltroXml());
        Selector.addChoosableFileFilter(new FiltroJson());
        Selector.addChoosableFileFilter(new FiltroObj());
        Selector.addChoosableFileFilter(new FiltroCsv());
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setTableModel(AbstractTableModel atm) {
        Tabla.setModel(atm);
    }

    private void TablaValueChanged(ListSelectionEvent e) {
        int fila = Tabla.getSelectedRow();
        if (fila != -1) {
            Matricula.setText((String) Tabla.getModel().getValueAt(fila, 0));
            Nombre.setText((String) Tabla.getModel().getValueAt(fila, 1));
            GrupoA.setSelectedItem(Tabla.getModel().getValueAt(fila, 2).toString());
            Plazas.setValue((Integer) Tabla.getModel().getValueAt(fila, 3));
            Precio.setText(Float.toString((Float) Tabla.getModel().getValueAt(fila, 4)));
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean solicitarConfirmacion() {
        boolean confirmado;
        if (JOptionPane.showConfirmDialog(this, "Seguro") == JOptionPane.YES_OPTION) {
            confirmado = true;
        } else {
            confirmado = false;
        }
        return confirmado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Selector = new javax.swing.JFileChooser();
        ButtonGroup = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Matricula = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Precio = new javax.swing.JTextField();
        Plazas = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        BotonPrecio = new javax.swing.JRadioButton();
        BotonMatricula = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        FiltrarGrupo = new javax.swing.JComboBox<>();
        Borrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Listar = new javax.swing.JButton();
        GrupoA = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        Buscar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Datos de vehículo");

        jLabel2.setText("Matrícula");

        jLabel3.setText("Nombre");

        jLabel4.setText("Grupo");

        jLabel5.setText("Plazas");

        jLabel6.setText("Precio por Día");

        jLabel7.setText("Listado de vehículos");

        Matricula.setEditable(false);

        Nombre.setEditable(false);

        Precio.setEditable(false);

        Plazas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nombre", "Grupo", "Plazas", "Precio Alquiler"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(0).setResizable(false);
            Tabla.getColumnModel().getColumn(1).setResizable(false);
            Tabla.getColumnModel().getColumn(2).setResizable(false);
            Tabla.getColumnModel().getColumn(3).setResizable(false);
            Tabla.getColumnModel().getColumn(4).setResizable(false);
        }

        ButtonGroup.add(BotonPrecio);
        BotonPrecio.setText("Precio Alquiler");

        ButtonGroup.add(BotonMatricula);
        BotonMatricula.setSelected(true);
        BotonMatricula.setText("Matrícula");
        BotonMatricula.setActionCommand("Matricula");

        jLabel8.setText("Orden del Listado");

        jLabel9.setText("Filtrar por grupo");

        FiltrarGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "A", "B", "C" }));

        Borrar.setText("Borrar");
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });

        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        Listar.setText("Listar Vehículos");
        Listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarActionPerformed(evt);
            }
        });

        GrupoA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        Abrir.setText("Abrir...");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        jMenu1.add(Abrir);

        Guardar.setText("Guardar...");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jMenu1.add(Guardar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vehículo");

        Nuevo.setText("Nuevo...");
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        jMenu2.add(Nuevo);

        Buscar.setText("Buscar...");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jMenu2.add(Buscar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel7)
                        .addGap(73, 73, 73)
                        .addComponent(Listar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(BotonMatricula)
                        .addGap(18, 18, 18)
                        .addComponent(BotonPrecio)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(FiltrarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Borrar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Modificar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(GrupoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Matricula, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                        .addComponent(Nombre)
                                        .addComponent(Precio))
                                    .addComponent(Plazas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(Listar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(BotonMatricula)
                            .addComponent(BotonPrecio)
                            .addComponent(jLabel9)
                            .addComponent(FiltrarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Matricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(GrupoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Plazas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Borrar)
                            .addComponent(Modificar))))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
        if (dialogoVehiculo.mostrar() == DialogoVehiculo.ACEPTAR) {
            Matricula.setText(dialogoVehiculo.getMatricula());
            Nombre.setText(dialogoVehiculo.getNombre());
            GrupoA.setSelectedItem(dialogoVehiculo.getGrupo());
            Plazas.setValue(dialogoVehiculo.getPlazas());
            Precio.setText(String.valueOf(Grupo.valueOf(dialogoVehiculo.getGrupo()).getValor()));
            controlador.crearVehiculo();
            dialogoVehiculo.limpiarCampos();
        }
    }//GEN-LAST:event_NuevoActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        if (Selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            controlador.cargarVehiculos();
        }
    }//GEN-LAST:event_AbrirActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (Selector.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            controlador.guardarVehiculos();
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        String cadena = JOptionPane.showInputDialog(this, "Matrícula");
        if (cadena != null && cadena.trim().length() != 0) {
            Matricula.setText(cadena);
            controlador.buscarVehiculo();
        } else {
            mostrarMensaje("Matrícula no válida");
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        String cadena = Matricula.getText();
        try {
            if (cadena.trim().length() != 0) {
                controlador.borrarVehiculo();
            } else {
                mostrarMensaje("No hay ningún vehículo seleccionado");
            }
        } catch (NullPointerException npe) {
            mostrarMensaje("No se encontró el vehículo");
        }

    }//GEN-LAST:event_BorrarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        String cadena = Matricula.getText();
        try {
            if (cadena.trim().length() != 0) {
                controlador.modificarVehiculo();
            } else {
                mostrarMensaje("No hay ningún vehículo seleccionado");
            }
        } catch (NullPointerException npe) {
            mostrarMensaje("No se encontró el vehículo");
        }
    }//GEN-LAST:event_ModificarActionPerformed

    private void ListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarActionPerformed
        controlador.listarVehiculos();
    }//GEN-LAST:event_ListarActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    public void limpiarCampos() {
        Matricula.setText("");
        Nombre.setText("");
        GrupoA.setSelectedItem("A");
        Plazas.setValue(1);
        Precio.setText("");
    }

    public String getFiltroGrupo() {
        return FiltrarGrupo.getSelectedItem().toString();
    }

    public String getOrden() {
        return ButtonGroup.getSelection().getActionCommand();
    }

    public String getMatricula() {
        return Matricula.getText();
    }

    public void mostrarMatricula(String matricula) {
        Matricula.setText(matricula);
    }

    public String getNombre() {
        return Nombre.getText();
    }

    public void mostrarNombre(String nombre) {
        Nombre.setText(nombre);
    }

    public String getGrupo() {
        return GrupoA.getSelectedItem().toString();
    }

    public void mostrarGrupo(String grupo) {
        GrupoA.setSelectedItem(grupo);
    }

    public int getPlazas() {
        return Integer.parseInt(Plazas.getValue().toString());
    }

    public void mostrarPlazas(int plazas) {
        Plazas.setValue(plazas);
    }

    public void mostrarPrecioAlquiler(float precio) {
        Precio.setText(String.valueOf(precio));
    }

    public String getNombreArchivo() {
        return Selector.getSelectedFile().toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JButton Borrar;
    private javax.swing.JRadioButton BotonMatricula;
    private javax.swing.JRadioButton BotonPrecio;
    private javax.swing.JMenuItem Buscar;
    private javax.swing.ButtonGroup ButtonGroup;
    private javax.swing.JComboBox<String> FiltrarGrupo;
    private javax.swing.JComboBox<String> GrupoA;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JButton Listar;
    private javax.swing.JTextField Matricula;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField Nombre;
    private javax.swing.JMenuItem Nuevo;
    private javax.swing.JSpinner Plazas;
    private javax.swing.JTextField Precio;
    private javax.swing.JFileChooser Selector;
    private javax.swing.JTable Tabla;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
