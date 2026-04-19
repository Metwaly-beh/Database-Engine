package DBMS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Table implements Serializable
{

    public int getNoColumns() {
        return noColumns;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public String[] getFields() {
        return fields;
    }

    private final String name;
    private final int noColumns;
    private final ArrayList<Page> pages;
    private String[] fields;


    public Table(String name,String[] columnNames){

        this.noColumns= columnNames.length;
        this.name=name;
        this.pages = new ArrayList<Page>(0);
        this.fields=columnNames;
    }

    public void InsertRecord(String[] record)  {

            if(!pages.getLast().AddRecord(record)){
                AddPageToList(record);
            }


    }

    //i is index for pages j is index for buckets
    public ArrayList<String[]> PrintTable(){
        ArrayList<String[]> fullFlatTable= new ArrayList<String[]>();
        for(int i=0;i<this.pages.size()-1;i++){
           for(int j=0; j<this.pages.get(i).getBucketCount();j++){
               fullFlatTable.add(this.pages.get(i).getRecordList().get(j));
           }
        }
        return fullFlatTable;
    }

/*---------------------------------------------------------------------------------------------------------------------*/
    //Helpers

    private void AddPageToList(){
        pages.add(new Page(this.noColumns));
    }


    private void AddPageToList(String[] record){
        pages.add(new Page(this.noColumns,record));
        FileManager.storeTablePage(this.name,pages.size()-1,pages.getLast());
    }
	
}


