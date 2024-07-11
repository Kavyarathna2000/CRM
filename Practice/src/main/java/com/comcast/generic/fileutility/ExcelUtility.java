package com.comcast.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName,int rowNum,int cellNUm) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("./testData/TestScriptdata.xlsx");
		Workbook w=WorkbookFactory.create(fis1);
		String s=w.getSheet(sheetName).getRow(rowNum).getCell(cellNUm).toString();
		w.close();
		return s;
	}
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("./testData/TestScriptdata.xlsx");
		Workbook w=WorkbookFactory.create(fis1);
		int rowCount=w.getSheet(sheetName).getLastRowNum();
		w.close();
		return rowCount;
	}
	public void setDataintoExcel(String sheetName,int rowNum,int cellNUm,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("./testData/TestScriptdata.xlsx");
		Workbook w=WorkbookFactory.create(fis1);
		w.getSheet(sheetName).getRow(rowNum).createCell(cellNUm);
		FileOutputStream fos=new FileOutputStream("./testData/TestScriptdata.xlsx");
		w.write(fos);
		w.close();
	}
}
