/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barsystems.estoque;

import barsystems.fornecedores.Class_Fornecedores;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class Painel_estoque extends javax.swing.JPanel {

    /**
     * Creates new form Painel_estoque
     */
     public void refreshList(){
        Class_estoque carrega = new Class_estoque();
        DefaultListModel lista = carrega.carregaLista();
        lista_centros.setModel(lista);
    }
     
    public void refreshTable(String codigo){
        Class_estoque enviar = new Class_estoque();
        tabela_estoque.setModel(enviar.carregaTabela(codigo));
    }
    
    public Painel_estoque() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subir_produto = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        quantidade = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista_centro2 = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_centros = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_estoque = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        subir_produto.setModal(true);
        subir_produto.getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Este produto esta no(a): ");
        subir_produto.getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 50, 158, 30);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("centroestoque");
        subir_produto.getContentPane().add(jLabel13);
        jLabel13.setBounds(180, 50, 115, 30);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Quantidade em caixas:");
        subir_produto.getContentPane().add(jLabel14);
        jLabel14.setBounds(20, 80, 150, 30);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("caixas");
        subir_produto.getContentPane().add(jLabel15);
        jLabel15.setBounds(180, 80, 40, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Quantidade em unidades: ");
        subir_produto.getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 120, 170, 20);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("unidades");
        subir_produto.getContentPane().add(jLabel17);
        jLabel17.setBounds(190, 110, 60, 30);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Transferir: ");
        subir_produto.getContentPane().add(jLabel18);
        jLabel18.setBounds(20, 150, 80, 17);
        subir_produto.getContentPane().add(quantidade);
        quantidade.setBounds(90, 140, 60, 30);

        jRadioButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton3.setText("caixas");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        subir_produto.getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(160, 150, 65, 25);

        jRadioButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jRadioButton4.setText("unidades");
        subir_produto.getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(160, 180, 83, 25);

        lista_centro2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lista_centro2);

        subir_produto.getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(260, 140, 160, 130);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Produto");
        subir_produto.getContentPane().add(jLabel19);
        jLabel19.setBounds(10, 10, 150, 40);

        setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Estoque");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel2);
        jLabel2.setBounds(0, 20, 700, 29);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Centros de estoque");
        add(jLabel1);
        jLabel1.setBounds(10, 60, 170, 20);

        lista_centros.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lista_centros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_centrosMouseClicked(evt);
            }
        });
        lista_centros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_centrosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lista_centros);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 160, 90);

        tabela_estoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Produto", "Quantidade por caixa", "Quantidade em unidade", "Valor compra unidade", "Valor venda unidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabela_estoque);

        add(jScrollPane2);
        jScrollPane2.setBounds(180, 80, 630, 210);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Produtos em centro de estoque");
        add(jLabel3);
        jLabel3.setBounds(180, 60, 210, 20);

        jButton1.setText("Subir Produto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(10, 180, 160, 50);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Detalhes do produto:");
        add(jLabel4);
        jLabel4.setBounds(190, 300, 140, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Produto");
        add(jLabel5);
        jLabel5.setBounds(330, 300, 90, 20);

        jLabel6.setText("Quantidade em estoque:");
        add(jLabel6);
        jLabel6.setBounds(190, 330, 130, 14);

        jLabel8.setText("Valor de compra unitario:");
        add(jLabel8);
        jLabel8.setBounds(190, 360, 120, 14);

        jLabel9.setText("Valor de venda unitario: ");
        add(jLabel9);
        jLabel9.setBounds(190, 390, 120, 14);

        jLabel10.setText("Comprado em: ");
        add(jLabel10);
        jLabel10.setBounds(440, 330, 80, 14);

        jLabel11.setText("Codigo da compra:");
        add(jLabel11);
        jLabel11.setBounds(440, 360, 100, 14);

        jLabel12.setText("Fornecedor: ");
        add(jLabel12);
        jLabel12.setBounds(440, 390, 70, 14);
    }// </editor-fold>//GEN-END:initComponents

    private void lista_centrosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_centrosValueChanged
       Class_estoque resolve = new Class_estoque();
       String codigo = resolve.getCodigo((String) lista_centros.getSelectedValue());
       refreshTable(codigo);
    }//GEN-LAST:event_lista_centrosValueChanged

    private void lista_centrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_centrosMouseClicked
        Class_estoque resolve = new Class_estoque();
        String codigo = resolve.getCodigo((String) lista_centros.getSelectedValue());
        refreshTable(codigo);
    }//GEN-LAST:event_lista_centrosMouseClicked

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        subir_produto.setBounds(0, 0, 450, 330);
        subir_produto.setLocationRelativeTo(null);
        subir_produto.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lista_centro2;
    private javax.swing.JList lista_centros;
    private javax.swing.JTextField quantidade;
    private javax.swing.JDialog subir_produto;
    private javax.swing.JTable tabela_estoque;
    // End of variables declaration//GEN-END:variables
}
