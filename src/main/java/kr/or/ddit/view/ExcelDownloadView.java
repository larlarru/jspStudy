package kr.or.ddit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.user.model.UserVo;

public class ExcelDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition", "attachement; filename=test.xlsx");
		
		// header : List<String>
		// data : List<UserVo>
		
		List<String> header = (List<String>)model.get("header");
		List<UserVo> data = (List<UserVo>)model.get("data");
		
		// excel 파일 생성
		XSSFWorkbook book = new XSSFWorkbook();	// java코드를 이용해서 만듬
		Sheet sheet = book.createSheet("users");
		
		int rownum = 0;
		int colnum = 0;
		
		Row row = sheet.createRow(rownum++);
		
		for(String h : header) {
			
			Cell cell = row.createCell(colnum++);
			cell.setCellValue(h);
			
		}
		
		
		for(UserVo userVo : data) {
			colnum = 0;
			row = sheet.createRow(rownum++);
			
			row.createCell(colnum++).setCellValue(userVo.getUserid());
			row.createCell(colnum++).setCellValue(userVo.getUsernm());
			row.createCell(colnum++).setCellValue(userVo.getAlias());
			
//			Cell cell = row.createCell(colnum++);
//			cell.setCellValue(userVo.getUserid());
//			Cell cell2 = row.createCell(colnum++);
//			cell2.setCellValue(userVo.getUsernm());
//			Cell cell3 = row.createCell(colnum++);
//			cell3.setCellValue(userVo.getAlias());
			
		}
		
		// data
		
		
		book.write(response.getOutputStream());
		
		
		
	}
	
	
	
}
