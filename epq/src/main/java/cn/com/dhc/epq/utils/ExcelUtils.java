package cn.com.dhc.epq.utils;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {
	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @param sheetName
	 * @param titles
	 * @param datas
	 * @param wb
	 * @return
	 */
	public static boolean exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName,
			String sheetName, List<String> titles, List<List<Object>> datas, HSSFWorkbook wb) {
		try {
			// Excel文件对象
			if (wb == null) {
				wb = new HSSFWorkbook();
			}
			// Sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 标题行
			HSSFRow row = sheet.createRow(0);
			// 样式
			HSSFCellStyle style = wb.createCellStyle();
//			style.setAlignment(HSSFC); // 创建一个居中格式
			HSSFCell cell = null;

			// 填充标题
			int titleIndex = 0;
			for (String title : titles) {
				cell = row.createCell(titleIndex);
				cell.setCellValue(title);
				cell.setCellStyle(style);
				titleIndex++;
			}

			// 填充数据
			int rowIndex = 1;
			for (List<Object> data : datas) {
				row = sheet.createRow(rowIndex);
				int cellIndex = 0;
				for (Object dataEle : data) {
					HSSFCell dataCell = row.createCell(cellIndex);
					dataCell.setCellValue(dataEle.toString());
					dataCell.setCellStyle(style);
					cellIndex++;
				}
				rowIndex++;
			}

			// 响应到客户端
			try {
				// 设置响应头
				response.reset();
				response.setContentType("application/msexcel");
				response.setHeader("Content-Disposition", "attachment;filename=" + processFileName(request, fileName));
				// 流输出
				OutputStream out = response.getOutputStream();
				wb.write(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * 解决中文乱码
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String processFileName(HttpServletRequest request, String fileName) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// IE
				codedfilename = URLEncoder.encode(fileName, "UTF8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				codedfilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}

}
