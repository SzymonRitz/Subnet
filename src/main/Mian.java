package main;

import java.util.Arrays;

public class Mian {

	public static void main(String[] args) {

		String rangeAddress = null;

		Reader r = new Reader();
		Ip ip = new Ip();
		try {
			rangeAddress = r.getIPAdress();
		} catch (Exception e) {
			System.out.println("Podaj adres IP jeszcze raz format\np. 100.0.18.0/23");
			rangeAddress = r.getIPAdress();
		}
		
		int mask = ip.getMask(rangeAddress);
		rangeAddress = ip.removeMask(rangeAddress);

		String[] tmp = rangeAddress.split("[.]");

		int[] singleIP = hexTOBin(rangeAddress); // konwersja do binarki

		int[] interfaces = r.getInterfaces();
		Arrays.sort(interfaces);
		reverse(interfaces);
		System.out.println();
		
		for (int i = 0; i < interfaces.length; i++) {
			System.out.println("next:");
			System.out.println();
			System.out.println(interfaces[i] + ": " + (32-ip.rang(interfaces[i])));
			show(ip.host(singleIP, ip.rang(interfaces[i])));
			ip.binToHex(ip.host(singleIP, ip.rang(interfaces[i])));
			System.out.println();
			show(ip.first(singleIP, ip.rang(interfaces[i])));
			ip.binToHex(ip.first(singleIP, ip.rang(interfaces[i])));
			System.out.println();
			show(ip.last(singleIP, ip.rang(interfaces[i])));
			ip.binToHex(ip.last(singleIP, ip.rang(interfaces[i])));
			System.out.println();
			show(ip.broadcast(singleIP, ip.rang(interfaces[i])));
			ip.binToHex(ip.broadcast(singleIP, ip.rang(interfaces[i])));
			System.out.println();
			singleIP = ip.nextSubNetwork(ip.broadcast(singleIP, ip.rang(interfaces[i])), ip.rang(interfaces[i]), 31);
		}
		System.out.println("end");
		System.out.println();
	}

	public static int[] convert(int number) { // konwersja
		int tmp = number;
		int[] bajt = new int[8];
		for (int i = 7; i > 0; i--) {
			bajt[i] = tmp % 2;
			tmp /= 2;
		}
		return bajt;
	}

	public static int[] hexTOBin(String addres) { //wywołanie konwersji na całym adresie

		String[] adr = addres.split("[.]");

		int[] tmpA = convert(Integer.parseInt(adr[0]));
		int[] tmpB = convert(Integer.parseInt(adr[1]));
		int[] tmpC = convert(Integer.parseInt(adr[2]));
		int[] tmpD = convert(Integer.parseInt(adr[3]));

		int[] singleIP = new int[32];

		for (int j = 0; j < 7; j++) {
			singleIP[j] = tmpA[j];
		}
		for (int j = 8; j <= 15; j++) {
			singleIP[j] = tmpB[j - 8];
		}
		for (int j = 16; j <= 23; j++) {
			singleIP[j] = tmpC[j - 16];
		}
		for (int j = 24; j <= 31; j++) {
			singleIP[j] = tmpD[j - 24];
		}
		return singleIP;
	}

	static void show(int[] tab) { 
		int j = 0;
		for (int i = 0; i < tab.length; i++) {
			if (j == 8) {
				System.out.print(" ");
				j = 0;
			}
			System.out.print(tab[i]);
			j++;
		}
		System.out.print("	");
	}
	
	static void reverse(int[]tab){
		for(int i = 0; i < tab.length / 2; i++)
		{
		    int temp = tab[i];
		    tab[i] = tab[tab.length - i - 1];
		    tab[tab.length - i - 1] = temp;
		}
	}

}
