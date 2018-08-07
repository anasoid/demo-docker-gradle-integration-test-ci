package org.github.anasoid.demo.docker.gradle.ci.web;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"}, loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

  private String login;
  private String jdbc;
  private String password;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().print("Hello, World! is a GET : " + getTextFromDatabase());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().print("Hello, World! is a POST : " + getTextFromDatabase());
  }


  String getTextFromDatabase() {

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    String URL = jdbc;
    Properties info = new Properties();
    info.put("user", login);
    info.put("password", password);
    Connection conn = null;
    String result = "";
    Statement stmt = null;
    try {
      conn = DriverManager.getConnection(URL, info);
      String meta = conn.getMetaData().getDatabaseProductVersion();

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select demo from demo");

      if (rs.next()) {
        result = rs.getString("demo");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null && !conn.isClosed()) {
          conn.close();
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public void init() throws ServletException {
    super.init();
    InputStream input = this.getServletContext()
        .getResourceAsStream("/WEB-INF/config.properties");
    Properties prop = new Properties();
    try {
      prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    jdbc = prop.getProperty("db.url");
    login = prop.getProperty("db.login");
    password = prop.getProperty("db.password");

  }
}