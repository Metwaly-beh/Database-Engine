package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import static java.nio.file.Files.delete;

public class DBApp
{
	static int dataPageSize = 2;
	
	public static void createTable(String tableName, String[] columnsNames)
	{
		FileManager.storeTable(tableName,new Table(tableName,columnsNames));

	}
	
	public static void insert(String tableName, String[] record)
	{
		Table t = FileManager.loadTable(tableName);
		t.InsertRecord(record);
		FileManager.storeTable(tableName, t);
	}
	
	public static ArrayList<String []> select(String tableName)
	{
		Table t = FileManager.loadTable(tableName);
		ArrayList<String[]> result = t.PrintTable();
		FileManager.storeTable(tableName, t);
		return result;
	}
	
	public static ArrayList<String []> select(String tableName, int pageNumber, int recordNumber)
	{

		Table t = FileManager.loadTable(tableName);
		ArrayList<String[]> result = t.tawa7od(pageNumber, recordNumber);
		FileManager.storeTable(tableName, t);
		return result;
	}
	
	public static ArrayList<String []> select(String tableName, String[] cols, String[] vals)
	{

		Table t = FileManager.loadTable(tableName);
		ArrayList<String[]> result = t.ConditionalTable(cols, vals);
		FileManager.storeTable(tableName, t);
		return result;
	}
	
	public static String getFullTrace(String tableName)
	{
		Table t = FileManager.loadTable(tableName);
		StringBuilder sb = new StringBuilder();
		for (String s : t.getFullTrace())
			sb.append(s).append("\n");
		int totalRecords = 0;
		for (Page p : t.getPages())
			totalRecords += p.getRecordList().size();
		sb.append("Pages Count: ").append(t.getPages().size())
				.append(", Records Count: ").append(totalRecords);
		return sb.toString();
	}
	
	public static String getLastTrace(String tableName)
	{
		
		return FileManager.loadTable(tableName).getTrace();
	}


	public static void main(String []args) throws IOException
	{
		String[] cols = {"id","name","major","semester","gpa"};
		createTable("student", cols);
		String[] r1 = {"1", "stud1", "CS", "5", "0.9"};
		insert("student", r1);
		String[] r2 = {"2", "stud2", "BI", "7", "1.2"};
		insert("student", r2);
		String[] r3 = {"3", "stud3", "CS", "2", "2.4"};
		insert("student", r3);
		String[] r4 = {"4", "stud4", "DMET", "9", "1.2"};
		insert("student", r4);
		String[] r5 = {"5", "stud5", "BI", "4", "3.5"};
		insert("student", r5);
		System.out.println("Output of selecting the whole table content:");
		ArrayList<String[]> result1 = select("student");
		for (String[] array : result1) {
			for (String str : array) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println("Output of selecting the output by position:");
		ArrayList<String[]> result2 = select("student", 1, 1);
		for (String[] array : result2) {
			for (String str : array) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println("Output of selecting the output by column condition:");
		ArrayList<String[]> result3 = select("student", new String[]{"gpa"}, new
				String[]{"1.2"});
		for (String[] array : result3) {
			for (String str : array) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------");
		System.out.println("Full Trace of the table:");
		System.out.println(getFullTrace("student"));
		System.out.println("--------------------------------");
		System.out.println("Last Trace of the table:");
		System.out.println(getLastTrace("student"));
		System.out.println("--------------------------------");
		System.out.println("The trace of the Tables Folder:");
		System.out.println(FileManager.trace());
		FileManager.reset();
		System.out.println("--------------------------------");
		System.out.println("The trace of the Tables Folder after resetting:");
		System.out.println(FileManager.trace());
	}
	
	
}
