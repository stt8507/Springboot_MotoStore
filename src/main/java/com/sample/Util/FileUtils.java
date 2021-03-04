package com.sample.Util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


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
	public static boolean upload(MultipartFile file, String path, String fileName) {
		//使用原檔名
		String realPath = path + "/" + fileName;
		File dest = new File(realPath);
		//判斷檔案父目錄是否存在
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}
		try {
			//儲存檔案
			file.transferTo(dest);
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
