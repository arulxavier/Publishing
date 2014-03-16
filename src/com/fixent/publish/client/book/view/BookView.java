/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fixent.publish.client.book.view;

/**
 *
 * @author Mathan
 */
public class BookView extends javax.swing.JPanel {

    /**
     * Creates new form BookListView
     */
    public BookView() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        bookListTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        bookListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        bookListTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(bookListTable);

        addButton.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        addButton.setText("Add");

        viewButton.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        viewButton.setText("Edit");

        deleteButton.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        deleteButton.setText("Delete");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Book List View");

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(deleteButton)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(errorLabel)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(viewButton)
                    .addComponent(deleteButton))
                .addGap(159, 159, 159))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTable bookListTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
	public javax.swing.JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(javax.swing.JButton addButton) {
		this.addButton = addButton;
	}

	public javax.swing.JTable getBookListTable() {
		return bookListTable;
	}

	public void setBookListTable(javax.swing.JTable bookListTable) {
		this.bookListTable = bookListTable;
	}

	public javax.swing.JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(javax.swing.JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public javax.swing.JLabel getjLabel1() {
		return jLabel1;
	}

	public void setjLabel1(javax.swing.JLabel jLabel1) {
		this.jLabel1 = jLabel1;
	}

	public javax.swing.JScrollPane getjScrollPane1() {
		return jScrollPane1;
	}

	public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
		this.jScrollPane1 = jScrollPane1;
	}

	public javax.swing.JButton getViewButton() {
		return viewButton;
	}

	public void setViewButton(javax.swing.JButton viewButton) {
		this.viewButton = viewButton;
	}

	public javax.swing.JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(javax.swing.JLabel errorLabel) {
		this.errorLabel = errorLabel;
	}

}
