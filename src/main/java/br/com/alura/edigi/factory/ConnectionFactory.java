package br.com.alura.edigi.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public static Connection getConexao() {

		ComboPooledDataSource cpds = new ComboPooledDataSource();

		try {
			var properties = getProperties();

			cpds.setJdbcUrl(properties.getProperty("database.url"));
			cpds.setUser(properties.getProperty("database.user"));
			cpds.setPassword(properties.getProperty("database.password"));

			return cpds.getConnection();

		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Properties getProperties() throws IOException {

		var properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/database.properties"));

		return properties;
	}
}
