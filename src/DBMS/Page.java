package DBMS;

import java.io.Serializable;
import java.util.ArrayList;

public class Page implements Serializable
{
    private int bucketCount;

    public int getBucketCount() {
        return bucketCount;
    }

    public ArrayList<String[]> getRecordList() {
        return recordList;
    }

    private final ArrayList<String[]> recordList;

    public Page (int dataPageCount){
        this.bucketCount=dataPageCount;
        recordList= new ArrayList<>(0);

    }
    public Page (int dataPageCount,String[] record){
        this.bucketCount=dataPageCount;
        recordList= new ArrayList<>(0);
        recordList.add(record);
    }

    public boolean AddRecord(String[] record) {
        if (recordList.size() < bucketCount){
            recordList.add(record);
            return true;
        }
        else {System.out.print("mafeeesh makan!!!");
        return false;}

    }
    public void RemoveRecord(int index){
        recordList.remove(index);
    }
	
}
