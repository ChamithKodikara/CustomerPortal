/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgates.customerportal.view.invoice;

import com.vgates.customerportal.controller.*;
import com.vgates.customerportal.model.*;
import com.vgates.customerportal.util.MethodResult;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chamith
 */
public class InvoiceMainForm extends javax.swing.JPanel {

    private final UserDetailController userDetailController;
    private final MasterServiceController serviceController;
    private final CustomerDetailController customerDetailController;
    private final MasterInvoiceController invoiceController;
    private final EmployeeDetailController employeeDetailController;

    private final DefaultTableModel defaultServiceTableModel;
    private final DefaultTableModel dtmOfDailyReportTbl;
    private final Logger LOGGER = Logger.getLogger(InvoiceMainForm.class);
    private UserDetail userDetail;
    private Invoice invoice;
    private MasterService searchedService;
    private File jasperFile;
    private File jasperFile2;
    private DefaultTableModel dtmOfMonthlyReportTbl;
    private DefaultTableModel dtmOfAnnualReportTbl;

    private Matcher priceMatcher;
    private final Pattern pattern;
    private List<MasterService> masterServices;
    private SimpleDateFormat sdf;
    private EmployeeDetail employee;

    private String serviceCategory;

    /**
     * Creates new form InvoiceMainForm
     */
    public InvoiceMainForm() {
        initComponents();
        setSize(800, 550);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        userDetailController = new UserDetailController();
        serviceController = new MasterServiceController();
        invoiceController = new MasterInvoiceController();
        masterServices = new ArrayList<>();
        userDetail = userDetailController.findUserDetailForActiveLogin();
        customerDetailController = new CustomerDetailController();
        employeeDetailController = new EmployeeDetailController();

        defaultServiceTableModel = (DefaultTableModel) tblServiceDetail.getModel();
        defaultServiceTableModel.setRowCount(0);

        txtNewTotalCost.setText("00.00");
        txtNewDiscount.setText("00.00");
        txtNewPaidAmount.setText("00.00");
        txtNewBalance.setText("00.00");
        txtNewFinalAmount.setText("00.00");
        txtNewInvoiceNo.setText("");
        txtNewDesc.setText("");
        txtNewInvoiceNo.setText(invoiceController.newInvoiceNo());
        serviceCategory = "Salon";
        rdoBtnSalon.setSelected(true);

        loadServiceList();
        loadCustomerList();
        jasperFile = new File("./reports/InvoiceReport.jasper");
        jasperFile2 = new File("./reports/PosBill.jasper");
        pattern = Pattern.compile("^\\d+.\\d?\\d?$");

        datePickerDailyReport.setDate(new Date());
        dtmOfDailyReportTbl = (DefaultTableModel) tblDailyReport.getModel();
        dtmOfMonthlyReportTbl = (DefaultTableModel) tblMonthlyReport.getModel();
        dtmOfAnnualReportTbl = (DefaultTableModel) tblAnnualReport.getModel();
        
        btnPrintBill.setVisible(false);
        btnPrintInvoice.setVisible(false);

    }

    private void loadCustomerList() {
        comboCustomer.removeAllItems();
        comboCustomer.addItem("[SELECT]");
        List<CustomerDetail> allActiveCustomerList = customerDetailController.getAllActiveCustomerList();
        allActiveCustomerList.forEach(e -> {
            comboCustomer.addItem(e);
        });
    }

    private void loadEmployeeList() {
        comboEmployee.removeAllItems();
        comboEmployee.addItem("[SELECT]");
        List<EmployeeDetail> allActiveEmployeeList = employeeDetailController.getAllActiveEmployeeList();
        allActiveEmployeeList.forEach(e -> {
            comboEmployee.addItem(e);
        });
    }

    private void loadServiceList() {
        comboServiceName.removeAllItems();
        comboServiceName.addItem("[SELECT]");
        List<MasterService> serviceList = serviceController.searchAllActiveServiceByCategory(serviceCategory);
        serviceList.forEach(e -> {
            comboServiceName.addItem(e);
        });

//        SearchComboBox searchService = new SearchComboBox();
//        searchService.search(comboServiceName, true, "No Service Found");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpServiceType = new javax.swing.ButtonGroup();
        lblInvoiceMain = new javax.swing.JLabel();
        panelSearchInvoice = new javax.swing.JTabbedPane();
        panelNewItem = new javax.swing.JPanel();
        lblNewItemMain = new javax.swing.JLabel();
        lblNewInvoiceNo = new javax.swing.JLabel();
        txtNewInvoiceNo = new javax.swing.JTextField();
        lblNewInvoiceDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNewDesc = new javax.swing.JTextArea();
        lblNewCost = new javax.swing.JLabel();
        lblNewDiscount = new javax.swing.JLabel();
        btnCancelAdd = new javax.swing.JButton();
        btnNewInvoice = new javax.swing.JButton();
        lblNewPaidAmount = new javax.swing.JLabel();
        lblNewBalance = new javax.swing.JLabel();
        lblNewService = new javax.swing.JLabel();
        comboServiceName = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblServiceDetail = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        lblNewFinalPrice = new javax.swing.JLabel();
        lblOutput = new javax.swing.JLabel();
        btnPrintInvoice = new javax.swing.JButton();
        lblCustomer = new javax.swing.JLabel();
        comboCustomer = new javax.swing.JComboBox();
        txtNewDiscount = new javax.swing.JFormattedTextField();
        txtNewPaidAmount = new javax.swing.JFormattedTextField();
        txtNewBalance = new javax.swing.JFormattedTextField();
        txtNewFinalAmount = new javax.swing.JFormattedTextField();
        txtNewTotalCost = new javax.swing.JFormattedTextField();
        btnRemove = new javax.swing.JButton();
        lblEmployee = new javax.swing.JLabel();
        comboEmployee = new javax.swing.JComboBox();
        btnPrintBill = new javax.swing.JButton();
        rdoBtnSalon = new javax.swing.JRadioButton();
        rdoBtnStudio = new javax.swing.JRadioButton();
        btnNewBill = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        datePickerDailyReport = new org.jdesktop.swingx.JXDatePicker();
        btnViewDailyRecords = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDailyReport = new javax.swing.JTable();
        lblDate1 = new javax.swing.JLabel();
        txtTotalIncomeDailyReport = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtYearMonthlyReport = new javax.swing.JFormattedTextField();
        lblYearMonthlyReport = new javax.swing.JLabel();
        lblMonthMonthlyReport = new javax.swing.JLabel();
        comboMonthMonthlyReport = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblMonthlyReport = new javax.swing.JTable();
        btnViewMonthlyRecords = new javax.swing.JButton();
        lblDate2 = new javax.swing.JLabel();
        txtTotalIncomeMonthlyReport = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblDate3 = new javax.swing.JLabel();
        btnViewAnnualRecords = new javax.swing.JButton();
        txtTotalIncomeAnnualReport = new javax.swing.JTextField();
        txtYearAnnualReport = new javax.swing.JFormattedTextField();
        lblYearMonthlyReport1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAnnualReport = new javax.swing.JTable();

        lblInvoiceMain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInvoiceMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInvoiceMain.setText("Invoice Details");

        panelSearchInvoice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panelSearchInvoice.setMaximumSize(new java.awt.Dimension(32767, 700));

        lblNewItemMain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNewItemMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewItemMain.setText("New Invoice");

        lblNewInvoiceNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewInvoiceNo.setText("Invoice No");

        txtNewInvoiceNo.setEditable(false);
        txtNewInvoiceNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNewInvoiceNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewInvoiceNoActionPerformed(evt);
            }
        });

        lblNewInvoiceDesc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewInvoiceDesc.setText("Description");

        txtNewDesc.setColumns(20);
        txtNewDesc.setRows(5);
        jScrollPane1.setViewportView(txtNewDesc);

        lblNewCost.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewCost.setText("Total");

        lblNewDiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewDiscount.setText("Discount");

        btnCancelAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelAdd.setText("Cancel");
        btnCancelAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAddActionPerformed(evt);
            }
        });

        btnNewInvoice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNewInvoice.setText("Save Invoice");
        btnNewInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewInvoiceActionPerformed(evt);
            }
        });

        lblNewPaidAmount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewPaidAmount.setText("Paid Amount");

        lblNewBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewBalance.setText("Balance");

        lblNewService.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewService.setText("Service");

        comboServiceName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboServiceName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[SELECT]" }));
        comboServiceName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboServiceNameItemStateChanged(evt);
            }
        });
        comboServiceName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboServiceNameActionPerformed(evt);
            }
        });

        tblServiceDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Service", "Description", "Done by", "Cost", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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
        tblServiceDetail.setColumnSelectionAllowed(true);
        tblServiceDetail.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblServiceDetail);
        tblServiceDetail.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblNewFinalPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNewFinalPrice.setText("Final Amount");

        lblOutput.setForeground(new java.awt.Color(204, 0, 0));

        btnPrintInvoice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrintInvoice.setText("Print");
        btnPrintInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintInvoiceActionPerformed(evt);
            }
        });

        lblCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCustomer.setText("Customer");

        comboCustomer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboCustomer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[SELECT]" }));
        comboCustomer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCustomerItemStateChanged(evt);
            }
        });
        comboCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCustomerActionPerformed(evt);
            }
        });

        txtNewDiscount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNewDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNewDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewDiscountFocusGained(evt);
            }
        });
        txtNewDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewDiscountActionPerformed(evt);
            }
        });
        txtNewDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewDiscountKeyReleased(evt);
            }
        });

        txtNewPaidAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNewPaidAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNewPaidAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewPaidAmountFocusGained(evt);
            }
        });
        txtNewPaidAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNewPaidAmountActionPerformed(evt);
            }
        });
        txtNewPaidAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewPaidAmountKeyReleased(evt);
            }
        });

        txtNewBalance.setEditable(false);
        txtNewBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNewBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtNewFinalAmount.setEditable(false);
        txtNewFinalAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNewFinalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtNewTotalCost.setEditable(false);
        txtNewTotalCost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtNewTotalCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(153, 0, 0));
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lblEmployee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEmployee.setText("Done by");

        comboEmployee.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboEmployee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[SELECT]" }));
        comboEmployee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEmployeeItemStateChanged(evt);
            }
        });
        comboEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEmployeeActionPerformed(evt);
            }
        });

        btnPrintBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPrintBill.setText("Print Bill");
        btnPrintBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintBillActionPerformed(evt);
            }
        });

        btnGrpServiceType.add(rdoBtnSalon);
        rdoBtnSalon.setText("Salon");
        rdoBtnSalon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoBtnSalonItemStateChanged(evt);
            }
        });
        rdoBtnSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoBtnSalonActionPerformed(evt);
            }
        });

        btnGrpServiceType.add(rdoBtnStudio);
        rdoBtnStudio.setText("Studio");
        rdoBtnStudio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoBtnStudioItemStateChanged(evt);
            }
        });
        rdoBtnStudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoBtnStudioActionPerformed(evt);
            }
        });

        btnNewBill.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNewBill.setText("Save Bill");
        btnNewBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNewItemLayout = new javax.swing.GroupLayout(panelNewItem);
        panelNewItem.setLayout(panelNewItemLayout);
        panelNewItemLayout.setHorizontalGroup(
            panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNewItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNewItemLayout.createSequentialGroup()
                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNewItemLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelNewItemLayout.createSequentialGroup()
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblNewDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblNewCost, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNewDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelNewItemLayout.createSequentialGroup()
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblNewFinalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNewBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNewPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNewFinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblOutput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 19, 19))
                    .addGroup(panelNewItemLayout.createSequentialGroup()
                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNewItemMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNewItemLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnPrintInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPrintBill, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNewInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(panelNewItemLayout.createSequentialGroup()
                                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelNewItemLayout.createSequentialGroup()
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblNewInvoiceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblNewInvoiceDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelNewItemLayout.createSequentialGroup()
                                                .addComponent(txtNewInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(rdoBtnSalon)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoBtnStudio))
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelNewItemLayout.createSequentialGroup()
                                        .addComponent(lblNewService, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(panelNewItemLayout.createSequentialGroup()
                        .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNewItemLayout.createSequentialGroup()
                                .addComponent(lblCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelNewItemLayout.createSequentialGroup()
                                .addComponent(lblEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE))))
        );
        panelNewItemLayout.setVerticalGroup(
            panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNewItemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNewItemMain, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoBtnSalon)
                        .addComponent(rdoBtnStudio))
                    .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNewInvoiceNo)
                        .addComponent(txtNewInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer)
                    .addComponent(comboCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNewInvoiceDesc)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewService)
                    .addComponent(comboServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnRemove))
                    .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEmployee)
                        .addComponent(comboEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewCost)
                    .addComponent(txtNewTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewDiscount)
                    .addComponent(txtNewDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewFinalPrice)
                    .addComponent(txtNewFinalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewPaidAmount)
                    .addComponent(txtNewPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNewBalance)
                    .addComponent(txtNewBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNewItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelAdd)
                    .addComponent(btnNewInvoice)
                    .addComponent(btnNewBill)
                    .addComponent(btnPrintBill)
                    .addComponent(btnPrintInvoice))
                .addContainerGap())
        );

        panelSearchInvoice.addTab("New Invoice", panelNewItem);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate.setText("Date");

        btnViewDailyRecords.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewDailyRecords.setText("View");
        btnViewDailyRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDailyRecordsActionPerformed(evt);
            }
        });

        tblDailyReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No", "Customer", "Billed by", "Employee", "Total Cost", "Final Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDailyReport);

        lblDate1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate1.setText("Total Income");

        txtTotalIncomeDailyReport.setEditable(false);
        txtTotalIncomeDailyReport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalIncomeDailyReport.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePickerDailyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnViewDailyRecords)
                        .addGap(316, 316, 316))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalIncomeDailyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(datePickerDailyReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewDailyRecords))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate1)
                    .addComponent(txtTotalIncomeDailyReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily", jPanel3);

        txtYearMonthlyReport.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));

        lblYearMonthlyReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearMonthlyReport.setText("Year");

        lblMonthMonthlyReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMonthMonthlyReport.setText("Month");

        comboMonthMonthlyReport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboMonthMonthlyReport.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "Apirl", "May", "June", "July", "August", "September", "October", "November", "December" }));

        tblMonthlyReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No", "Customer", "Billed by", "Employee", "Total Cost", "Final Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblMonthlyReport);

        btnViewMonthlyRecords.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewMonthlyRecords.setText("View");
        btnViewMonthlyRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMonthlyRecordsActionPerformed(evt);
            }
        });

        lblDate2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate2.setText("Total Income");

        txtTotalIncomeMonthlyReport.setEditable(false);
        txtTotalIncomeMonthlyReport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalIncomeMonthlyReport.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblYearMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtYearMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMonthMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMonthMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnViewMonthlyRecords)
                        .addGap(0, 114, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalIncomeMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYearMonthlyReport)
                    .addComponent(txtYearMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonthMonthlyReport)
                    .addComponent(comboMonthMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewMonthlyRecords))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate2)
                    .addComponent(txtTotalIncomeMonthlyReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Monthly", jPanel4);

        lblDate3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDate3.setText("Total Income");

        btnViewAnnualRecords.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnViewAnnualRecords.setText("View");
        btnViewAnnualRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAnnualRecordsActionPerformed(evt);
            }
        });

        txtTotalIncomeAnnualReport.setEditable(false);
        txtTotalIncomeAnnualReport.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalIncomeAnnualReport.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtYearAnnualReport.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));

        lblYearMonthlyReport1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblYearMonthlyReport1.setText("Year");

        tblAnnualReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No", "Customer", "Billed by", "Employee", "Total Cost", "Final Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblAnnualReport);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblYearMonthlyReport1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYearAnnualReport, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnViewAnnualRecords)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalIncomeAnnualReport, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewAnnualRecords)
                    .addComponent(lblYearMonthlyReport1)
                    .addComponent(txtYearAnnualReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate3)
                    .addComponent(txtTotalIncomeAnnualReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Annually", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        panelSearchInvoice.addTab("Reports", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInvoiceMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelSearchInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblInvoiceMain, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSearchInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNewInvoiceNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewInvoiceNoActionPerformed

    }//GEN-LAST:event_txtNewInvoiceNoActionPerformed

    private void btnCancelAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAddActionPerformed
        clearForm();


    }//GEN-LAST:event_btnCancelAddActionPerformed

    private void btnNewInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewInvoiceActionPerformed
//        int responce = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this Invoice...?", "New Invoice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        int responce = JOptionPane.YES_OPTION;
        if (comboCustomer.getSelectedIndex() == 0) {
            lblOutput.setText("Customer Not selected...");
            return;
        }
        if (Double.parseDouble(txtNewFinalAmount.getText()) <= Double.parseDouble(txtNewPaidAmount.getText())) {
//            LOGGER.debug("Enter to if condition");
//            if (responce == JOptionPane.YES_OPTION) {
            invoice = new Invoice();
            invoice.setInvoiceNo(txtNewInvoiceNo.getText());
            invoice.setCreatedDate(new Date());
            invoice.setDescription(txtNewDesc.getText());
            invoice.setTotalAmount(Double.parseDouble(txtNewTotalCost.getText()));
            invoice.setDiscount(Double.parseDouble(txtNewDiscount.getText()));
            invoice.setPaidAmount(Double.parseDouble(txtNewPaidAmount.getText()));
            invoice.setBalanceAmount(Double.parseDouble(txtNewBalance.getText()));
            invoice.setFinalAmount(Double.parseDouble(txtNewFinalAmount.getText()));
            invoice.setCustomerDetail((CustomerDetail) comboCustomer.getSelectedItem());
            invoice.setEmployeeDetail(employee);

            MethodResult result = invoiceController.generateNewInvoice(invoice, masterServices);
            if (result.isOk()) {

                int res = JOptionPane.showConfirmDialog(this, result.getMessage() + "\nDo you want to print the invoice?", "New Invoice", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    btnPrintInvoice.doClick();
                }

                clearForm();

            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "New Invoice", JOptionPane.INFORMATION_MESSAGE);
            }
//            }
        } else {
            lblOutput.setText("Invoice cannot continue cause Paid amount not enough");
        }


    }//GEN-LAST:event_btnNewInvoiceActionPerformed

    private void clearForm() {
        txtNewTotalCost.setText("00.00");
        txtNewDiscount.setText("00.00");
        txtNewPaidAmount.setText("00.00");
        txtNewBalance.setText("00.00");
        txtNewFinalAmount.setText("00.00");
        txtNewInvoiceNo.setText("");
        txtNewDesc.setText("");
        comboCustomer.setSelectedIndex(0);
        comboServiceName.setSelectedIndex(0);
        txtNewInvoiceNo.setText(invoiceController.newInvoiceNo());
        invoice = new Invoice();
        masterServices = new ArrayList<>();
        defaultServiceTableModel.setRowCount(0);
        employee = null;
    }

    private void comboServiceNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboServiceNameItemStateChanged

        try {
            if (comboServiceName.getSelectedItem() != null && comboServiceName.getSelectedIndex() != 0) {
                searchedService = (MasterService) comboServiceName.getSelectedItem();
            } else {
                searchedService = null;
            }
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            searchedService = null;
        }
    }//GEN-LAST:event_comboServiceNameItemStateChanged

    private void comboServiceNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboServiceNameActionPerformed

    }//GEN-LAST:event_comboServiceNameActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (searchedService != null) {
            masterServices.add(searchedService);
            Object[] rowData = {searchedService.getServiceName(), searchedService.getDescription(), employee, searchedService.getCost(), searchedService.getDiscount()};
            defaultServiceTableModel.addRow(rowData);
            txtNewTotalCost.setText(String.valueOf(Double.parseDouble(txtNewTotalCost.getText()) + searchedService.getCost()));
            txtNewDiscount.setText(String.valueOf(Double.parseDouble(txtNewDiscount.getText()) + searchedService.getDiscount()));
            txtNewFinalAmount.setText(String.valueOf(Double.parseDouble(txtNewFinalAmount.getText()) + searchedService.getCost()));
            lblOutput.setText("");
        } else {
            System.out.println("No services");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnPrintInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintInvoiceActionPerformed
        if (comboCustomer.getSelectedIndex() == 0) {
            lblOutput.setText("Customer Not selected...");
            return;
        }
        CustomerDetail customer = (CustomerDetail) comboCustomer.getSelectedItem();

        FileInputStream fileInputStream = null;
        try {
            Map<String, Object> param = new HashMap<>();
            fileInputStream = new FileInputStream(jasperFile);
            param.put("CUSTOMER_ID", customer.getCustomerNo());
            param.put("CUSTOMER_NAME", customer.getCustomerName());
            param.put("CUSTOMER_ADDRESS", customer.getAddress());
            param.put("INVOICE_ID", txtNewInvoiceNo.getText());
            param.put("TOTAL_AMOUNT", txtNewTotalCost.getText());
            param.put("FINAL_AMOUNT", txtNewFinalAmount.getText());
            param.put("PAID_AMOUNT", txtNewPaidAmount.getText());
            param.put("DISCOUNT", txtNewDiscount.getText());
            param.put("BALANCE", txtNewBalance.getText());
            JRTableModelDataSource ds = new JRTableModelDataSource(defaultServiceTableModel);
            JasperPrint jp = JasperFillManager.fillReport(fileInputStream, param, ds);
            JasperViewer.viewReport(jp, false);
        } catch (FileNotFoundException | JRException ex) {
            java.util.logging.Logger.getLogger(InvoiceMainForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(InvoiceMainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPrintInvoiceActionPerformed

    private void comboCustomerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCustomerItemStateChanged
        lblOutput.setText("");
    }//GEN-LAST:event_comboCustomerItemStateChanged

    private void comboCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCustomerActionPerformed

    private void txtNewDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewDiscountKeyReleased
        Matcher matcher = pattern.matcher(txtNewDiscount.getText());
        if (!matcher.matches()) {
            return;
        }
        txtNewFinalAmount.setText(Double.parseDouble(txtNewTotalCost.getText()) - Double.parseDouble(txtNewDiscount.getText()) + "");
    }//GEN-LAST:event_txtNewDiscountKeyReleased

    private void txtNewPaidAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPaidAmountKeyReleased
        Matcher matcher = pattern.matcher(txtNewPaidAmount.getText());
        if (!matcher.matches()) {
            return;
        }
        txtNewBalance.setText(Double.parseDouble(txtNewPaidAmount.getText()) - Double.parseDouble(txtNewFinalAmount.getText()) + "");
    }//GEN-LAST:event_txtNewPaidAmountKeyReleased

    private void txtNewDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewDiscountActionPerformed
        txtNewPaidAmount.requestFocus();
    }//GEN-LAST:event_txtNewDiscountActionPerformed

    private void txtNewDiscountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewDiscountFocusGained
        txtNewDiscount.selectAll();
    }//GEN-LAST:event_txtNewDiscountFocusGained

    private void txtNewPaidAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNewPaidAmountActionPerformed
        btnNewInvoice.doClick();
    }//GEN-LAST:event_txtNewPaidAmountActionPerformed

    private void txtNewPaidAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPaidAmountFocusGained
        txtNewPaidAmount.selectAll();
    }//GEN-LAST:event_txtNewPaidAmountFocusGained

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        final int selectedRow = tblServiceDetail.getSelectedRow();
        Double cost = (Double) tblServiceDetail.getValueAt(selectedRow, 3);

        txtNewTotalCost.setText(String.valueOf(searchedService.getCost() - cost));
        txtNewFinalAmount.setText(String.valueOf(Double.parseDouble(txtNewFinalAmount.getText()) - cost));

        defaultServiceTableModel.removeRow(selectedRow);
        masterServices.remove(selectedRow);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnViewDailyRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDailyRecordsActionPerformed
        Date date = datePickerDailyReport.getDate();
        List<Invoice> allInvoiceOfDay = invoiceController.getAllInvoiceOfDay(date);
        if (allInvoiceOfDay == null || allInvoiceOfDay.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No any invoice for " + sdf.format(date));
            return;
        }
        double totalIncome = 0;
        dtmOfDailyReportTbl.setRowCount(0);
        for (Invoice e : allInvoiceOfDay) {
            dtmOfDailyReportTbl.addRow(new Object[]{
                e.getInvoiceNo(),
                e.getCustomerDetail().toString(),
                "user0",
                "employee0", e.getTotalAmount(),
                e.getFinalAmount()
            });
            totalIncome += e.getFinalAmount();
        }
        txtTotalIncomeDailyReport.setText(totalIncome + "");
    }//GEN-LAST:event_btnViewDailyRecordsActionPerformed

    private void btnViewMonthlyRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMonthlyRecordsActionPerformed
        String year = txtYearMonthlyReport.getText();
        int month = comboMonthMonthlyReport.getSelectedIndex() + 1;
        System.out.println(year + " " + month);
        List<Invoice> allInvoicesOfMonth = invoiceController.getAllInvoicesOfMonth(Integer.parseInt(year), month);
        if (allInvoicesOfMonth == null || allInvoicesOfMonth.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No any invoice for " + year + " - " + comboMonthMonthlyReport.getSelectedItem());
            return;
        }
        double totalIncome = 0;
        dtmOfMonthlyReportTbl.setRowCount(0);
        for (Invoice e : allInvoicesOfMonth) {
            dtmOfMonthlyReportTbl.addRow(new Object[]{
                e.getInvoiceNo(),
                e.getCustomerDetail().toString(),
                "user0",
                "employee0", e.getTotalAmount(),
                e.getFinalAmount()
            });
            totalIncome += e.getFinalAmount();
        }
        txtTotalIncomeMonthlyReport.setText(totalIncome + "");
    }//GEN-LAST:event_btnViewMonthlyRecordsActionPerformed

    private void btnViewAnnualRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAnnualRecordsActionPerformed
        String year = txtYearAnnualReport.getText();

        List<Invoice> allInvoicesOfYear = invoiceController.getAllInvoicesOfYear(Integer.parseInt(year));
        if (allInvoicesOfYear == null || allInvoicesOfYear.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No any invoice for " + year);
            return;
        }
        double totalIncome = 0;
        dtmOfAnnualReportTbl.setRowCount(0);
        totalIncome = allInvoicesOfYear.stream().map((e) -> {
            dtmOfAnnualReportTbl.addRow(new Object[]{
                e.getInvoiceNo(),
                e.getCustomerDetail().toString(),
                "user0",
                "employee0", e.getTotalAmount(),
                e.getFinalAmount()
            });
            return e;
        }).map((e) -> e.getFinalAmount()).reduce(totalIncome, (accumulator, _item) -> accumulator + _item);
        txtTotalIncomeAnnualReport.setText(totalIncome + "");
    }//GEN-LAST:event_btnViewAnnualRecordsActionPerformed

    private void comboEmployeeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEmployeeItemStateChanged
        if (comboEmployee.getSelectedIndex() == 0) {
            return;
        }
        employee = (EmployeeDetail) comboEmployee.getSelectedItem();
    }//GEN-LAST:event_comboEmployeeItemStateChanged

    private void comboEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEmployeeActionPerformed

    private void btnPrintBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintBillActionPerformed
        if (comboCustomer.getSelectedIndex() == 0) {
            lblOutput.setText("Customer Not selected...");
            return;
        }
        if (defaultServiceTableModel.getRowCount() < 1) {
            lblOutput.setText("No services...");
            return;
        }
        CustomerDetail customer = (CustomerDetail) comboCustomer.getSelectedItem();

        FileInputStream fileInputStream = null;
        try {
            Map<String, Object> param = new HashMap<>();
            fileInputStream = new FileInputStream(jasperFile2);
            if (rdoBtnSalon.isSelected()) {
                param.put("COMPANY_NAME", "Salon Wonrose");
                param.put("ADDRESS", "328 C, New Kandy Road,Delgoda");
                param.put("EMAIL", "info@salonwonrose.com");
                param.put("TELE", "0112403605");
            } else {
                param.put("COMPANY_NAME", "Studio Ronak");
                param.put("ADDRESS", "328 C, New Kandy Road,Delgoda");
                param.put("EMAIL", "info@ronakstudios.com");
                param.put("TELE", "0112403605");
            }
            param.put("CUSTOMER_ID", customer.getCustomerNo());
//            param.put("CUSTOMER_NAME", customer.getCustomerName());
//            param.put("CUSTOMER_ADDRESS", customer.getAddress());
            param.put("INVOICE_ID", txtNewInvoiceNo.getText());
            param.put("TOTAL_AMOUNT", txtNewTotalCost.getText());
            param.put("FINAL_AMOUNT", txtNewFinalAmount.getText());
            param.put("PAID_AMOUNT", txtNewPaidAmount.getText());
            param.put("DISCOUNT", txtNewDiscount.getText());
            param.put("BALANCE", txtNewBalance.getText());
            JRTableModelDataSource ds = new JRTableModelDataSource(defaultServiceTableModel);
            JasperPrint jp = JasperFillManager.fillReport(fileInputStream, param, ds);
            JasperViewer.viewReport(jp, false);
//            printDirect(jp);
        } catch (FileNotFoundException | JRException ex) {
            java.util.logging.Logger.getLogger(InvoiceMainForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(InvoiceMainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnPrintBillActionPerformed

    private void rdoBtnSalonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoBtnSalonItemStateChanged
        if (rdoBtnSalon.isSelected()) {
            serviceCategory = "Salon";
            loadServiceList();
        } else if (rdoBtnStudio.isSelected()) {
            serviceCategory = "Studio";
            loadServiceList();
        }
    }//GEN-LAST:event_rdoBtnSalonItemStateChanged

    private void rdoBtnStudioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoBtnStudioItemStateChanged
        if (rdoBtnSalon.isSelected()) {
            serviceCategory = "Salon";
            loadServiceList();
        } else if (rdoBtnStudio.isSelected()) {
            serviceCategory = "Studio";
            loadServiceList();
        }
    }//GEN-LAST:event_rdoBtnStudioItemStateChanged

    private void rdoBtnSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBtnSalonActionPerformed
        if (rdoBtnSalon.isSelected()) {
            serviceCategory = "Salon";
            loadServiceList();
        } else if (rdoBtnStudio.isSelected()) {
            serviceCategory = "Studio";
            loadServiceList();
        }
    }//GEN-LAST:event_rdoBtnSalonActionPerformed

    private void rdoBtnStudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoBtnStudioActionPerformed
        if (rdoBtnSalon.isSelected()) {
            serviceCategory = "Salon";
            loadServiceList();
        } else if (rdoBtnStudio.isSelected()) {
            serviceCategory = "Studio";
            loadServiceList();
        }
    }//GEN-LAST:event_rdoBtnStudioActionPerformed

    private void btnNewBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewBillActionPerformed
       //        int responce = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this Invoice...?", "New Invoice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        int responce = JOptionPane.YES_OPTION;
        if (comboCustomer.getSelectedIndex() == 0) {
            lblOutput.setText("Customer Not selected...");
            return;
        }
        if (Double.parseDouble(txtNewFinalAmount.getText()) <= Double.parseDouble(txtNewPaidAmount.getText())) {
//            LOGGER.debug("Enter to if condition");
//            if (responce == JOptionPane.YES_OPTION) {
            invoice = new Invoice();
            invoice.setInvoiceNo(txtNewInvoiceNo.getText());
            invoice.setCreatedDate(new Date());
            invoice.setDescription(txtNewDesc.getText());
            invoice.setTotalAmount(Double.parseDouble(txtNewTotalCost.getText()));
            invoice.setDiscount(Double.parseDouble(txtNewDiscount.getText()));
            invoice.setPaidAmount(Double.parseDouble(txtNewPaidAmount.getText()));
            invoice.setBalanceAmount(Double.parseDouble(txtNewBalance.getText()));
            invoice.setFinalAmount(Double.parseDouble(txtNewFinalAmount.getText()));
            invoice.setCustomerDetail((CustomerDetail) comboCustomer.getSelectedItem());
            invoice.setEmployeeDetail(employee);

            MethodResult result = invoiceController.generateNewInvoice(invoice, masterServices);
            if (result.isOk()) {

                int res = JOptionPane.showConfirmDialog(this, result.getMessage() + "\nDo you want to print the Bill?", "New Bill", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    btnPrintBill.doClick();
                }

                clearForm();

            } else {
                JOptionPane.showMessageDialog(this, result.getMessage(), "New Invoice", JOptionPane.INFORMATION_MESSAGE);
            }
//            }
        } else {
            lblOutput.setText("Bill cannot continue cause Paid amount not enough");
        }
    }//GEN-LAST:event_btnNewBillActionPerformed

    private void printDirect(JasperPrint jasperPrint) {
        try {
           // String report = JasperCompileManager.compileReportToFile(sourceFileName);

            // JasperPrint jasperPrint = JasperFillManager.fillReport(report, para, ds);
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
            printerJob.defaultPage(pageFormat);

            int selectedService = 0;

            HashPrintServiceAttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName("Microsoft XPS Document Writer", null));

            PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

            try {
                printerJob.setPrintService(printService[selectedService]);

            } catch (Exception e) {

                System.out.println(e);
            }
            JRPrintServiceExporter exporter;
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // printRequestAttributeSet.add(MediaSizeName.NA_LETTER);
            printRequestAttributeSet.add(new Copies(1));

            // these are deprecated
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelAdd;
    private javax.swing.ButtonGroup btnGrpServiceType;
    private javax.swing.JButton btnNewBill;
    private javax.swing.JButton btnNewInvoice;
    private javax.swing.JButton btnPrintBill;
    private javax.swing.JButton btnPrintInvoice;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnViewAnnualRecords;
    private javax.swing.JButton btnViewDailyRecords;
    private javax.swing.JButton btnViewMonthlyRecords;
    private javax.swing.JComboBox comboCustomer;
    private javax.swing.JComboBox comboEmployee;
    private javax.swing.JComboBox comboMonthMonthlyReport;
    private javax.swing.JComboBox comboServiceName;
    private org.jdesktop.swingx.JXDatePicker datePickerDailyReport;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblDate3;
    private javax.swing.JLabel lblEmployee;
    private javax.swing.JLabel lblInvoiceMain;
    private javax.swing.JLabel lblMonthMonthlyReport;
    private javax.swing.JLabel lblNewBalance;
    private javax.swing.JLabel lblNewCost;
    private javax.swing.JLabel lblNewDiscount;
    private javax.swing.JLabel lblNewFinalPrice;
    private javax.swing.JLabel lblNewInvoiceDesc;
    private javax.swing.JLabel lblNewInvoiceNo;
    private javax.swing.JLabel lblNewItemMain;
    private javax.swing.JLabel lblNewPaidAmount;
    private javax.swing.JLabel lblNewService;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JLabel lblYearMonthlyReport;
    private javax.swing.JLabel lblYearMonthlyReport1;
    private javax.swing.JPanel panelNewItem;
    private javax.swing.JTabbedPane panelSearchInvoice;
    private javax.swing.JRadioButton rdoBtnSalon;
    private javax.swing.JRadioButton rdoBtnStudio;
    private javax.swing.JTable tblAnnualReport;
    private javax.swing.JTable tblDailyReport;
    private javax.swing.JTable tblMonthlyReport;
    private javax.swing.JTable tblServiceDetail;
    private javax.swing.JFormattedTextField txtNewBalance;
    private javax.swing.JTextArea txtNewDesc;
    private javax.swing.JFormattedTextField txtNewDiscount;
    private javax.swing.JFormattedTextField txtNewFinalAmount;
    private javax.swing.JTextField txtNewInvoiceNo;
    private javax.swing.JFormattedTextField txtNewPaidAmount;
    private javax.swing.JFormattedTextField txtNewTotalCost;
    private javax.swing.JTextField txtTotalIncomeAnnualReport;
    private javax.swing.JTextField txtTotalIncomeDailyReport;
    private javax.swing.JTextField txtTotalIncomeMonthlyReport;
    private javax.swing.JFormattedTextField txtYearAnnualReport;
    private javax.swing.JFormattedTextField txtYearMonthlyReport;
    // End of variables declaration//GEN-END:variables
}
