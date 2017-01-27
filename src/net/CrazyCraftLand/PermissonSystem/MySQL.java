package net.CrazyCraftLand.PermissonSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * CrazyCraftLand API
 * 
 * @author ursinn (Ursin Filli)
 * @Copyright (c) 2016 - 2017 Ursin Filli
 * @license CC-BY-SA 4.0 International
 *
 */
public class MySQL {

	public static Connection con;

	public void connect() {
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection(
						"jdbc:mysql://" + Main.getInstance().MySQL_Host + ":" + Main.getInstance().MySQL_Port + "/"
								+ Main.getInstance().MySQL_Database + "?autoReconnect=true",
						Main.getInstance().MySQL_Username, Main.getInstance().MySQL_Password);
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§aMySQL Verbindung Aufgebaut");
			} catch (SQLException e) {
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§cMySQL - Connect Error");
				if (Main.getInstance().Debug) {
					Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
							Main.getInstance().Prefix + "§cFür Mehr Details Debug Mode Auf true stellen :)");
				} else {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public void close() {
		if (isConnected()) {
			try {
				con.close();
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§aMySQL Verbindung Geschlossen");
			} catch (SQLException e) {
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§cMySQL - Close Error");
				if (Main.getInstance().Debug) {
					Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
							Main.getInstance().Prefix + "§cFür Mehr Details Debug Mode Auf true stellen :)");
				} else {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * @return true or false
	 */
	public boolean isConnected() {
		return con != null;
	}

	/**
	 * @param query
	 */
	public void update(String query) {
		if (isConnected()) {
			try {
				Statement st = con.createStatement();
				st.executeUpdate(query);
				st.close();
			} catch (SQLException e) {
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§cMySQL - Update Error");
				if (Main.getInstance().Debug) {
					Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
							Main.getInstance().Prefix + "§cFür Mehr Details Debug Mode Auf true stellen :)");
				} else {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * @param query
	 * @return rs
	 */
	public ResultSet getResult(String query) {
		ResultSet rs = null;
		if (isConnected()) {
			try {
				Statement st = con.createStatement();
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
						Main.getInstance().Prefix + "§cMySQL - Result Error");
				if (Main.getInstance().Debug) {
					Main.getInstance().sendMessage(SendMessageEnum.CONSOLE,
							Main.getInstance().Prefix + "§cFür Mehr Details Debug Mode Auf true stellen :)");
				} else {
					System.err.println(e.getMessage());
				}
			}
		}
		return rs;
	}

}
