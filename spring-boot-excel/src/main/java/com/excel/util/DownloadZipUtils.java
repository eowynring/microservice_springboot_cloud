package com.excel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.excel.entity.AddDataTrendResponse;
import com.excel.query.GetStatisticsExportQry;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;

/**
 * @author guofei
 * @date 2022/4/27 5:04 PM
 */
public class DownloadZipUtils {

  /**
   *
   * @param queryList  查询数据的参数
   * @param response
   */
  private void downloadZip(List<GetStatisticsExportQry> queryList, HttpServletResponse response){
    ServletOutputStream outputStream = null;
    ZipOutputStream zipOutputStream = null;
    try {
      response.setCharacterEncoding("utf-8");
      response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
      String fileNameEncode = URLEncoder.encode("文件名" + System.currentTimeMillis(), "UTF-8");
      response.setHeader("Content-Disposition", "attachment;filename=" + fileNameEncode + ".zip");
      outputStream = response.getOutputStream();
      zipOutputStream = new ZipOutputStream(outputStream);
      //boolean isSupper = userPermissionManager.isSupperAdmin(HttpRequestContext.getUserId());
      for (int i = 0; i < queryList.size(); i++) {
        GetStatisticsExportQry qry = queryList.get(i);
        //stationDataReportManager.assembleQry(qry, isSupper);
        //新增
        ExcelWriter writer = EasyExcel.write().excelType(ExcelTypeEnum.XLS).build();

        getWriter(qry, writer);

        //创建压缩文件
        ZipEntry zipEntry = new ZipEntry(qry.getExcelName() + ".xls");
        zipOutputStream.putNextEntry(zipEntry);
        Workbook workbook = writer.writeContext().writeWorkbookHolder().getWorkbook();
        //将excel对象以流的形式写入压缩流
        workbook.write(zipOutputStream);

      }
      zipOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //关闭数据流，注意关闭的顺序
      try {
        zipOutputStream.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void getWriter(GetStatisticsExportQry qry, ExcelWriter writer) {
    writeData1(qry, writer);
    writeData2(qry, writer);
  }

  private void writeData1(GetStatisticsExportQry qry, ExcelWriter writer) {
    // 填充数据
    List<AddDataTrendResponse> addDataTrendList = new ArrayList<>();
    addDataTrendList.add(new AddDataTrendResponse("2022-01-01",1,1));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-02",2,2));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-03",3,3));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-04",4,4));
    WriteSheet sheet = EasyExcel.writerSheet("sheet1")
        .head(AddDataTrendResponse.class).build();
    writer.write(addDataTrendList, sheet);
  }

  private void writeData2(GetStatisticsExportQry qry, ExcelWriter writer) {
    // 填充数据
    List<AddDataTrendResponse> addDataTrendList = new ArrayList<>();
    addDataTrendList.add(new AddDataTrendResponse("2022-01-01",1,1));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-02",2,2));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-03",3,3));
    addDataTrendList.add(new AddDataTrendResponse("2022-01-04",4,4));
    WriteSheet sheet = EasyExcel.writerSheet("sheet2")
        .head(AddDataTrendResponse.class).build();
    writer.write(addDataTrendList, sheet);
  }
}

