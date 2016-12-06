/*
 * @(#)IndyListSV.java
 *
 * Copyright (c) 1998 Karl Moss. All Rights Reserved.
 * You may study, use, modify, and distribute this software for any
 * purpose provided that this copyright notice appears in all copies.
 * This software is provided WITHOUT WARRANTY either expressed or
 * implied.
 */

import javax.servlet.*;
import javax.servlet.http.*;

import Domain.*;

import java.sql.*;
import java.util.Collection;

/**
 * <p>This is a simple servlet that will use JDBC to gather all
 * of the Indy 500 winner  information from a database and format it
 * into an HTML table. This servlet uses HttpSessions to keep
 * track of the position within the ResultSet so that the
 * table can be split into several different pages, each with
 * a 'Next n rows' link.
 */
public class servlet extends HttpServlet	{

	/**
	 * <p>Initialize the servlet. This is called once when the
	 * servlet is loaded. It is guaranteed to complete before any
	 * requests are made to the servlet
	 * @param cfg Servlet configuration information
	 */
	public void init(ServletConfig cfg)
			throws ServletException		{
		super.init(cfg);
	}

	/**
	* <p>Destroy the servlet. This is called once when the servlet
	* is unloaded.
	*/
	public void destroy()		{
		super.destroy();
	}

	/**
	 * <p>Performs the HTTP POST operation
	 * @param req The request from the client
	 * @param resp The response from the servlet
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException		{
		/*	Same as get																	*/
		doGet(req, resp);
	}

	/**
	 * <p>Performs the HTTP GET operation
	 * @param req The request from the client
	 * @param resp The response from the servlet
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		 throws ServletException, java.io.IOException	{
	 	

		/*	Get the URI, Set the content type, and create a PrintWriter		*/
		String uri = req.getRequestURI();
		resp.setContentType("text/html");
		java.io.PrintWriter out = new java.io.PrintWriter(resp.getOutputStream());

		/*	Print the HTML header														*/
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Composers In the DB</title>");
		out.println("</head>");
		out.println("<h2><center>");
		out.println("Compoers");
		out.println("</center></h2>");
		out.println("<br>");

		/*	Create any addition properties necessary for connecting
		 *	to the database, such as user and password
		 */
		try{
			Collection<Composer> composer = Composer.findAll();
			System.out.println(composer.size() );
			System.out.println(composer.toString());
			Collection<Composistions> compositions = Composistions.findAll(); 
			
			
			
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		
		
		java.util.Properties props = new java.util.Properties();
		props.put("user", "indywinner");
		props.put("password", "Indy500");
		
		query("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost/indywinners?verifyServerCertificate=false&useSSL=true",
					props,
					"SELECT * from IndyWinners where year < " +
					lastYear + " order by year desc",
					out,
					uri);

		/*	Wrap up																		*/
		out.println("</html>");
		out.flush();
		out.close();
	}


	/**
	 * <p>Given the JDBC driver name, URL, and query string,
	 * execute the query and format the results into an
	 * HTML table
	 * @param driverName JDBC driver name
	 * @param connectionURL JDBC connection URL
	 * @param props Addition connection properties, such as user
	 * and password
	 * @param query SQL query to execute
	 * @param out PrintWriter to use to output the query results
	 * @param uri Request URI
	 * @return true if the query was successful
	 */
	private boolean query(String driverName,
								 String connectionURL,
								 java.util.Properties props,
								 String query,
								 java.io.PrintWriter out,
								 String uri)		{
		boolean rc = true;

		Connection con = null;		/*	JDBC Connection object						*/
		Statement stmt = null;		/*	JDBC Statement Object						*/
		ResultSet rs = null;			/*	JDBC Result Object							*/

		/*	Keep stats for how long it takes to execute the query					*/
		long startMS = System.currentTimeMillis();
		/*	Keep the number of rows in the ResultSet									*/
		int rowCount = 0;

		try {
			/*	Create an instance of the JDBC driver so that it has
			 *	a chance to register itself
			 */
			Class.forName(driverName).newInstance();

			/*	Create a new database connection.										*/
			con = DriverManager.getConnection(connectionURL, props);

			/*	Create a statement object that we can execute queries	with		*/
			stmt = con.createStatement();

			/*	Execute the query																*/
			rs = stmt.executeQuery(query);

			/*	Format the results into an HTML table									*/
			rowCount = formatTable(rs, out, uri);
		} catch (Exception ex)		{
			/*	Send the error back to the client										*/
			out.println("***** Exception!*****\n");
			ex.printStackTrace(out);
			rc = false;
		} finally	{
			try {
				/*	Always close properly													*/
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex)		{
				/*	Ignore any errors here													*/
			}
		}

		/*	If we queried the table successfully, output some statistics		*/
		if (rc) {
			long elapsed = System.currentTimeMillis() - startMS;
			out.println("<br><i>" + rowCount + " rows in " +
			elapsed + "ms</i>");
		}

		return rc;
	}

	/**
	 * <p>Given a JDBC ResultSet, format the results into
	 * an HTML table
	 * @param rs JDBC ResultSet
	 * @param out PrintWriter to use to output the table
	 * @param uri Requesting URI
	 * @return The number of rows in the ResultSet
	 */
	private int formatTable(java.sql.ResultSet rs,
									java.io.PrintWriter out,
									String uri)
			throws Exception		{

		int rowsPerPage = 10;
		int rowCount = 0;

		/*	Keep track of the last year found												*/
		String lastYear = "";

		/*	This will be true if there is still more data in the table				*/
		boolean more = false;

		/*	Create the table																		*/
		out.println("<center><table border>");

		/*	Process the results. First dump out the column
		 *	headers as found in the ResultSetMetaData
		 */
		ResultSetMetaData rsmd = rs.getMetaData();

		int columnCount = rsmd.getColumnCount();

		/*	Start the table row																	*/
		out.println("<tr>");

		for (int i = 0; i < columnCount; i++) {
			/*	Create each table header. Note that the column index is 1-based	*/
			out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
		}

		/*	End the table row																		*/
		out.println("</tr>");
		
		/*	Now walk through the entire ResultSet and get each row					*/
		while (rs.next()) {
			rowCount++;

			/* Start a table row																	*/
			out.println("<tr>");

			// Dump out the values of each row
			for (int i = 0; i < columnCount; i++) {
				/*	Create the table data. Note that the column index is 1-based	*/
				String data = rs.getString(i + 1);
				out.println("<td>" + data + "</td>");

				/*	If this is the year column, cache it									*/
				if (i == 0) {
					lastYear = data;
				}
			}

			/*	End the table row																	*/
			out.println("</tr>");

			/*	If we are keeping track of the maximum number of
			 *	rows per page and we have exceeded that count
			 *	break out of the loop
			 */
			if ((rowsPerPage > 0) && (rowCount >= rowsPerPage))	{
				/*	Find out if there are any more rows after this one					*/
				more = rs.next();
				break;
			}
		}

		/*	End the table																			*/
		out.println("</table></center>");

		if (more)	{
			/*	Create a 'Next' button															*/
			out.println("<form method=POST action=\"" + uri + "\">");
			out.println("<center>");
			out.println("<input type=submit value=\"Next " + rowsPerPage + " rows\">");
			out.println("</center>");

			/* Page was filled. Put in the last year that we saw						*/
			out.println("<input type=hidden name=lastYear value=" + lastYear + ">");
			out.println("</form>");
		}

		return rowCount;
	}

}