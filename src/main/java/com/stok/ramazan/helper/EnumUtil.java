package com.stok.ramazan.helper;

public class EnumUtil {
	public enum EntityState {
		PASSIVE(0, "Pasif"), 
		ACTIVE(1, "Aktif");
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
	
	public enum UserType {
		FIRMA(0, "Firma"), 
		ADMIN(1, "Admin"),
		CALISAN(2, "Calisan");
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
}
