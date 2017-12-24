package han;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by han on 2017/12/22.
 */
public class ExcelUtils {
    private static ExcelUtils ourInstance = new ExcelUtils();

    private static ExcelUtils getInstance() {
        return ourInstance;
    }
    private   Workbook wbWorkbook = null;
    private ExcelUtils() {
    }
    private   Workbook readerExcel(String path) {
        InputStream is = null;

        try {
            if (path.endsWith(".xls")) {
                is = new FileInputStream(path);
                wbWorkbook = new HSSFWorkbook(is);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wbWorkbook;
    }
    private Cell  readCell( int row,int col){
        if (wbWorkbook!=null){
            Sheet sheet= wbWorkbook.getSheetAt(0);
            HSSFCell cell= (HSSFCell ) sheet.getRow(row).getCell(col);
            return cell;
        }
        return null;
    }
    private String get账号(){
        return  readCell(3,1).getStringCellValue();
    }
    private String get借方交易笔数 (){
        return  readCell(6,3).getStringCellValue();
    }
    private String get贷方交易笔数(){
        return  readCell(7,3).getStringCellValue();
    }
    private int get总交易笔数  (){
        return  Integer.parseInt(get借方交易笔数())+ Integer.parseInt(get贷方交易笔数());
    }
    private  String get交易时间(int i ){
       return  readCell(10+i,0).getStringCellValue().substring(2,8);
    }
    private  String get记账日期 (int i ){
        return  readCell(10+i,8).getStringCellValue().substring(2,8);
    }
    private  String get币种(int i ){
         String cur= readCell(10+i,4).getStringCellValue();
        switch (cur){
            case "人民币元":
                return  "CNY";
            case "美元":
                return  "USD";
            default:return cur;
        }
    }
    private  String get摘要(int i ){
        return readCell(10+i,9).getStringCellValue();

    }
    private  String get备注 (int i ){
        return readCell(10+i,10).getStringCellValue();

    }
    private  String get账户明细编号  (int i ){
        return readCell(10+i,11).getStringCellValue().split("-")[0];

    }
    private  String get交易流水号  (int i ){
        return readCell(10+i,11).getStringCellValue().split("-")[1];

    }
    public static void main(String [] args){
        ExcelUtils excelUtils = ExcelUtils.getInstance();
        excelUtils.readerExcel("E:\\git\\pw-swift-core\\对账单\\37101002710051003563(1).xls");
        Cell cell=excelUtils.readCell(0,0);  // 0->
        System.out.println(cell.getStringCellValue());

        for (int i =0 ;i< excelUtils.get总交易笔数() ;i++){
            System.out.println(excelUtils.get记账日期(i) + excelUtils.get币种(i)
                    +excelUtils.get摘要(i) + excelUtils.get备注(i)+"|"+ excelUtils.get账户明细编号(i)
                    +" "+excelUtils.get交易流水号(i) );
        }
    }
}
