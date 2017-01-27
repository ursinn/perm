package net.CrazyCraftLand.PermissonSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 
 * Permisson System
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class PermUtils {
	
	@Deprecated
	public void createDefaultTabels() {
		if (Main.getInstance().UseMySQL) {
			//TODO
		} else {
			System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
		}
	}
	
	public class PlayerAPI {
		
		private boolean MySQL = Main.getInstance().UseMySQL;
		private UUID uuid;
		
		public PlayerAPI(UUID UUID) {
			uuid = UUID;
		}
		
		public int getGroup() {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "User";
				InsertUserInDB();
				ResultSet rs = Main.getInstance().getMySQL()
						.getResult("SELECT Group From " + Tabel + " WHERE UUID='" + uuid + "'");
				try {
					while (rs.next()) {
						return rs.getInt("Group");
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PlayerAPI.getGroup() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return 0;
		}
		
		public void setGroup(int GroupID) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "User";
				InsertUserInDB();
				Main.getInstance().getMySQL()
						.update("UPDATE " + Tabel + " SET Group='" + GroupID + "' WHERE UUID='" + uuid + "'");
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		private void InsertUserInDB() {
			String Tabel = Main.getInstance().TabelPrefix + "User";
			Main.getInstance().getMySQL().update("INSERT INTO " + Tabel + " (UUID, Group) VALUES ('"+uuid+"', '1')");
		}

		@Deprecated
		public void setSpecialPermissons(ArrayList<String> list) {
			if (MySQL) {
				//TODO
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		@Deprecated
		public List<String> getSpecialPermissons() {
			if (MySQL) {
				//TODO
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return null;
		}
	}
	
	public class PermAPI {
		
		private boolean MySQL = Main.getInstance().UseMySQL;
		private int GroupID;
		
		public PermAPI(int Group) {
			GroupID = Group;
		}
		
		public List<String> getPermissons() {
			List<String> list = new ArrayList<String>();
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Permissions";
				ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM `"+Tabel+"`");
				try {
					while (rs.next()) {
						if (rs.getInt("Group") == GroupID) {
							list.add(rs.getString("Permission"));
						}
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.getPermissons() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return list;
		}
		
		public List<String> getPermissons(int Group) {
			List<String> list = new ArrayList<String>();
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Permissions";
				ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM `"+Tabel+"`");
				try {
					while (rs.next()) {
						if (rs.getInt("Group") == Group) {
							list.add(rs.getString("Permission"));
						}
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.getPermissons() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return list;
		}
		
		public void addPermisson(String Permisson) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Permissions";
				Main.getInstance().getMySQL().update("INSERT INTO " + Tabel + " (Group, Permission) VALUES ('"+GroupID+"', '"+Permisson+"')");
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		public void addPermissons(ArrayList<String> list) {
			for(int i=0; i<list.size(); i++) {
				addPermisson(list.get(i));
			}
		}

		/**
		 * @return List<String>
		 */
		public List<String> getGroupPermisonsOrdet() {
			List<String> list = new ArrayList<String>();
			for (int i=0; i<=GroupID; i++) {
				list.addAll(getPermissons(i));
			}
			return list;
		}
		
	}

	/**
	 * @param uuid
	 * @return PlayerAPI
	 */
	public PlayerAPI PlayerAPI(UUID uuid) {
		return new PlayerAPI(uuid);
	}
	
	/**
	 * @param GroupID
	 * @return PermAPI
	 */
	public PermAPI PermAPI(int GroupID) {
		return new PermAPI(GroupID);
	}
}
