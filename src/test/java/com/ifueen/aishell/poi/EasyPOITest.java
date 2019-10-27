package com.ifueen.aishell.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyPOITest {

    /**
     * 测试EasyPOI的导出
     * @throws Exception
     */
    @Test
    public void testCreateEasyPOI() throws Exception {

        Dpt dpt = new Dpt();
        dpt.setName("AI部");

        EasyPOI easyPOI = new EasyPOI();
        easyPOI.setUsername("韩梅梅");
        easyPOI.setAge(18);
        easyPOI.setSpeak("说明锤子说明");
        easyPOI.setImg("1.jpg");
        easyPOI.setDpt(dpt);

        EasyPOI easyPOI2 = new EasyPOI();
        easyPOI2.setUsername("老婆");
        easyPOI2.setAge(18);
        easyPOI2.setSpeak("说明锤子说明");
        easyPOI2.setImg("2.jpg");
        easyPOI2.setDpt(dpt);

        ArrayList<EasyPOI> easyPOIS = new ArrayList<>();
        easyPOIS.add(easyPOI);
        easyPOIS.add(easyPOI2);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                EasyPOI.class, easyPOIS);


        //通过流的方式输出
        FileOutputStream fileOutputStream = new FileOutputStream("s.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     *输出部门表
     */
    @Test
    public void testDPT() throws Exception{
        Dpt dpt = new Dpt();
        dpt.setName("AI部");

        Dpt dpt1 = new Dpt();
        dpt1.setName("算法部");

        //准备数据(以后是直接读取这些数据)
        List<Dpt> list = new ArrayList<>();
        list.add(dpt);
        list.add(dpt1);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                Dpt.class, list);

        //输出
        FileOutputStream out = new FileOutputStream("dpt.xls");
        workbook.write(out);
        out.close();
    }
}
