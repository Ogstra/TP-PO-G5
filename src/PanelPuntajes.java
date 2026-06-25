import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelPuntajes extends JPanel {
    private List<String[]> puntajes;
    private volatile boolean volver = false;

    public PanelPuntajes() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setBackground(new Color(30, 30, 30));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Monospaced", Font.PLAIN, 18));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVolver.addActionListener(e -> {
            synchronized (this) {
                volver = true;
                notifyAll();
            }
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelBoton.setBackground(Color.BLACK);
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public void actualizar() {
        puntajes = Puntajes.leer();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.CYAN);
        g.setFont(new Font("Monospaced", Font.BOLD, 36));
        String titulo = "TOP 10 PUNTAJES";
        int anchoTitulo = g.getFontMetrics().stringWidth(titulo);
        g.drawString(titulo, getWidth() / 2 - anchoTitulo / 2, 80);

        if (puntajes == null || puntajes.isEmpty()) {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Monospaced", Font.PLAIN, 22));
            g.drawString("No hay puntajes guardados.", getWidth() / 2 - 160, getHeight() / 2);
            return;
        }

        g.setFont(new Font("Monospaced", Font.PLAIN, 24));
        int margenIzq = getWidth() / 2 - 280;
        int y = 150;
        for (int i = 0; i < puntajes.size(); i++) {
            String nombre = puntajes.get(i)[0];
            String puntos = puntajes.get(i)[1];
            String linea = String.format("%2d.  %-20s %s", i + 1, nombre, puntos);

            g.setColor(i == 0 ? Color.YELLOW : Color.WHITE);
            g.drawString(linea, margenIzq, y);
            y += 44;
        }
    }

    public synchronized void esperarVolver() throws InterruptedException {
        volver = false;
        while (!volver) wait();
    }
}
