package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reader {

	public Reader() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	public String getIPAdress() {
		String address = null;
		String pattern = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}/[0-9]{1,2}";
		Pattern r = Pattern.compile(pattern);
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj Adres");
		if (sc.hasNext(Pattern.compile(pattern))) {
			address = sc.next(pattern);
		}
		String mask = address.substring(address.length() - 2);
		return address;

	} // 100.1.18.0/23

	public int[] getInterfaces() { // 50 127 6 21 4
		String line = null;
		ArrayList<Integer> interfaces = new ArrayList<>();
		System.out.println("Podaj po spacji rozmiary podsieci np. 50 127 6 21 4");
		Scanner sc = new Scanner(System.in);
		line = sc.nextLine();
		Scanner sc1 = new Scanner(line);
		while (sc1.hasNext()) {
			interfaces.add(sc1.nextInt());
		}
		int[] tab = new int[interfaces.size()];
		for (int i = 0; i < interfaces.size(); i++) {
			tab[i] = interfaces.get(i);
		}
		sc.close();
		sc1.close();
		return tab;
	}

}
