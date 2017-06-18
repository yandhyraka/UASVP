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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author User
 */
public class PanelHistory extends JPanel implements KeyListener {

    private JLabel title;
    private JTextField search;
    private JTable tabel;
    private JScrollPane tablePane;
    private DataUser currentUser;
    private ListenerHistory listener;
    private ModelHistory mh;

    public void addListenerHistory(ListenerHistory listener) {
        this.listener = listener;
    }

    public PanelHistory(DataUser currentUser, ModelHistory mh) {
        this.mh = mh;
        this.currentUser = currentUser;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("History");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        search = new JTextField("Search", 25);
        tabel = new JTable();
        tabel.setModel(mh);
        tabel.setAutoCreateRowSorter(true);
        tabel.setRowHeight(20);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabel.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabel.getRowSorter().toggleSortOrder(0);
        tablePane = new JScrollPane(tabel);
        tablePane.setPreferredSize(new Dimension(450, 150));
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(485, 300));
        String column = "10dlu, pref, 10dlu";
        String row = "15dlu, pref, 15dlu, pref, 10dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 1, "center, center"));
        this.add(search, c.xyw(2, 4, 1, "center, center"));
        this.add(tablePane, c.xyw(2, 6, 1, "center, center"));
    }

    public void registerListener() {
        search.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(search)) {
            if (search.getText().equalsIgnoreCase("")) {
                tabel.setModel(listener.searchDefaultHistory());
            } else {
                tabel.setModel(listener.searchKeywordHistory(search.getText()));
            }
        }
    }
}
