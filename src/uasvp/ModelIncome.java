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
public class ModelIncome extends AbstractTableModel {

    private String[] columnNames = {"Date", "Description", "Amount"};
    private Class<?>[] columnClasses = {String.class, String.class, Integer.class};
    ServiceIncome si = new ServiceIncome();
    private Vector<DataIncome> data;
    private Vector<Object[]> rows;

    public ModelIncome(DataUser user) {
        data = si.getIncome(user);
        rows = new Vector<Object[]>();
        for (DataIncome a : data) {
            Object[] arow = new Object[3];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getKeterangan();
            arow[2] = a.getJumlah();
            rows.add(arow);
        }
    }

    public Vector<DataIncome> getIncome(DataUser user) {
        return si.getIncome(user);
    }

    public boolean InputIncome(DataUser user, DataIncome income) {
        return si.addNewIncome(user, income);
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
