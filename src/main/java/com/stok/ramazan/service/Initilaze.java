package com.stok.ramazan.service;

import com.stok.ramazan.dao.interfaces.ILisansDao;
import com.stok.ramazan.entity.*;
import com.stok.ramazan.helper.EnumUtil;
import com.stok.ramazan.helper.EnumUtil.UserType;
import com.stok.ramazan.helper.Helper;
import com.stok.ramazan.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Component
public class Initilaze implements ApplicationRunner {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IConductService conductService;
    @Autowired
    private IAddresService addresService;
    @Autowired
    private IFirmaService firmaService;
    @Autowired
    private ILisansDao lisansDao;

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
            exist = roleService.getAll().size() == 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return exist;
    }

    private void createDefaultRoles() {
        UserType[] userTypes = EnumUtil.UserType.values();
        for (UserType userType : userTypes) {
            Role role = new Role();
            role.setYetkiAdi(userType.name());
            role.setYetkiAciklamasi("Role Degeri: " + userType.ordinal() + "Role Value: " + userType.name());
            roleService.add(role);
        }
    }

    public void create() throws IOException {
        createDefaultRoles();
        Role role = roleService.getRoleByName("Admin");
        try {

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

            Conduct conduct = new Conduct();
            conduct.setTelNo("12345");
            conduct.setContactType(EnumUtil.ContactTipi.GENEL);
            conductService.add(conduct);

            user.setLstConduct(Arrays.asList(conduct));

            user.setAdi("test verisi");
            user.setSoyadi("test verisi");
            user.setUserType(UserType.FIRMA);
            user.setUserName("admin");
            user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
            user.setRole(role);
            userService.add(user);
            Firma firma = new Firma();
            Address address = new Address();
            address.setAdres("test");
            address.setAdresTipi(EnumUtil.AddresTipi.GENEL);
            addresService.add(address);

            firma.setAdress(address);
            firma.setUser(user);
            firma.setFirmaAdi("TEST");
            firma.setLstConduct(user.getLstConduct());

            firmaService.add(firma);

            Lisans lisans = new Lisans();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            lisans.setFirma(firma);
            lisans.setLicenseFinishDate(formatter.parse("2400-12-31"));
            lisans.setLicenseStartDate(formatter.parse("1900-01-01"));
            String lisansKey = Helper.generateUnique();
            lisans.setLicenseKey(lisansKey);
            lisansDao.add(lisans);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}