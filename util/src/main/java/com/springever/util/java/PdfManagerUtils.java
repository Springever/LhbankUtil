package com.springever.util.java;

import java.io.File;
import java.io.FileOutputStream;

import com.lhbank.itextpdf.text.Document;
import com.lhbank.itextpdf.text.Image;
import com.lhbank.itextpdf.text.PageSize;
import com.lhbank.itextpdf.text.pdf.PdfCopy;
import com.lhbank.itextpdf.text.pdf.PdfImportedPage;
import com.lhbank.itextpdf.text.pdf.PdfReader;
import com.lhbank.itextpdf.text.pdf.PdfWriter;

public class PdfManagerUtils {

	/**
	 * 图片转PDF
	 * 
	 * @param imageUrl
	 *            ：图片路径
	 * @param mOutputPdfFileName
	 *            ：生成PDF路径
	 * @return
	 */
	public static void pdf(String imageUrl, String mOutputPdfFileName) {
		Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
		try {
			PdfWriter
					.getInstance(doc, new FileOutputStream(mOutputPdfFileName));
			doc.open();
			doc.newPage();
			Image png1 = Image.getInstance(imageUrl);
			float heigth = png1.getHeight();
			float width = png1.getWidth();
			int percent = getPercent2(heigth, width);
			png1.setAlignment(Image.MIDDLE);
			png1.scalePercent(percent + 3);// 表示是原来图像的比例;
			doc.add(png1);
			doc.close();
			File mOutputPdfFile = new File(mOutputPdfFileName);
			if (!mOutputPdfFile.exists()) {
				mOutputPdfFile.deleteOnExit();
			}
			if (!mOutputPdfFile.createNewFile()) {
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
	 * 
	 * @param h
	 * @param w
	 * @return
	 */

	public static int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	/**
	 * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的
	 *
	 */
	public static int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 590 / w * 100;// 530
		p = Math.round(p2);
		return p;
	}

	/**
	 * 合并PDF
	 * 
	 * @param files
	 *            ：合并PDF集合
	 * @param newfile
	 *            ：生成新文件
	 * @return
	 */
	public static boolean mergePdfFiles(String[] files, String newfile) {
		boolean retValue = false;
		Document document = null;
		try {
			document = new Document(new PdfReader(files[0]).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
			document.open();
			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(files[i]);
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
			retValue = true;
		} catch (Exception e) {
		} finally {
			document.close();
		}
		return retValue;
	}
}