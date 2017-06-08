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
public class ModelExpenditure extends AbstractTableModel {

    private String[] columnNames = {"Date", "Description", "Amount", "Category"};
    private Class<?>[] columnClasses = {String.class, String.class, Integer.class, String.class};
    ServiceExpenditure se = new ServiceExpenditure();
    ServiceCategory sc = new ServiceCategory();
    private Vector<DataExpenditure> data;
    private Vector<Object[]> rows;

    public ModelExpenditure(DataUser user) {
        data = se.getExpenditure(user);
        rows = new Vector<Object[]>();
        for (DataExpenditure a : data) {
            Object[] arow = new Object[6];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getKeterangan();
            arow[2] = a.getJumlah();
            arow[3] = sc.getCategoryById(a.getIdKategori()).getNama();
            arow[4] = sc.getCategoryById(a.getIdKategori()).getId();
            arow[5] = a.getId();
            rows.add(arow);
        }
    }

    public Vector<DataExpenditure> getExpenditure(DataUser user) {
        return se.getExpenditure(user);
    }

    public boolean inputExpenditure(DataUser user, DataExpenditure expend) {
        return se.addNewExpenditure(user, expend);
    }

    public boolean editExpenditure(DataExpenditure expend) {
        return se.editSelectedExpenditure(expend);
    }

    public boolean deleteExpenditure(int id) {
        return se.deleteSelectedExpenditure(id);
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
