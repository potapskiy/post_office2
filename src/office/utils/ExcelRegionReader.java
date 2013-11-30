package office.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import office.dao.DepartmentsDAO;
import office.dao.RegionsDAO;
import office.dao.TownsDAO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class ExcelRegionReader {

	@SuppressWarnings("unused")
	private String filePath;
	private Workbook wb;

	/**
	 * Class constructor
	 * 
	 * @param filePath
	 *            - path to file
	 * @throws IOException
	 */
	public ExcelRegionReader(String filePath) throws IOException {
		this.filePath = filePath;
		InputStream myxls = new FileInputStream(filePath);
		if (isFileInExcelFormat(filePath) == 1) {
			this.wb = new HSSFWorkbook(myxls);
		} else if (isFileInExcelFormat(filePath) == 2) {
			this.wb = new XSSFWorkbook(myxls);
		}
	}

	/**
	 * Function checks if file has xls or xlsx extension or other
	 * 
	 * @param path
	 * @return 1 if xls format, 2 if xlsx format, 0 - other
	 */
	private int isFileInExcelFormat(String path) {

		if (path.endsWith(".xls")) {
			return 1;
		} else if (path.endsWith(".xlsx")) {
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * Function changes cell type to String
	 * 
	 * @param cell
	 *            - cell witch type is changing
	 * @return HSSFCell
	 */
	private Cell setStringCellType(Cell cell) {
		Cell res = cell;
		res.setCellType(HSSFCell.CELL_TYPE_STRING);
		return res;
	}



	private void getRegions() {
		Sheet sheet = wb.getSheet("list");
		
		TownsDAO dDAO = new TownsDAO();
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row rowItem = sheet.getRow(i);
			
			String raj = setStringCellType(rowItem.getCell(5)).getStringCellValue().replaceAll(" ", "");
			
			boolean  notReg = raj.isEmpty();
			
			String code = setStringCellType(rowItem.getCell(6)).getStringCellValue().replaceAll(" ", "");
			if (!notReg){
			//	code = code + "001";
				System.out.println(raj+"  "+code+"001");
				dDAO.insertTown(Integer.parseInt(code), raj);
			}
		}

	
	}
	
	
	private void getRegionsNames() {
		Sheet sheet = wb.getSheet("reg");
		
		RegionsDAO dDAO = new RegionsDAO();
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row rowItem = sheet.getRow(i);
			
			String code = setStringCellType(rowItem.getCell(0)).getStringCellValue().replaceAll(" ", "");
			String raj = setStringCellType(rowItem.getCell(1)).getStringCellValue().replaceAll(" ", "");
			
			boolean  notReg = raj.isEmpty();
			
			
			if (!notReg){
			//	code = code + "001";
				System.out.println(raj+"  "+code);
				dDAO.insertRegion(Integer.parseInt(code), raj);
			}
		}

	
	}
	
	public static void main(String[] args) throws IOException {
		
		ExcelRegionReader r = new ExcelRegionReader("regions.xls");
		//r.getRegions();
		r.getRegionsNames();
	}

	

}
