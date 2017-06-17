/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author User
 */
public class PanelDeleteIncome extends JPanel implements TableModelListener, ListSelectionListener, ActionListener {

    private JLabel title;
    private JTable tabel;
    private JScrollPane tablePane;
    private JButton cancel, select;
    private DataUser currentUser;
    private ListenerIncome listener;

    public void addListenerIncome(ListenerIncome listener) {
        this.listener = listener;
    }

    public PanelDeleteIncome(DataUser currentUser) {
        this.currentUser = currentUser;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Delete Income");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        ModelIncome mi = new ModelIncome(currentUser);
        tabel = new JTable();
        tabel.setModel(mi);
        tabel.setAutoCreateRowSorter(true);
        tabel.setRowHeight(20);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabel.getRowSorter().toggleSortOrder(0);
        tablePane = new JScrollPane(tabel);
        tablePane.setPreferredSize(new Dimension(350, 150));
        cancel = new JButton("Cancel");
        select = new JButton("Select");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(390, 300));
        String column = "40dlu, 65dlu, 10dlu, 65dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 3, "center, center"));
        this.add(tablePane, c.xyw(2, 4, 3, "center, center"));
        this.add(cancel, c.xy(2, 6));
        this.add(select, c.xy(4, 6));
    }

    public void registerListener() {
        cancel.addActionListener(this);
        select.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(select)) {
            ModelIncome mi = (ModelIncome) tabel.getModel();
            Object[] temp = mi.getRow(tabel.getSelectedRow());
            int id =Integer.parseInt(String.valueOf(temp[3]));
            listener.deleteIncome(id);
        }
        if (e.getSource().equals(cancel)) {
            listener.cancelIncome(this);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
