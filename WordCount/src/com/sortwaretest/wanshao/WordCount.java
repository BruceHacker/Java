package com.sortwaretest.wanshao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordCount {

	private static int ch, num, line, codeline, newline, noteline;
	private static String catalog1 = "E:\\ѧϰ\\Eclipse\\WordCount\\src\\com\\sortwaretest\\wanshao\\";
	private static String catalog2;
	private static String[] str;
	private static File file;

	public static void main(String[] args) {
		printDirection();
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("���������");
			String str0 = scan.nextLine();
			str = str0.split(" ");
			catalog2 = str[0];
			if (str[0].equals("-c")) {
				characterCount();
			} else if (str[0].equals("-w")) {
				wordCount();
			} else if (str[0].equals("-l")) {
				lineCount();
			} else if (str[0].equals("-o")) {
				save();
			} else if (str[0].equals("-a")) {
				newLineCount();
			} else if (str[0].equals("-#")) {
				break;
			}
			System.out.println("---------------------------------------");
			System.out.println("����-#����");
		}
	}

	public static void printDirection() {
		System.out.println("---------------------------------------");
		System.out.println("�÷���[-parameter] [input_file_name]");
		System.out.println("    ��[-parameter] [output_file_name]");
		System.out.println("����[-parameter]ѡ�������-c,-w,-l,-o,-a");
		System.out.println("  -c [input_file_name]  �����ļ����ַ���");
		System.out.println("  -w [input_file_name]  �����ļ��ĵ�����");
		System.out.println("  -l [input_file_name]  �����ļ�������");
		System.out.println("  -a [input_file_name]  �����ļ��Ĵ�����/ע����/����");
		System.out.println("  -o [output_file_name] ����������ָ���ļ�");
		System.out.println("����-#����");
		System.out.println("---------------------------------------");
	}

	public static boolean isWord(int ch) {
		if (ch == 9 || ch == 10 || ch == 13 || ch == 32 || ch == 40 || ch == 46
				|| ch == 59 || ch == 123 || ch == 124 || ch == 125) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNewLine(int ch) {
		if (ch == 10) {
			return true;
		} else
			return false;
	}

	public static void characterCount() {
		file = new File(catalog1 + str[1]);
		int length = (int) file.length();
		System.out.println(str[1] + " , �ַ����� " + length);
		try {
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , �ַ����� " + length + "\r\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void wordCount() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(catalog1
					+ str[1]));
			while (br.read() != -1) {
				ch = br.read();
				if (isWord(ch) == false) {
					num += 1;
				}
			}
			System.out.println(str[1] + " , �������� " + num);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , �������� " + num + "\r\n");
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("�Ҳ������ļ���");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void lineCount() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(catalog1 + str[1]));
			while (br.readLine() != null) {
				line++;
			}
			System.out.println(str[1] + " , ������ " + line);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , ������ " + line + "\r\n");
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void newLineCount() {
		String s = null;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(catalog1 + str[1]));
			boolean comm = false;
			while ((s = br.readLine()) != null) {
				if (s.startsWith("/*") && s.endsWith("*/")) {
					noteline++;
				} else if (s.trim().startsWith("//")) {
					noteline++;
				} else if (s.startsWith("/*") && !s.endsWith("*/")) {
					noteline++;
					comm = true;
				} else if (!s.startsWith("/*") && s.endsWith("*/")) {
					noteline++;
					comm = false;
				} else if (comm) {
					noteline++;
				} else if (s.trim().length() < 1) {
					newline++;
				} else {
					codeline++;
				}
			}
			System.out.println(str[1] + " , ������/ע����/����  " + codeline + "/"
					+ noteline + "/" + newline);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , ������/ע����/����  " + codeline + "/" + noteline
					+ "/" + newline + "\r\n");
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("result.txt"));
			FileWriter fw = new FileWriter(new File("output.txt"), true);
			while (br.readLine() != null) {
				String str = br.readLine();
				fw.write(str + "\r\n");
			}
			fw.flush();
			fw.close();
			System.out.println("�ѱ��浽output.txt��");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}