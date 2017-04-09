package com.stok.ramazan.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.stok.ramazan.entity.Role;
import com.stok.ramazan.entity.User;
import com.stok.ramazan.helper.EnumUtil.UserType;
import com.stok.ramazan.service.interfaces.IRoleService;
import com.stok.ramazan.service.interfaces.IUserService;

@Component
public class Initilaze implements ApplicationRunner {
	private IRoleService roleService;
	private IUserService userService;

	@Autowired
	public Initilaze(IRoleService roleService, IUserService userService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		if (check() == true) {
			System.out.println("init ilk kez çalıştı basladi...");
			create();
		} else {
			System.out.println("init daha öncede çalıştı...");
		}

	}

	public boolean check() {
		boolean exist = false;
		try {
			if (roleService.getAll().size() == 0) {
				exist = true;
			} else {
				exist = false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exist;
	}

	public void create() throws IOException {

		Role role = new Role();

		try {
			role.setYetkiAdi("ROLE_ADMIN");
			role.setYetkiAciklamasi("Admin Rölü...");
			roleService.add(role);
			/*
			 * // Ileride kullanılmak amacıyla yazılmıştır ClassLoader
			 * classLoader = Initialize.class.getClassLoader(); File imageFile =
			 * new File(classLoader.getResource("unknown.jpg").getFile());
			 * 
			 * byte[] imageInByte; BufferedImage originalImage =
			 * ImageIO.read(imageFile); try {
			 * 
			 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 * ImageIO.write(originalImage, "jpg", baos); baos.flush();
			 * imageInByte = baos.toByteArray(); baos.close(); InputStream in =
			 * new ByteArrayInputStream(imageInByte); photo =
			 * IOUtils.toByteArray(in); employee.setPhoto(photo); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
			User user = new User();
			user.setAdi("ramazan");
			user.setSoyadi("cesur");
			user.setUserType(UserType.ADMIN);
			user.setUserName("admin");
			user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
			user.setRole(role);
			userService.add(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}