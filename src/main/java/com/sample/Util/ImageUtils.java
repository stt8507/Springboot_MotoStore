package com.sample.Util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtils {
	String openUrl; // 原始圖片開啟路徑
    String saveUrl; // 新圖儲存路徑
    String saveName; // 新圖名稱
    String suffix; // 新圖型別 只支援gif,jpg,png

    public ImageUtils(String openUrl, String saveUrl, String saveName,
            String suffix) {
        this.openUrl = openUrl;
        this.saveName = saveName;
        this.saveUrl = saveUrl;
        this.suffix = suffix;
    }

    /**
     * 圖片縮放.
     *     
     * @param width 需要的寬度
     * @param height 需要的高度
     * @throws Exception
     */
    public void zoom(int width, int height) throws Exception {
        double sx = 0.0;
        double sy = 0.0;
        File file = new File(openUrl);
        if (!file.isFile()) {
            throw new Exception("ImageDeal>>>" + file + " 不是一個圖片檔案!");
        }
        BufferedImage bi = ImageIO.read(file); // 讀取該圖片
        // 計算x軸y軸縮放比例--如需等比例縮放，在呼叫之前確保引數width和height是等比例變化的
        sx = (double) width / bi.getWidth();
        sy = (double) height / bi.getHeight();
        AffineTransformOp op = new AffineTransformOp(
                AffineTransform.getScaleInstance(sx, sy), null);
        File sf = new File(saveUrl, saveName + "." + suffix);
        Image zoomImage = op.filter(bi, null);
        try {
            ImageIO.write((BufferedImage) zoomImage, suffix, sf); // 儲存圖片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
