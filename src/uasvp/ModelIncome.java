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
    private ServiceIncome si = new ServiceIncome();
    private Vector<DataIncome> data;
    private Vector<Object[]> rows;
    private int totalIncome;

    public ModelIncome(DataUser user) {
        data = si.getIncome(user);
        rows = new Vector<Object[]>();
        for (DataIncome a : data) {
            Object[] arow = new Object[4];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getKeterangan();
            arow[2] = a.getJumlah();
            arow[3] = a.getId();
            rows.add(arow);
        }
    }

    public int getThisMonthIncome(String month) {
        totalIncome = 0;
        for (DataIncome a : data) {
            if (month.equalsIgnoreCase(new SimpleDateFormat("MMMM").format(a.getTanggal()))) {
                totalIncome = totalIncome + a.getJumlah();
            }
        }
        return totalIncome;
    }

    public Vector<DataIncome> getIncome(DataUser user) {
        return si.getIncome(user);
    }

    public boolean inputIncome(DataUser user, DataIncome income) {
        return si.addNewIncome(user, income);
    }

    public boolean editIncome(DataIncome income) {
        return si.editSelectedIncome(income);
    }

    public boolean deleteIncome(int id) {
        return si.deleteSelectedIncome(id);
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
