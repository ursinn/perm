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
	
	public void createDefaultTabels() {
		if (Main.getInstance().UseMySQL) {
			checkUser_MySQL();
			checkPerm_MySQL();
			checkUserPerm_MySQL();
			checkGroup_MySQL();
		} else {
			System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
		}
	}
	
	private void checkUser_MySQL() {
		String Tabel = Main.getInstance().TabelPrefix + "User";
		ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM information_schema.tables WHERE table_schema = '"+Main.getInstance().MySQL_Database+"' AND table_name = '"+Tabel+"' LIMIT 1;");
		try {
			while (rs.next()) {
				return;
			}
		} catch (SQLException e) {
			Main.getInstance().getMySQL().update("CREATE TABLE IF NOT EXISTS `"+Tabel+"` (`id` int(11) NOT NULL, `UUID` varchar(64) NOT NULL, `Group` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` ADD PRIMARY KEY (`id`);");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
		}
	}
	
	private void checkPerm_MySQL() {
		String Tabel = Main.getInstance().TabelPrefix + "Permissions";
		ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM information_schema.tables WHERE table_schema = '"+Main.getInstance().MySQL_Database+"' AND table_name = '"+Tabel+"' LIMIT 1;");
		try {
			while (rs.next()) {
				return;
			}
		} catch (SQLException e) {
			Main.getInstance().getMySQL().update("CREATE TABLE IF NOT EXISTS `"+Tabel+"` (`id` int(11) NOT NULL, `Permission` varchar(250) NOT NULL, `Group` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` ADD PRIMARY KEY (`id`);");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
		}
	}
	
	private void checkUserPerm_MySQL() {
		String Tabel = Main.getInstance().TabelPrefix + "UserPerms";
		ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM information_schema.tables WHERE table_schema = '"+Main.getInstance().MySQL_Database+"' AND table_name = '"+Tabel+"' LIMIT 1;");
		try {
			while (rs.next()) {
				return;
			}
		} catch (SQLException e) {
			Main.getInstance().getMySQL().update("CREATE TABLE IF NOT EXISTS `"+Tabel+"` (`id` int(11) NOT NULL, `UUID` varchar(64) NOT NULL, `Permission` varchar(250) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` ADD PRIMARY KEY (`id`);");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
		}
	}
	
	private void checkGroup_MySQL() {
		String Tabel = Main.getInstance().TabelPrefix + "Groups";
		ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM information_schema.tables WHERE table_schema = '"+Main.getInstance().MySQL_Database+"' AND table_name = '"+Tabel+"' LIMIT 1;");
		try {
			while (rs.next()) {
				return;
			}
		} catch (SQLException e) {
			Main.getInstance().getMySQL().update("CREATE TABLE IF NOT EXISTS `"+Tabel+"` (`id` int(11) NOT NULL, `Name` varchar(250) NOT NULL, `Prefix` varchar(250) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` ADD PRIMARY KEY (`id`);");
			Main.getInstance().getMySQL().update("ALTER TABLE `"+Tabel+"` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
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

		public void addSpecialPermissions(ArrayList<String> list) {
			if (MySQL) {
				for(int i=0; i<list.size(); i++) {
					addSpecialPermission(list.get(i));
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		public void addSpecialPermission(String Permission) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "UserPerms";
				Main.getInstance().getMySQL().update("INSERT INTO " + Tabel + " (UUID, Permission) VALUES ('"+uuid+"', '"+Permission+"')");
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		public List<String> getSpecialPermissons() {
			List<String> list = new ArrayList<String>();
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "UserPerms";
				ResultSet rs = Main.getInstance().getMySQL().getResult("SELECT * FROM `"+Tabel+"`");
				try {
					while (rs.next()) {
						if (rs.getString("UUID") == uuid.toString()) {
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
	}
	
	public class PermAPI {
		
		private boolean MySQL = Main.getInstance().UseMySQL;
		private int GroupID;
		
		public PermAPI() {
			GroupID = 0;
		}
		
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
		
		public void addPermisson(String Permission) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Permissions";
				Main.getInstance().getMySQL().update("INSERT INTO " + Tabel + " (Group, Permission) VALUES ('"+GroupID+"', '"+Permission+"')");
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
		
		public String getPrefix() {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				ResultSet rs = Main.getInstance().getMySQL()
						.getResult("SELECT Prefix From " + Tabel + " WHERE id='" + GroupID + "'");
				try {
					while (rs.next()) {
						return rs.getString("Prefix");
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.getPrefix() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return null;
		}
		
		public String getName() {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				ResultSet rs = Main.getInstance().getMySQL()
						.getResult("SELECT Name From " + Tabel + " WHERE id='" + GroupID + "'");
				try {
					while (rs.next()) {
						return rs.getString("Name");
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.getName() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return null;
		}
		
		public boolean ExistGroupName(String name) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				ResultSet rs = Main.getInstance().getMySQL()
						.getResult("SELECT id From " + Tabel + " WHERE Name='" + name + "'");
				try {
					return rs.next();
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.ExistGroupName() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return false;
		}
		
		public int getGroupID(String name) {
			if(!ExistGroupName(name)) {
				return 0;
			}
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				ResultSet rs = Main.getInstance().getMySQL()
						.getResult("SELECT id From " + Tabel + " WHERE Name='" + name + "'");
				try {
					while (rs.next()) {
						return rs.getInt("id");
					}
				} catch (SQLException e) {
					if (Main.getInstance().Debug) {
						System.err.println(e.getMessage());
					}
					System.err.println(
							"ERROR!!! - PermAPI.getGroupID() Exeption - Contact the developer for more informations!");
				}
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return 0;
		}
		
		public void setPrefix(String prefix) {
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				Main.getInstance().getMySQL().update("UPDATE " + Tabel + " SET Prefix='" + prefix + "' WHERE id='" + GroupID + "'");
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
		}
		
		public boolean registerGroup(String name, String prefix) {
			if(ExistGroupName(name)) {
				return false;
			}
			if (MySQL) {
				String Tabel = Main.getInstance().TabelPrefix + "Groups";
				Main.getInstance().getMySQL().update("INSERT INTO " + Tabel + " (Name, Prefix) VALUES ('"+name+"', '"+prefix+"')");
				return true;
			} else {
				System.err.println("Error: Diese Version Unterstützt nur MySQL!"); //FIXME: UPDATE MySQL Alternativ
			}
			return false;
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
	
	/**
	 * @return PermAPI
	 */
	public PermAPI PermAPI() {
		return new PermAPI();
	}
}
