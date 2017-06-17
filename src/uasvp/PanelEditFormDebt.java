/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uasvp;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PanelEditFormDebt extends JPanel implements ActionListener, KeyListener {

    private JLabel title;
    private JTextField amount;
    private JButton cancel, save;
    private JComboBox from, to, status;
    private JCalendar calendar;
    private String[] isiStatus = {"Not Paid", "Paid"};
    private Object[] debt;
    private ListenerDebt listener;
    private ModelUser mc = new ModelUser();

    public void addListenerDebt(ListenerDebt listener) {
        this.listener = listener;
    }

    public PanelEditFormDebt(Object[] debt) {
        this.debt = debt;
        initComp();
        buildGui();
        registerListener();
    }

    public void initComp() {
        title = new JLabel("Edit Debt");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        amount = new JTextField(String.valueOf(debt[1]), 25);
        cancel = new JButton("Cancel");
        save = new JButton("Save");
        from = new JComboBox(mc.getUser());
        from.setSelectedIndex(getIdUser(String.valueOf(debt[2])));
        to = new JComboBox(mc.getUser());
        to.setSelectedIndex(getIdUser(String.valueOf(debt[3])));
        status = new JComboBox(isiStatus);
        if (String.valueOf(debt[4]).equalsIgnoreCase("Not Paid")) {
            status.setSelectedIndex(0);
        } else {
            status.setSelectedIndex(1);
        }
        calendar = new JCalendar();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(String.valueOf(debt[0]));
        } catch (ParseException ex) {
            Logger.getLogger(PanelEditFormDebt.class.getName()).log(Level.SEVERE, null, ex);
        }
        calendar.setDate(date);
    }

    public int getIdUser(String user) {
        Vector<String> users = mc.getUser();
        int jumlah = 0;
        for (String s : users) {
            if (user.equalsIgnoreCase(s)) {
                return jumlah;
            } else {
                jumlah++;
            }
        }
        return -1;
    }

    public void buildGui() {
        this.setPreferredSize(new Dimension(390, 430));
        String column = "20dlu, 45dlu, 5dlu, 50dlu, 5dlu, 25dlu, 45dlu, 5dlu";
        String row = "15dlu, pref, 15dlu, pref, 1dlu, pref, 10dlu, pref, 10dlu, pref, 20dlu, pref, 10dlu";
        FormLayout layout = new FormLayout(column, row);
        this.setLayout(layout);
        CellConstraints c = new CellConstraints();

        this.add(title, c.xyw(2, 2, 7, "center, center"));
        this.add(new JLabel("Date"), c.xyw(2, 4, 1, "left, top"));
        this.add(calendar, c.xyw(4, 4, 5));
        this.add(new JLabel("From"), c.xy(2, 6));
        this.add(from, c.xy(4, 6));
        this.add(new JLabel("To"), c.xy(6, 6));
        this.add(to, c.xyw(7, 6, 2));
        this.add(new JLabel("Amount"), c.xy(2, 8));
        this.add(amount, c.xyw(4, 8, 5));
        this.add(new JLabel("Status"), c.xy(2, 10));
        this.add(status, c.xyw(4, 10, 5));
        this.add(cancel, c.xyw(2, 12, 2));
        this.add(save, c.xyw(7, 12, 2));
    }

    public void registerListener() {
        amount.addKeyListener(this);
        cancel.addActionListener(this);
        save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(save)) {
            DataDebt dd = new DataDebt();
            dd.setId(Integer.parseInt(String.valueOf(debt[5])));
            dd.setTanggal(calendar.getDate());
            dd.setUsername1(from.getSelectedItem().toString());
            dd.setUsername2(to.getSelectedItem().toString());
            dd.setJumlah(Integer.parseInt(amount.getText()));
            dd.setStatus(status.getSelectedIndex());
            listener.editDebt(dd);
        }

        if (e.getSource().equals(cancel)) {
            listener.cancelDebt(this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource().equals(amount)) {
            char chara = e.getKeyChar();
            if (!(((chara >= '0') && (chara <= '9') || (chara == KeyEvent.VK_BACK_SPACE) || (chara == KeyEvent.VK_DELETE)))) {
                getToolkit().beep();
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
}
