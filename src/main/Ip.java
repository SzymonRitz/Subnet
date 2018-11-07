package main;

import java.util.Arrays;

public class Ip {

	static int[] powerOfBit = { 128, 64, 32, 16, 8, 4, 2, 1 };

	public Ip() {
		// TODO Auto-generated constructor stub
	}

	void getSubNetworks() {

	}

	public String removeMask(String str) {
		return str.substring(0, str.length() - 3);
	}

	public int getMask(String address) {
		int mask = Integer.parseInt(address.substring(address.length() - 2));
		address = removeMask(address);
		return mask;
	}

	int rang(int number) {
		int power = 0;
		int value = 1;
		while (number > value - 2) {
			power++;
			value *= 2;
		}
		return power;
	}

	int[] host(int[] tab, int mask) {
		int[] tmp = Arrays.copyOf(tab, tab.length);
		for (int i = 31; i >= tmp.length - mask; i--) {
			tmp[i] = 0;
		}
		return tmp;
	}

	int[] first(int[] tab, int mask) {
		int[] tmp = Arrays.copyOf(tab, tab.length);
		tmp[31] = 1;
		return tmp;
	}

	int[] last(int[] tab, int mask) {
		int[] tmp = Arrays.copyOf(tab, tab.length);
		for (int i = 30; i >= tmp.length - mask; i--) {
			tmp[i] = 1;
		}
		tmp[31] = 0;
		return tmp;
	}

	int[] broadcast(int[] tab, int mask) {
		int[] tmp = Arrays.copyOf(tab, tab.length);
		for (int i = 31; i >= tmp.length - mask; i--) {
			tmp[i] = 1;
		}
		return tmp;
	}

	int[] nextSubNetwork(int[] tab, int mask, int index) {
		int[] tmp = Arrays.copyOf(tab, tab.length);
		int i = 31;
		while (tmp[i] != 0) {
			tmp[i] = 0;
			i--;
		}
		tmp[i] = 1;
		return tmp;
	}

	void binToHex(int[] tab) {
		int first = 0;
		int second = 0;
		int third = 0;
		int last = 0;
		for (int j = 0; j <= 7; j++) {
			if (tab[j] == 1){
				first += powerOfBit[j];
			}
		}
		for (int j = 8; j <= 15; j++) {
			if (tab[j] == 1){
				second += powerOfBit[j - 8];
			}
		}
		for (int j = 16; j <= 23; j++) {
			if (tab[j] == 1){
				third += powerOfBit[j - 16];
			}
		}
		for (int j = 24; j <= 31; j++) {
			if (tab[j] == 1){
				last += powerOfBit[j - 24];
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(first)
		.append(".").append(second)
		.append(".").append(third)
		.append(".").append(last);
		System.out.println(sb.toString());
	}

}
