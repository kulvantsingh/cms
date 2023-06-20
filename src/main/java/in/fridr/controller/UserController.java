package in.fridr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.fridr.entity.QB_mapping;
import in.fridr.entity.UserDetails;
import in.fridr.modal.MessageResponse;
import in.fridr.service.ClientServices;

import java.io.Closeable;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import antlr.collections.List;  

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private Environment environment;
	@Autowired
	private ClientServices clientService;
	
	@PostMapping("/saveUserDetails")
	public ResponseEntity<UserDetails> saveUserDetailsmethod(@RequestBody UserDetails userDetails) {
		UserDetails userInfo = null;
		try {
		 userInfo=clientService.getUserDetails(userDetails);
		 return	new ResponseEntity<UserDetails>(userInfo,HttpStatus.OK);
		}
		catch(Exception e) {
		return	new ResponseEntity<UserDetails>(userInfo,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		
			
	}
	
	@GetMapping("/saveUserDetails1")
	public String save1UserDetailsmethod() {
		QB_mapping QBInfo = null;
		try {
			
			//obtaining input bytes from a file  
			FileInputStream fis=new FileInputStream(new File("F:\\QBDIFFICULTY_JAVA_PROGRAME\\SchemeofShuffling.xlsx"));  
			//creating workbook instance that refers to .xls file  
			XSSFWorkbook workbook=new XSSFWorkbook(fis);   
			//creating a Sheet object to retrieve the object  
			XSSFSheet sheet=workbook.getSheetAt(0);
			int setArray[]=new int[4];
			LinkedHashMap<String,java.util.List<String>> hm= new LinkedHashMap<String, java.util.List<String>>();
			java.util.List<String> questionsAlias=new ArrayList<String>();
			java.util.List<java.util.List<String>> questionsAliasList=new ArrayList<java.util.List<String>>();
			//iterator for excel sheet

			Iterator rows=sheet.rowIterator();

			while(rows.hasNext()) {
				XSSFRow row=(XSSFRow) rows.next();
				//System.out.println(row.getCell(1)+" "+row.getCell(4));
				
				//hm.put(row.getCell(0).toString(), null);
				questionsAlias.add(row.getCell(0).toString());
				//System.out.println(hm);
				Iterator cells=row.cellIterator();
				/*
				 * while(cells.hasNext()) { System.out.println(cells.); }
				 */
			}

			for (int i = 0; i < questionsAlias.size(); i++) {
				
				java.util.List<String> questionNumbers=new ArrayList<String>();
				int temp=0;
				int alias=0;
				for(int j=0;j<4;j++) {
					/*
					 * FileInputStream fis1=new FileInputStream(new
					 * File("C:\\Users\\kulvantk\\Downloads\\SchemeofShuffling.xlsx")); //creating
					 * workbook instance that refers to .xls file XSSFWorkbook workbook1=new
					 * XSSFWorkbook(fis); //creating a Sheet object to retrieve the object
					 */	XSSFSheet sheet1=workbook.getSheetAt(0);
						int setArray1[]=new int[4];
						
						//iterator for excel sheet
						if(j==0) {
							
							temp=1;
							alias=0;
						}
						else if(j==1)
						{
							temp=4;alias=3;
						}
						else if(j==2) {
							temp=7;alias=6;
						}
						else if(j==3) {
							temp=10;alias=9;}
						Iterator rows1=sheet1.rowIterator();
						while(rows1.hasNext()) {
						
							XSSFRow row1=(XSSFRow) rows1.next();
							
							if(row1.getCell(alias).toString().equals(questionsAlias.get(i).toString())) {
								questionNumbers.add(row1.getCell(temp).getStringCellValue());
							}
						
						}
						
						
				}
				
				questionsAliasList.add(questionNumbers);
				
			}
			for(int i = 0; i < questionsAlias.size(); i++) {
				//System.out.println(questionsAliasList.get(i));
				hm.put(questionsAlias.get(i),questionsAliasList.get(i));
			}
			fis.close();
			((Closeable) workbook).close();


			  for(Map.Entry<String,java.util.List<String>> entry : hm.entrySet()) {
				  QB_mapping qb=new QB_mapping();
				  qb.setQb_desc(entry.getKey());
				  qb.setSet1_qno(Integer.parseInt(entry.getValue().get(0).substring(1)));
				  qb.setSet2_qno(Integer.parseInt(entry.getValue().get(1).substring(1)));
				  qb.setSet3_qno(Integer.parseInt(entry.getValue().get(2).substring(1)));
				  qb.setSet4_qno(Integer.parseInt(entry.getValue().get(3).substring(1)));
				  try {
				clientService.saveQBMapping(qb);
				  }
				  catch (Exception e) {
					// TODO: handle exception
					  System.out.println(e);
				}
			  System.out.println("Key = " + entry.getKey() + ", Value = " +
			  entry.getValue()); }
			 
			
			
			
		 return	"hello";
		}
		catch(Exception e) {
		return	"hello";
		}
	
		//TotalCount t=new TotalCount();

		//t.countValueFromExcel(hm);
			
	}

}
