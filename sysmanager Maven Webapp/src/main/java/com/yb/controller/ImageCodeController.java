package com.yb.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yb.util.VerifyCodeUtil;

@Controller
@Scope("prototype")
@RequestMapping("/image")
public class ImageCodeController{
	@RequestMapping("/code")
	public void getImageCode(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String code = VerifyCodeUtil.generateVerifyCode(4);
		HttpSession session = request.getSession();
		session.setAttribute("code", code);
		BufferedImage image = VerifyCodeUtil.getImage(190, 40, code);
		ServletOutputStream os = response.getOutputStream();
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpg", os);
	}
}
