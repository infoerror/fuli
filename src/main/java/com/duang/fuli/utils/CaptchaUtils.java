package com.duang.fuli.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaUtils {
	private static final int WIDTH = 100, HEIGHT = 18;


	private static Font mFont = new Font("Times New Roman", Font.PLAIN, 17);

	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static void writeImg2Resp(HttpServletResponse resp, HttpSession httpSession,String sessionFlag) {
		// 在内存中生成图片
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 255));
		g.fillRect(1, 1, WIDTH, HEIGHT);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
		g.setFont(mFont);
		g.setColor(getRandColor(160, 200));

		// 画随机线条
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(WIDTH - 1);
			int y = random.nextInt(HEIGHT - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			g.drawLine(x, y, x1, y1);
		}

		// 从另一个方向画随机线条
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(WIDTH - 1);
			int y = random.nextInt(HEIGHT - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			g.drawLine(x, y, x - x1, y - y1);
		}
		
		//生成随机数字然后将数字转换为字母
		String sRand = "";
		for(int i=0; i<6; i++){
			int itmp = random.nextInt(26)+65;
			//将随机数字转换为字母
			char ctmp = (char)itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20+random.nextInt(110),
					20+random.nextInt(110),20+random.nextInt(110)));
			//将字母与依次绘画到图片里面去
			g.drawString(String.valueOf(ctmp), 15*i+5, 16);
		}
		
		//将随机产生的字符串放在session，用于验证码的判断
		httpSession.setAttribute(sessionFlag, sRand);
		g.dispose();
		
		//设置响应的文件头
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		//告诉浏览器返回的是图片，不是html页面
		resp.setContentType("image/jpeg");
		
		try {
			ImageIO.write(image, "JPEG", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("验证码生成出错");
		}
	}

}
