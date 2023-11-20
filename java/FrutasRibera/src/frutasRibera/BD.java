package frutasRibera;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;



public class BD {
	private static BD miInstancia=null; //objeto de la clase BD
	private static boolean permitirInstancianueva; //para ver si existe solo 1 obj
	private String cadenaConexion;
	private String usuario;
	private String password;
	private Connection conn;
	private Statement stmt;


	BD() throws Exception {
		if(!permitirInstancianueva)
			throw new Exception("No se puede crear otro objeto de esta clase. Usa getInstance()");
		// crea el objeto
		/*else
		if(!cargarParametrosConexion())
			throw new Exception("No se encuentran los parámetros de conexión");
	 */
	}
	
public boolean  cargarParametrosConexion ()  {
		
		
		//AQUI SE LEE UN FICHERO TXT PARA COGER 3 COSAS SEPARADAS POR GUIONES
		  
	   String cadena ="";
       String archivoParametros = "configTienda.txt";
       File file = new File(archivoParametros);
       String texto = "";
       int cont = 0;
       
       if(file.exists()) {
    	   Scanner entrada = null; 
    	   try {
			 entrada =  new Scanner(file);
		     } catch (FileNotFoundException e) {
			 return false;   
		    }
    	   //while (entrada.hasNext()) {         //mientras tengas filas por leer...
    	   cadena = entrada.nextLine();          //lee la primera linea, se usa para saltarlas tambien
    	   System.out.println("Línea leída del archivo: " + cadena);
    	   
    	   String[] param = cadena.split("-");
			//	if (param[0].equals("cadena")) {
					setCadenaConexion(param[0]);
					System.out.println("Parámetro cadena de conexión cargado: " + param[0]);
					cont++;
			//	} else if (param[0].equals("usuario")) {
					setUsuario(param[1]);
					System.out.println("Parámetro usuario cargado: " + param[1]);
					cont++;
				//} else if (param[0].equals("password")) {
					setPassword(param[2]);
					System.out.println("Parámetro Password cargado: " + param[2]);
					cont++;
				//}

		}
	//}while
       
return cont == 3;
          
}

	
	
	
	
	
	public static BD getInstance() {
		//vamos a ver si el obj (miinstancia) es nulo  o no.
		if(miInstancia==null ) { //en este if solo entra 1 vez, despues ya no null
			permitirInstancianueva=true;
			try {
				miInstancia=new BD();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			permitirInstancianueva=false;
		}
		
		return miInstancia;
	}



	 public  ResultSet consultaBD(String consulta) throws SQLException {
		  //"jdbc:oracle:thin:@localhost:1521:XE","jardineria","jardineria"
		  // jdbc:oracle:thin:@localhost:1521:XE-tiendaFruta-tiendaFruta

		 DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());

		 conn = DriverManager.getConnection(cadenaConexion, usuario, password);


		  stmt = conn.createStatement();
		  ResultSet rset =stmt.executeQuery(consulta);
		 /*  while (rset.next())
				 System.out.println ("Son: "+rset.getString(1));   // Print col 1*/
		  //  stmt.close();
			return rset;

	 }



	 public void cerrarConsulta() throws SQLException {
		stmt.close();
	 }

	

	public String getCadenaConexion() {
		return cadenaConexion;
	}

	public void setCadenaConexion(String cadenaConexion) {
		this.cadenaConexion = cadenaConexion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	



//debo crearme un main para probar que carga los parametros de conexion y me carga la consulta a bd de los empleados

  public static void main(String[] args)  {

BD.getInstance().cargarParametrosConexion();
System.out.println();
//BD.getInstance().cargaEmpleados();

  }
  
//Añade a la clase BD.java el siguiente método para cargar de la BD los que son empleados (!0)

public ArrayList<Empleado>cargaEmpleados() {
	BD miConexion=BD.getInstance(); //necesitas conectarte
	
	ArrayList<Empleado> misTrab= new ArrayList<>();
			 
			//voy a consultar mi bd para que me de los valores que sean distintos de 0		 
	try {
		ResultSet rset=miConexion.consultaBD("SELECT dni,nombre,apellidos,direccion,turno FROM PERSONA where turno!=0");
		
		 while (rset.next()) {
			 misTrab.add(new Empleado(rset.getString(1), rset.getString(2), rset.getString(3),rset.getString(4), rset.getInt(5)));
				//ojito que turno es un int y por tanto un getint
			 System.out.println ("Son empleados: " + rset.getString(1) + ' ' + rset.getString(2) + ' ' + rset.getString(3) +  ' ' +
				      rset.getString(4) +  " con el turno:" +  rset.getInt(5)); 
		 }
        
	 miConexion.cerrarConsulta();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	
	//debo meter la consulta leida en un arraylist y ponerlo en el return
    }
	 return misTrab;  
   
  } 
  
  
  
 
}


