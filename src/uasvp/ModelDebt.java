/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class ModelDebt extends AbstractTableModel {

    private String[] columnNames = {"Date", "Amount", "From", "To", "Status"};
    private Class<?>[] columnClasses = {String.class, Integer.class, String.class, String.class, String.class};
    ServiceDebt sd = new ServiceDebt();
    private Vector<DataDebt> data;
    private Vector<Object[]> rows;

    public ModelDebt(DataUser user) {
        data = sd.getDebt(user);
        rows = new Vector<Object[]>();
        for (DataDebt a : data) {
            Object[] arow = new Object[6];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getJumlah();
            arow[2] = a.getUsername1();
            arow[3] = a.getUsername2();
            if (a.getStatus() == 0) {
                arow[4] = "Not Paid";
            } else {
                arow[4] = "Paid";
            }
            arow[5] = a.getId();
            rows.add(arow);
        }
    }

    public Vector<DataDebt> getDebt(DataUser user) {
        return sd.getDebt(user);
    }

    public boolean inputDebt(DataDebt debt) {
        return sd.addNewDebt(debt);
    }

    public boolean editDebt(DataDebt debt) {
        return sd.editSelectedDebt(debt);
    }

    public boolean deleteDebt(int id) {
        return sd.deleteSelectedDebt(id);
    }

    public Object[] getRow(int row) {
        return rows.get(row);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] arow = rows.get(rowIndex);
        return arow[columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
