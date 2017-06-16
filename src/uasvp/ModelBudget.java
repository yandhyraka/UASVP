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
public class ModelBudget extends AbstractTableModel {

    private String[] columnNames = {"Month", "Amount", "Category"};
    private Class<?>[] columnClasses = {String.class, Integer.class, String.class};
    ServiceBudget sb = new ServiceBudget();
    ServiceCategory sc = new ServiceCategory();
    private Vector<DataBudget> data;
    private Vector<Object[]> rows;

    public ModelBudget(DataUser user) {
        data = sb.getBudget(user);
        rows = new Vector<Object[]>();
        for (DataBudget a : data) {
            Object[] arow = new Object[6];
            arow[0] = a.getBulan();
            arow[1] = a.getJumlah();
            arow[2] = sc.getCategoryById(a.getIdKategori()).getNama();
            arow[3] = sc.getCategoryById(a.getIdKategori()).getId();
            arow[4] = a.getSisa();
            arow[5] = a.getId();
            rows.add(arow);
        }
    }
    
     public Vector<DataBudget> getBudget(DataUser user) {
        return sb.getBudget(user);
    }

    public boolean inputBudget(DataUser user, DataBudget budget) {
        return sb.addNewBudget(user, budget);
    }

    public boolean editBudget(DataBudget budget) {
        return sb.editSelectedBudget(budget);
    }

    public boolean deleteBudget(int id) {
        return sb.deleteSelectedBudget(id);
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
