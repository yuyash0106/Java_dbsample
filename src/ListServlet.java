
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String sql;
		RequestDispatcher disp;
		/////////////////////
		//データベース処理
		/////////////////////
		String mes = "";
		String page = "/list.jsp";

		/////////////////////
		//URLセットする
		String url = "jdbc:mysql://localhost:3306/jv82?characterEncording=utf8";
		Connection con=null;
		Statement st=null;

		/////////////////////
		//Class.forName
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("err1");

			mes="classfornameでエラー";
			request.setAttribute("mes",mes);

			disp=request.getRequestDispatcher("/err.jsp");
			disp.forward(request,response);
			return;
		}
		try {
			con=DriverManager.getConnection(url,"root","");
			st=con.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("err2");

			mes="DriverManager,stでエラー";
			request.setAttribute("mes", mes);
			disp=request.getRequestDispatcher("/err.jsp");
			disp.forward(request, response);
			return;
		}
		///////////////////////////////////
		//SQL文の作成
		sql="select * from product";
		//System.out.println(sql);
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

		///////////////////////////////////
		//SQL実行
		try {
			ResultSet rs = null;;
			rs = st.executeQuery(sql);

			while (rs.next()) {
				ArrayList<String> rec = new ArrayList<String>();

				rec.add(rs.getString("produnt_no"));
				rec.add(rs.getString("produnt_name"));
				rec.add(rs.getString("produnt_price"));
				table.add(rec);
			  }
			}catch (SQLException e) {
				System.out.println("sql err");
			}

			request.setAttribute("TABLE", table);
			disp = request.getRequestDispatcher(page);
			disp.forward(request, response);

		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
