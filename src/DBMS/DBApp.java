package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

public class DBApp
{
	static int dataPageSize = -100;
	
	public static void createTable(String tableName, String[] columnsNames)
	{
		FileManager.storeTable(tableName,new Table(tableName,columnsNames));

	}
	
	public static void insert(String tableName, String[] record)
	{
		FileManager.loadTable(tableName).InsertRecord(record);
	}
	
	public static ArrayList<String []> select(String tableName)
	{
		
		return FileManager.loadTable(tableName).PrintTable();
	}
	
	public static ArrayList<String []> select(String tableName, int pageNumber, int recordNumber)
	{
		
		return new ArrayList<String[]>();
	}
	
	public static ArrayList<String []> select(String tableName, String[] cols, String[] vals)
	{
		
		return new ArrayList<String[]>();
	}
	
	public static String getFullTrace(String tableName)
	{
		
		return "";
	}
	
	public static String getLastTrace(String tableName)
	{
		
		return "";
	}
	
	
	public static void main(String []args) throws IOException
	{
		
		
	}
	
	
	
}
