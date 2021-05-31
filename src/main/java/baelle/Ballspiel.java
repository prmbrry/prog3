package baelle;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;

/**
 * Steuerungsklasse für eine Ball-Animation
 * @author Doro
 *
 */
public class Ballspiel {
	private BallFrame f;
	private Set<Ball> alleBaelle;
		
	/**
	 * erstellt die Steuerungsklasse für die angegebene Oberfläche
	 * @param f
	 */
	public Ballspiel(BallFrame f)
	{
		this.f = f;
		this.alleBaelle = new HashSet<>();
	}
	
	/**
	 * startet einen Ball auf der Oberfläche und lässt ihn hüpfen
	 */
	public void ballStarten()
	{
		Random r = new Random();
		int dauer = r.nextInt(500) + 1000 ; //Zufallszahl zwischen 1000 und 1500
		Ball b = new Ball(f.getZeichenflaeche());
		b.huepfen(dauer);
		alleBaelle.add(b);
	}
	
	/**
	 * hält alle Bälle auf der Oberfläche an, so dass sie an ihrer aktuellen Position
	 * stehen bleiben
	 */
	public void baelleStoppen()
	{
		alleBaelle.forEach(b -> b.stopBall());
	}

	/**
	 * lässt alle angehaltenen Bälle wieder weiter hüpfen
	 */
	public void baelleWeiter() {
		alleBaelle.forEach(b -> b.continueBall());
//		alleBaelle.forEach(b -> b.setDx(2));
//		alleBaelle.forEach(b -> b.setDy(2));
	}

	/**
	 * löscht alle Bälle von der Oberfläche
	 */
	public void alleLoeschen() {
		alleBaelle.forEach(b -> b.deleteBall());
		alleBaelle.clear();
//		alleBaelle.forEach(b -> b.stopThis());
//		alleBaelle.clear();

	}
}




