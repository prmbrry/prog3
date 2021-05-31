package baelle;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Oberfläche für ein Ballspiel
 * @author Doro
 *
 */
public class BallFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Ballspiel spiel;
	
	/**
	 * erstellt eine Oberfläche mit 4 Buttons
	 */
	public BallFrame() {
		setSize(400, 200);
		setTitle("Tanzende Bälle");
		spiel = new Ballspiel(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();
		uhrzeit = new JTextField();
		contentPane.add(uhrzeit, BorderLayout.NORTH);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		Thread thread = new Thread() {
			public void run() {
				while(true) {
				LocalDateTime now = LocalDateTime.now();  
				uhrzeit.setText(dtf.format(now));
				}
			}
		};
		thread.start();
		
		canvas = new JPanel();
		contentPane.add(canvas, BorderLayout.CENTER);
		JPanel p = new JPanel();
		addButton(p, "Ball starten", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.ballStarten();
			}
		});

		addButton(p, "Anhalten", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.baelleStoppen();
			}
		});
		
		addButton(p, "Weiter", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.baelleWeiter();
			}
		});
		
		addButton(p, "Leeren", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				spiel.alleLoeschen();
			}
		});
		contentPane.add(p, BorderLayout.SOUTH);
	}

	private void addButton(Container c, String title, ActionListener a) {
		JButton b = new JButton(title);
		c.add(b);
		b.addActionListener(a);
	}
	
	/**
	 * liefert die Zeichenfläche von this
	 * @return Zeichenfläche
	 */
	public JPanel getZeichenflaeche()
	{
		return this.canvas;
	}

	private JPanel canvas;
	private JTextField uhrzeit;
	
	/**
	 * startet die Ballspiel-Oberfläche und macht sie sichtbar
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		BallFrame frame = new BallFrame();
		frame.setVisible(true);
	}
}