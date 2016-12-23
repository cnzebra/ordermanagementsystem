package com.oms.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

	@ConfigurationProperties(prefix = "spring.data.mongodb")
	public class ConnectionProperties {

		private String host;

		private Integer port;
		private String dataBase;

		/*private String userName;
		private String password;*/

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getDataBase() {
			return dataBase;
		}

		public void setDataBase(String dataBase) {
			this.dataBase = dataBase;
		}

		/*public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

*/
	}


