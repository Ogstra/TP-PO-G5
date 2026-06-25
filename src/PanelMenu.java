import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    private volatile String eleccion = null;

    public PanelMenu() {
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("SKY DEFENSE", SwingConstants.CENTER);
        titulo.setForeground(Color.CYAN);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 52));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 60, 0);
        add(titulo, gbc);

        gbc.insets = new Insets(10, 100, 10, 100);
        for (String opcion : new String[]{"Jugar", "Puntajes", "Salir"}) {
            gbc.gridy++;
            add(crearBoton(opcion), gbc);
        }
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(30, 30, 30));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Monospaced", Font.PLAIN, 22));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            synchronized (this) {
                eleccion = texto;
                notifyAll();
            }
        });
        return btn;
    }

    public synchronized String esperarEleccion() throws InterruptedException {
        while (eleccion == null) wait();
        String result = eleccion;
        eleccion = null;
        return result;
    }
}
