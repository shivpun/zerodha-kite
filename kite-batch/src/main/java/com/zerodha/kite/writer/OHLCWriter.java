package com.zerodha.kite.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemWriter;

public class OHLCWriter implements ItemWriter<List<Map<Integer, Object[]>>> {
	
	private int cntRow = 0;

	@Override
	public void write(List<? extends List<Map<Integer, Object[]>>> items) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		int cnt = 0;
		List<Map<Integer, Object[]>> masf = items.get(0);
		for(Map<Integer, Object[]> maps:masf) {
		Map<Integer, Object[]> mapObj = maps;
		for (Map.Entry<Integer, Object[]> obj : mapObj.entrySet()) {
			if (obj.getKey().equals(1)) {
				cnt = Integer.valueOf((String) obj.getValue()[0]);
				sheet = workbook.createSheet("TimeFrame_" + cnt);
			}
			Row row = sheet.createRow(this.cntRow);
			int cellnum = 0;
			for (Object o : obj.getValue()) {
				Cell cell = row.createCell(cellnum);
				if(o instanceof Double) {
					cell.setCellValue((double)o);
				} else {
					cell.setCellValue(String.valueOf(o));
				}
				cellnum = cellnum + 1;
			}
			this.cntRow = this.cntRow + 1;
		}
		}
		File file = new File("HDFCBANK_15_FEB_2019_"+String.valueOf(cnt)+".xlsx");
		System.out.println(file.getAbsolutePath());
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
	}
}
