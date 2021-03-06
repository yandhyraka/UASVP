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
import javax.swing.JOptionPane;
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
public class PanelDeleteBudget extends JPanel implements ActionListener {

    private JLabel title;
    private JTable tabel;
    private JScrollPane tablePane;
    private JButton cancel, select;
    private DataUser currentUser;
    private ModelBudget mb;
    private ListenerBudget listener;

    public void addListenerBudget(ListenerBudget listener) {
        this.listener = listener;
    }

    public PanelDeleteBudget(DataUser currentUser, ModelBudget mb) {
        this.currentUser = currentUser;
        this.mb = mb;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Delete Budget");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        tabel = new JTable();
        tabel.setModel(mb);
        tabel.setAutoCreateRowSorter(true);
        tabel.setRowHeight(20);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabel.getRowSorter().toggleSortOrder(0);
        tablePane = new JScrollPane(tabel);
        tablePane.setPreferredSize(new Dimension(300, 150));
        cancel = new JButton("Cancel");
        select = new JButton("Select");
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(355, 300));
        String column = "30dlu, 65dlu, 10dlu, 65dlu, 10dlu";
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
            if (tabel.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please Select Item Before Continue");
            } else {
                ModelBudget mb = (ModelBudget) tabel.getModel();
                Object[] temp = mb.getRow(tabel.convertRowIndexToModel(tabel.getSelectedRow()));
                int id = Integer.parseInt(String.valueOf(temp[5]));
                listener.deleteBudget(id);
            }
        }
        if (e.getSource().equals(cancel)) {
            listener.cancelBudget(this);
        }
    }
}
