package sample.utils;

import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import sample.dao.SystemUtilsDao;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class Barcode_pdf {


    public static void createImage(String image_name,String myString)  {
        try {
            EAN13Bean code128 = new EAN13Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();
            //write to png file
            SystemUtilsDao systemUtilsDao = new SystemUtilsDao();
            FileOutputStream fos = new FileOutputStream(systemUtilsDao.ExcelFilePath() +image_name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
