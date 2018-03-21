package com.sortwaretest.wanshao;

public class bbb {
	public void selectNum() {
		for (int n = 10; n <= 99; n++) {
			for (int m = 10; m <= 99; m++) {
				if (isRepeat(n, m)) {
					continue;
				} else {
					System.out.println("×éºÏÊÇ" + n + "," + m);
				}
			}
		}
	}

	public boolean isRepeat(int n, int m) {
		int[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int s = n + m;
		while (n != 0) {
			a[n % 10] = 1;
			n = n / 10;
		}

		while (m != 0) {
			a[m % 10] = 1;
			m = m / 10;
		}

		while (s != 0) {
			if (a[s % 10] == 1) {
				return true;
			}
			s = s / 10;
		}
		return false;
	}

	public static void main(String args[]) {
		new bbb().selectNum();
	}

}
