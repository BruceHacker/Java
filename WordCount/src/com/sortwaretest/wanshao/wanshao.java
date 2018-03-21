package com.sortwaretest.wanshao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class wanshao {
	public static void main(String[] args) throws Exception {
		String[] a = getArrayByFile("text1.txt", new char[] { '\n' });
		String[] b = getArrayByFile("text2.txt", new char[] { '\n', ' ' });
		FileWriter c = new FileWriter("text3.txt");
		int aIndex = 0;
		int bIndex = 0;

		while (aIndex < a.length) {
			c.write(a[aIndex++] + "\n");
			if (bIndex < b.length)
				c.write(b[bIndex++] + "\n");
		}

		while (bIndex < b.length) {
			c.write(b[bIndex++] + "\n");
		}
		c.close();
	}

	public static String[] getArrayByFile(String filename, char[] seperators)
			throws Exception {
		File f = new File(filename);
		FileReader reader = new FileReader(f);
		char[] buf = new char[(int) f.length()];
		int len = reader.read(buf);
		String results = new String(buf, 0, len);
		String regex = null;
		if (seperators.length > 1) {
			regex = "" + seperators[0] + "|" + seperators[1];
		} else {
			regex = "" + seperators[0];
		}
		return results.split(regex);
	}

}
