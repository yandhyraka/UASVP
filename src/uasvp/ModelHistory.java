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
public class ModelHistory extends AbstractTableModel {

    private String[] columnNames = {"Date", "Description", "Amount", "Category"};
    private Class<?>[] columnClasses = {String.class, String.class, Integer.class, String.class};
    ServiceIncome si = new ServiceIncome();
    ServiceExpenditure se = new ServiceExpenditure();
    ServiceDebt sd = new ServiceDebt();
    private Vector<DataIncome> datai;
    private Vector<DataExpenditure> datae;
    private Vector<DataDebt> datad;
    private Vector<Object[]> rows;

    public ModelHistory(DataUser user) {
        datai = si.getIncome(user);
        datae = se.getExpenditure(user);
        datad = sd.getDebt(user);

        rows = new Vector<Object[]>();
        for (DataIncome a : datai) {
            Object[] arow = new Object[4];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getKeterangan();
            arow[2] = a.getJumlah();
            arow[3] = "Income";
            rows.add(arow);
        }
        for (DataExpenditure a : datae) {
            Object[] arow = new Object[4];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = a.getKeterangan();
            arow[2] = a.getJumlah();
            arow[3] = "Expenditure";
            rows.add(arow);
        }
        for (DataDebt a : datad) {
            Object[] arow = new Object[4];
            String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(a.getTanggal());
            arow[0] = tanggal;
            arow[1] = "From " + a.getUsername1() + " To " + a.getUsername2();
            arow[2] = a.getJumlah();
            arow[3] = "Debt";
            rows.add(arow);
        }
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
