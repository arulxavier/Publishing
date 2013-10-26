/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fixent.publish.client.subscribe.view;

/**
 *
 * @author Mathan
 */
public class SubscriberInfoPopupView extends javax.swing.JPanel {

    /**
     * Creates new form SubscriberInfoPopupView
     */
    public SubscriberInfoPopupView() {
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

        jLabel1 = new javax.swing.JLabel();
        bookComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        subscribeDatePicker = new com.fixent.scm.client.component.ADatePicker();
        jLabel3 = new javax.swing.JLabel();
        noOfYearComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        expiredDatePicker = new com.fixent.scm.client.component.ADatePicker();
        jLabel5 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Book");

        bookComboBox.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        bookComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("Subscribe Date");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setText("No Of Year");

        noOfYearComboBox.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        noOfYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "1", "2", "3" }));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setText("Expired Date");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Subscribe Info");

        saveButton.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        saveButton.setText("Save");

        cancelButton.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(noOfYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(subscribeDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expiredDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(subscribeDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(bookComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(noOfYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(expiredDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bookComboBox;
    private javax.swing.JButton cancelButton;
    private com.fixent.scm.client.component.ADatePicker expiredDatePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox noOfYearComboBox;
    private javax.swing.JButton saveButton;
    private com.fixent.scm.client.component.ADatePicker subscribeDatePicker;
    // End of variables declaration//GEN-END:variables
	public javax.swing.JComboBox getBookComboBox() {
		return bookComboBox;
	}

	public void setBookComboBox(javax.swing.JComboBox bookComboBox) {
		this.bookComboBox = bookComboBox;
	}

	public javax.swing.JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(javax.swing.JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public com.fixent.scm.client.component.ADatePicker getExpiredDatePicker() {
		return expiredDatePicker;
	}

	public void setExpiredDatePicker(
			com.fixent.scm.client.component.ADatePicker expiredDatePicker) {
		this.expiredDatePicker = expiredDatePicker;
	}

	public javax.swing.JLabel getjLabel1() {
		return jLabel1;
	}

	public void setjLabel1(javax.swing.JLabel jLabel1) {
		this.jLabel1 = jLabel1;
	}

	public javax.swing.JLabel getjLabel2() {
		return jLabel2;
	}

	public void setjLabel2(javax.swing.JLabel jLabel2) {
		this.jLabel2 = jLabel2;
	}

	public javax.swing.JLabel getjLabel3() {
		return jLabel3;
	}

	public void setjLabel3(javax.swing.JLabel jLabel3) {
		this.jLabel3 = jLabel3;
	}

	public javax.swing.JLabel getjLabel4() {
		return jLabel4;
	}

	public void setjLabel4(javax.swing.JLabel jLabel4) {
		this.jLabel4 = jLabel4;
	}

	public javax.swing.JLabel getjLabel5() {
		return jLabel5;
	}

	public void setjLabel5(javax.swing.JLabel jLabel5) {
		this.jLabel5 = jLabel5;
	}

	public javax.swing.JComboBox getNoOfYearComboBox() {
		return noOfYearComboBox;
	}

	public void setNoOfYearComboBox(javax.swing.JComboBox noOfYearComboBox) {
		this.noOfYearComboBox = noOfYearComboBox;
	}

	public javax.swing.JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(javax.swing.JButton saveButton) {
		this.saveButton = saveButton;
	}

	public com.fixent.scm.client.component.ADatePicker getSubscribeDatePicker() {
		return subscribeDatePicker;
	}

	public void setSubscribeDatePicker(
			com.fixent.scm.client.component.ADatePicker subscribeDatePicker) {
		this.subscribeDatePicker = subscribeDatePicker;
	}
    
    
}