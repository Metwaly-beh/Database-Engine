package DBMS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Table implements Serializable {

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

    public ArrayList<String> getFullTrace() {
        return fullTrace;
    }

    public String getTrace() {
        return trace;
    }

    private final String name;
    private final int noColumns;
    private final ArrayList<Page> pages;
    private final String[] fields;
    private String trace;
    private final ArrayList<String> fullTrace;


    public Table(String name, String[] columnNames) {
        this.noColumns = columnNames.length;
        this.name = name;
        this.pages = new ArrayList<Page>();
        this.fields = columnNames;



        trace="Table created name:"+name+", columnsNames:[";
        for(int i=0;i<columnNames.length;i++){
            if (i== columnNames.length-1)
                trace += columnNames[i]+"]";
         else   trace += columnNames[i]+", ";

        }
        fullTrace=new ArrayList<String>();

        fullTrace.add(trace);


    }

    public void InsertRecord(String[] record) {

        long startTime = System.nanoTime();
        int pageIndex;
        if (pages.isEmpty() || !pages.getLast().AddRecord(record)) {
            AddPageToList(record);
            pageIndex = pages.size() - 1;
        } else {
            pageIndex = pages.size() - 1;
            FileManager.storeTablePage(this.name, pageIndex, pages.getLast());
        }
        long endTime = System.nanoTime();
        trace="Inserted:[";
        for(int i=0;i<record.length;i++){
            if (i== record.length-1)
                trace += record[i]+"], at page number:";
          else  trace += record[i]+", ";

        }
        trace+= (pages.size()-1)+", execution time (mil):"+((int)(endTime-startTime)/1000000);
        fullTrace.add(trace);
        FileManager.storeTable(this.name,this);


    }


    public ArrayList<String[]> tawa7od(int pageNumber,int recordNumber){
        long startTime = System.nanoTime();
        ArrayList<String[]> r =new ArrayList<String[]>();
        r.add(this.pages.get(pageNumber).getRecordList().get(recordNumber));
        long endTime = System.nanoTime();

        trace = "Select pointer page:" + pageNumber + ", record:" + recordNumber +", total output count:1, execution time (mil):"+((int)(endTime-startTime)/1000000);
        fullTrace.add(trace);
        FileManager.storeTable(this.name,this);
        return r;


    }

    //i is index for pages j is index for buckets
    public ArrayList<String[]> PrintTable() {
        int noPages=0;
        int noRecords=0;
        long startTime = System.nanoTime();
        ArrayList<String[]> fullFlatTable = new ArrayList<String[]>();
        for (int i = 0; i < this.pages.size() ; i++,noPages++) {
            for (int j = 0; j < this.pages.get(i).getRecordList().size(); j++,noRecords++) {
                fullFlatTable.add(this.pages.get(i).getRecordList().get(j));
            }
        }

        long endTime = System.nanoTime();

        trace="Select all pages:"+noPages+", records:"+noRecords+", execution time (mil):"+((int)(endTime-startTime)/1000000);
        fullTrace.add(trace);
        FileManager.storeTable(this.name,this);
        return fullFlatTable;
    }


    public ArrayList<String[]> ConditionalTable(String[] columns, String[] values) {
        int noRecords=0;
        long startTime = System.nanoTime();
        ArrayList<String[]> allRecords = getAllRecords();



        trace="Select condition:[";
        for(int i=0;i<columns.length;i++) {
            if (i == columns.length - 1)
                trace = "Select condition:" + Arrays.toString(columns) + "->"+ Arrays.toString(values) + ", Records per page:[";
            else  trace += columns[i] + ", ";

        }
//        for(int i=0;i<values.length;i++) {
//            if (i == values.length - 1)
//                trace += values[i] + "], Records per page:[";
//           else trace += values[i] + ", ";
//
//        }

        ArrayList<String[]> subTable = new ArrayList<String[]>();
        for (int pageIdx = 0; pageIdx < pages.size(); pageIdx++) {
            int matchCount = 0;
            for (String[] record : pages.get(pageIdx).getRecordList()) {
                boolean match = true;
                for (int i = 0; i < columns.length; i++) {
                    int temp = getColumnIndex(noColumns, fields, columns[i]);
                    if (!record[temp].equals(values[i])) { match = false; break; }
                }
                if (match) { subTable.add(record); noRecords++; matchCount++; }
            }
            if (matchCount > 0) trace += "[" + pageIdx + ", " + matchCount + "], ";
        }

        long endTime = System.nanoTime();
        if (noRecords > 0)
            trace = trace.substring(0, trace.length() - 2);
        trace+="], records:"+noRecords+", execution time (mil):"+((int)(endTime-startTime)/1000000);

        fullTrace.add(trace);
        FileManager.storeTable(this.name,this);

        return subTable;
    }


    /*---------------------------------------------------------------------------------------------------------------------*/
    //Helpers


    private void AddPageToList() {
        pages.add(new Page(DBApp.dataPageSize));
    }


    private void AddPageToList(String[] record) {
        pages.add(new Page(DBApp.dataPageSize, record));
        FileManager.storeTablePage(this.name, pages.size() - 1, pages.getLast());

    }



    private int getColumnIndex(int noColumns,String[] fields,String columnName){
        for(int i=0;i <noColumns ;i++ ) {
            if (fields[i].equals(columnName)){
                return i;
            }
        }
        return -1;
    }


    private ArrayList<String[]> getAllRecords() {
        ArrayList<String[]> all = new ArrayList<>();
        for (Page p : pages)
            all.addAll(p.getRecordList());
        return all;
    }
}


