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
public class PanelMainDebt extends JPanel implements ActionListener {

    private JLabel title;
    private JTable tabel;
    private JScrollPane tablePane;
    private JButton newButton, editButton, deleteButton;
    private DataUser currentUser;
    private ListenerMainDebt listener;
    private ModelDebt md;

    public void addListenerMainDebt(ListenerMainDebt listener) {
        this.listener = listener;
    }

    public PanelMainDebt(DataUser currentUser, ModelDebt md) {
        this.md = md;
        this.currentUser = currentUser;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Debt");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        tabel = new JTable();
        tabel.setModel(md);
        tabel.setAutoCreateRowSorter(true);
        tabel.setRowHeight(20);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabel.getRowSorter().toggleSortOrder(0);
        tablePane = new JScrollPane(tabel);
        tablePane.setPreferredSize(new Dimension(500, 150));
        newButton = new JButton("New");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(550, 340));
        String column = "85dlu, 65dlu, 10dlu, 65dlu, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 3, "center, center"));
        this.add(tablePane, c.xyw(2, 4, 3, "center, center"));
        this.add(newButton, c.xyw(2, 6, 3));
        this.add(editButton, c.xy(2, 8));
        this.add(deleteButton, c.xy(4, 8));
    }

    public void registerListener() {
        newButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newButton)) {
            listener.addDebt();
        }
        if (e.getSource().equals(editButton)) {
            ModelDebt md = (ModelDebt) tabel.getModel();
            Object[] temp = md.getRow(tabel.convertRowIndexToModel(tabel.getSelectedRow()));
            listener.editDebt(temp);
        }
        if (e.getSource().equals(deleteButton)) {
            ModelDebt md = (ModelDebt) tabel.getModel();
            Object[] temp = md.getRow(tabel.convertRowIndexToModel(tabel.getSelectedRow()));
            int id = Integer.parseInt(String.valueOf(temp[5]));
            listener.deleteDebt(id);
        }
    }
}
