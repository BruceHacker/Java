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
	private static String catalog1 = "E:\\学习\\Eclipse\\WordCount\\src\\com\\sortwaretest\\wanshao\\";
	private static String catalog2;
	private static String[] str;
	private static File file;

	public static void main(String[] args) {
		printDirection();
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("请输入命令：");
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
			System.out.println("输入-#结束");
		}
	}

	public static void printDirection() {
		System.out.println("---------------------------------------");
		System.out.println("用法：[-parameter] [input_file_name]");
		System.out.println("    或：[-parameter] [output_file_name]");
		System.out.println("其中[-parameter]选项包括：-c,-w,-l,-o,-a");
		System.out.println("  -c [input_file_name]  返回文件的字符数");
		System.out.println("  -w [input_file_name]  返回文件的单词数");
		System.out.println("  -l [input_file_name]  返回文件的行数");
		System.out.println("  -a [input_file_name]  返回文件的代码行/注释行/空行");
		System.out.println("  -o [output_file_name] 将结果输出到指定文件");
		System.out.println("输入-#结束");
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
		System.out.println(str[1] + " , 字符数： " + length);
		try {
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , 字符数： " + length + "\r\n");
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
			System.out.println(str[1] + " , 单词数： " + num);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , 单词数： " + num + "\r\n");
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到该文件！");
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
			System.out.println(str[1] + " , 行数： " + line);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , 行数： " + line + "\r\n");
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
			System.out.println(str[1] + " , 代码行/注释行/空行  " + codeline + "/"
					+ noteline + "/" + newline);
			FileWriter fw = new FileWriter(new File("result.txt"), true);
			fw.write(str[1] + " , 代码行/注释行/空行  " + codeline + "/" + noteline
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
			System.out.println("已保存到output.txt中");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}