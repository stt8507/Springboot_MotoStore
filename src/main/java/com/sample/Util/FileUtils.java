package com.sample.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

import javax.servlet.http.Part;


/**
 * 檔案上傳工具包
 */
public class FileUtils {
	/**
	 *
	 * @param file     檔案
	 * @param path     檔案存放路徑
	 * @param fileName 原始檔名
	 * @return
	 */
	public static void upload(Part part, String path, String fileName) {
		//使用原檔名
		String realPath = path + fileName;
		try {
			new File(realPath).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(realPath);
		try (InputStream in = part.getInputStream(); OutputStream out = new FileOutputStream(realPath)) {
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}
	}
}
