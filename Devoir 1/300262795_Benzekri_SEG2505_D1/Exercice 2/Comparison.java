import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Comparison {

	public static void main(String[] args) {
		int N = 10000;
		int tinM = 10;
		long debut = 0, fin = 0, aListTime, lListTime, aTime, tot = 0;
		List<Character> arrayList = new ArrayList<>();
		while (fin - debut < tinM) {
			debut = System.currentTimeMillis();
			for (int i = 0; i < N; i++) {
				arrayList.add((char) ((int) (Math.random() * 26) + 97));
			}
			fin = System.currentTimeMillis();
			if (fin - debut < tinM)
			{
				N += 10000;
			}
			
		}
		List<Character> linkedList = new LinkedList<Character>();
		char[] array = new char[N];
		for (int i = 0; i < N; i++) {
			linkedList.add((char) ((int) (Math.random() * 26) + 97));
		}

		for (int i = 0; i < N; i++) {
			array[i] = (char) ((int) (Math.random() * 26) + 97);
		}
		debut = fin = 0;
		debut = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			tot += arrayList.get(i);
		}
		fin = System.currentTimeMillis();
		aListTime  = fin - debut;
		debut = fin = 0;
		debut = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			tot += linkedList.get(i);
		}
		fin = System.currentTimeMillis();
		lListTime  = fin - debut;
		debut = fin = 0;
		debut = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			tot += array[i];
		}
		fin = System.currentTimeMillis();
		aTime = fin - debut;
		System.out.printf("%-10s%-20s%-20s%-20s\n", "N","ArrayList Time", "LinkedList Time", "Array Time");
		System.out.printf("%-10d%-20s%-20s%-20s", N, aListTime+" ms", lListTime+" ms", aTime+" ms");
	}
}