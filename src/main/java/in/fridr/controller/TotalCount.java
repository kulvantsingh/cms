package in.fridr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TotalCount {

	public static void main(String[] args) throws IOException {

		
	}

	public void countValueFromExcel(LinkedHashMap<String, List<String>> hm) throws IOException {
		
		int k=1;
		
		int questionKey=1;
		// to find the all four sets difficulty level.
				FileInputStream fis_findTotal=new FileInputStream(new File("C:\\Users\\kulvantk\\Downloads\\AFCAT_question_dificulty\\Cycle_7_"+k+""+".xlsx"));  
				//creating workbook instance that refers to .xls file  
				XSSFWorkbook workbook_findTotal=new XSSFWorkbook(fis_findTotal);   
				//creating a Sheet object to retrieve the object  
				//XSSFSheet sheet_findTotal=workbook_findTotal.getSheetAt(0);
				
				LinkedHashMap<String,java.util.List<Double>> hm_total= new LinkedHashMap<String, java.util.List<Double>>();
				
				
				
				
		// TODO Auto-generated method stub
		
				
		for(Map.Entry<String,java.util.List<String>> entry : hm.entrySet()) {
			java.util.List<Double> totalList=new ArrayList<Double>();
	
			double totalAppeared=0,totalAttempted=0 ,totalCorrect=0;
			System.out.println(entry.getKey()+" "+entry.getValue());
			
		    for(int i=0;i<4;i++) {
		    	XSSFSheet sheet_findTotal=workbook_findTotal.getSheetAt(0);
				Iterator rows=sheet_findTotal.rowIterator();
				rows.next();rows.next();
		    	while(rows.hasNext()) {
		    		XSSFRow row=(XSSFRow) rows.next();
					
					  
					  if((Integer.parseInt(entry.getValue().get(i).toString().substring(1))==(int)row.getCell(4).getNumericCellValue()&&(int)row.getCell(3).getNumericCellValue()==i+1)) {
						 // System.out.println(Integer.parseInt(entry.getValue().get(i).toString().substring(1))+" ="+(int)row.getCell(4).getNumericCellValue());
						  totalAppeared=totalAppeared+row.getCell(5).getNumericCellValue();
						  totalAttempted=totalAttempted+(int)row.getCell(6).getNumericCellValue();
						  totalCorrect=totalCorrect+(int)row.getCell(7).getNumericCellValue();
					   }
					 
					 
		    		
		    		
		    	}
		    	
		    }
		    totalList.add(totalAppeared);
		    totalList.add(totalAttempted);
		    totalList.add(totalCorrect);
		    totalList.add((totalCorrect*100/totalAppeared));
		    totalList.add((totalCorrect*100/totalAttempted));
		    hm_total.put(""+questionKey, totalList);
		    questionKey++;
		 
		}
		
		fis_findTotal.close();
		workbook_findTotal.close();
		
		
		
		  for(Map.Entry<String,java.util.List<Double>> entry : hm_total.entrySet()) {
		  System.out.println("Key = " + entry.getKey() + ", Value = " +
		  entry.getValue()); }
		 
		generateExcel(hm_total,k);
		
	}
	
	
	
	public void generateExcel(LinkedHashMap<String, List<Double>> hm_total,int k) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		try   
		{  
		//declare file name to be create   
		String filename = "C:\\Users\\kulvantk\\Downloads\\AFCAT_question_dificulty\\Cycle7\\AFCATCycle7_"+k+".xlsx";
		//creating an instance of HSSFWorkbook class  
		XSSFWorkbook workbook = new XSSFWorkbook();  
		//invoking creatSheet() method and passing the name of the sheet to be created   
		XSSFSheet sheet = workbook.createSheet("AFCAT"+"Cycle_"+k);   
		//creating the 0th row using the createRow() method  
		XSSFRow rowhead = sheet.createRow((short)0);  
		//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		rowhead.createCell(0).setCellValue("S.No.");  
		rowhead.createCell(1).setCellValue("Subject");  
		rowhead.createCell(2).setCellValue("Question No");  
		rowhead.createCell(3).setCellValue("CandidatesAppeared");  
		rowhead.createCell(4).setCellValue("CandidateAttempts");  
		rowhead.createCell(5).setCellValue("CorrectlyAnsweredCount");
		rowhead.createCell(6).setCellValue("DifficultyLevelAppeared");
		rowhead.createCell(7).setCellValue("DifficultyLevelAttempts");
		//creating the 1st row 
		int rowCount=0;
		for(Map.Entry<String,java.util.List<Double>> entry : hm_total.entrySet()) {
			  System.out.println("Key = " + entry.getKey() + ", Value = " +
			  entry.getValue()); 
			  XSSFRow row = sheet.createRow((short)++rowCount);
			  row.createCell(0).setCellValue(rowCount);
			  row.createCell(1).setCellValue("AFCAT");
			  row.createCell(2).setCellValue(entry.getKey());
			  row.createCell(3).setCellValue(entry.getValue().get(0));
			  row.createCell(4).setCellValue(entry.getValue().get(1));
			  row.createCell(5).setCellValue(entry.getValue().get(2));
			  row.createCell(6).setCellValue(df.format(entry.getValue().get(3)));
			  row.createCell(7).setCellValue(df.format(entry.getValue().get(4)));
		}
		 
		//inserting data in the first row  
		//row.createCell(0).setCellValue("1");  
		//row.createCell(1).setCellValue("John William");  
		//row.createCell(2).setCellValue("9999999");  
		//row.createCell(3).setCellValue("william.john@gmail.com");  
		//.createCell(4).setCellValue("700000.00");  
	
		FileOutputStream fileOut = new FileOutputStream(filename);  
		workbook.write(fileOut);  
		//closing the Stream  
		fileOut.close();  
		//closing the workbook  
		workbook.close();  
		//prints the message on the console  
		System.out.println("Excel file has been generated successfully.");  
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}  
	}
	
	
	
}
