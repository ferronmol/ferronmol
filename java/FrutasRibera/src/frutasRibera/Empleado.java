package frutasRibera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Empleado extends Persona {

	private int turno;
	private ArrayList<Pedido>pedidosAsignados; //hago 2 constructores, uno con el arraylist y otro sin el arraylist para asignar memoria
	
	
	public Empleado(String dNI, String nombre, String apellidos, String direccion, int turno,
			ArrayList<Pedido> pedidosAsignados) {
		super(dNI, nombre, apellidos, direccion);
		this.turno = turno;
		this.pedidosAsignados = pedidosAsignados;
	}
	
	
	public Empleado(String dNI, String nombre, String apellidos, String direccion, int turno) {
		super(dNI, nombre, apellidos, direccion);
		this.turno = turno;
		this.pedidosAsignados = new ArrayList<>(); //aqui guardo memoria!!!!
	}


	public ArrayList<Pedido> getPedidosAsignados() {
		return pedidosAsignados;
	}


	public void setPedidosAsignados(ArrayList<Pedido> pedidosAsignados) {
		this.pedidosAsignados = pedidosAsignados;
	}


	@Override
	public String toString() {
		return "Empleado [pedidosAsignados=" + pedidosAsignados + ", turno=" + turno + ", DNI=" + DNI + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + "]";
	}
	
//cada empleado le debe asignar los pedidos que se realizan en su turno siendo el
//	turno 1 ó 2. Lee del fichero pedidos.csv y asigna a cada empleado los pedidos que tiene que
//	preparar según el turno.	
	
	public void asignarPedidosTurno() {
		
		// LEER DEL FICHERO CSV
		
		File f=new File("Pedidos.csv");
		String cadena="";
		String linea[]=null;
		
		try {
			Scanner entrada=new Scanner(f);
			cadena=entrada.nextLine();
			while(entrada.hasNext()) {
				cadena=entrada.nextLine();
				linea=cadena.split(";");
				if(this.turno==Integer.parseInt(linea[3])) {
					this.pedidosAsignados.add(new Pedido(Integer.parseInt(linea[0]), linea[1], Double.parseDouble(linea[2]),Integer.parseInt(linea[3])));
				}
					
					
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//HE RELLENADO MI ARRAYLIST pedidosAsignados con el csv con los pedidos	
	}
	//Cada empleado debe procesar los pedidos que se le han asignado, debe
	//invocar a generarFactura para cada pedido y realizar una inserción en la tabla factura de la
	//BD que está definida como: CodigoFactura(es el string formado por
	//codigoPedido_dniCliente), codigoPedido, dniCliente, total(totalsinIVA+IVAReducido(4%)-
	//descuento del pedido)
	//La salida del método es un objeto de la clase Factura que se vuelca en un fichero de
	//texto con el nombre FacturaDNI.txt y que incluye la siguiente información...
	
	public Factura generarFactura(Pedido p) {
		String nombre="Factura"+p.getDNICliente()+".txt";
		Factura f=null;
		try {
			PrintWriter salida=new PrintWriter(new File(nombre));
			String codigoFactura=p.getCodigPedido()+"_"+p.getDNICliente();
			f=new Factura(codigoFactura, p.getCodigPedido(),p.getDNICliente(), 0);
			//Buscar los productos pedidos de ese pedido
			salida.println("Factura: "+codigoFactura);
			salida.println("Cliente: "+p.getDNICliente());
			salida.println("***************Productos***********************");
			f.setProductos(f.cargarProductos());
			int contador=1;
			for(ProductoPedido pp: f.getProductos()) {
				salida.print(contador);
				salida.println(pp.imprimirProducto());
				contador++;
			}
			salida.println();
			salida.println("\t\t\t\t TOTAL "+ f.totalSinIva()+"€");
			salida.println("\t\t\t\t IVA "+f.ivaReducido()+"€");
			salida.println("\t\t\t\t Descuento "+p.getDescuento()+"€");
			salida.println("\t\t\t\t IMPORTE TOTAL: "+(f.totalSinIva()+f.ivaReducido()-p.getDescuento()));
			
			f.setTotal(f.totalSinIva()+f.ivaReducido()-p.getDescuento());
			salida.flush();
			salida.close();
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return f;
	}
	public static void main(String[] args) throws FileNotFoundException {
		Empleado e=new Empleado("15151515P", "PAco", "", "", 2);
		e.asignarPedidosTurno();
		Factura f=new Factura();
		BD miconexion=BD.getInstance();
		miconexion.cargarParametrosConexion();
		
		for(Pedido p: e.getPedidosAsignados()) {
			//System.out.println(p);
			f=e.generarFactura(p);
			
			try {
				BD.getInstance().consultaBD("delete from Factura");
				BD.getInstance().consultaBD("Insert into Factura values('"+f.getCodigoFactura()+"',"+f.getCodigoPedido()+",'"+f.getDniCliente()+"',"+f.getTotal()+")");
				BD.getInstance().cerrarConsulta();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Insert into Factura values('"+f.getCodigoFactura()+"',"+f.getCodigoPedido()+",'"+f.getDniCliente()+"',"+f.getTotal()+")");
			
		}
	}
}