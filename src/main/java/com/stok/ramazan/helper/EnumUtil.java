package com.stok.ramazan.helper;

public class EnumUtil {
    public enum EntityState {
        PASSIVE(0, "Pasif"), ACTIVE(1, "Aktif");
        private final Integer id;
        private final String name;

        private EntityState(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static EntityState parse(Integer id) {
            for (EntityState entityState : EntityState.values()) {
                if (entityState.getId().equals(id)) {
                    return entityState;
                }
            }
            return null;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum EmployeeType {
        PATRON, ISCİ, ARAC
    }

    public enum TelOrAddres {
        TELEFON, ADDRES
    }


    public enum UserType {
        FIRMA(0, "Firma"), ADMIN(1, "Admin"), CALISAN(2, "CalisanDTO");
        private final Integer id;
        private final String name;

        private UserType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static UserType parse(Integer id) {
            for (UserType userType : UserType.values()) {
                if (userType.getId().equals(id)) {
                    return userType;
                }
            }
            return null;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum JasperReportType {
        PDF(0, "Pdf"), EXCEL(1, "Excel"), WORD(3, "Word"), HTML(4, "Html");
        private final Integer id;
        private final String name;

        private JasperReportType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static JasperReportType parse(Integer id) {
            for (JasperReportType jasperReportType : JasperReportType.values()) {
                if (jasperReportType.getId().equals(id)) {
                    return jasperReportType;
                }
            }
            return null;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public enum AdresKullaniciTipi {
        EMPLOYEE, FIRMA, MUSTERI, STOK
    }

    public enum AddresTipi {
        EV, IS, GENEL
    }

    public enum ContactTipi {
        EV, CEP, IS, GENEL
    }

    public enum FirmaTipi {
        KAYITLI_FIRMA, USTLENICI_FIRMA
    }

    public enum MusteriTipi {
        MUSTERI, KEFIL
    }

    public enum OdemeTipi {
        GELIR, GIDER
    }

    public enum UnitType {
        ADET, KG, ML
    }
}