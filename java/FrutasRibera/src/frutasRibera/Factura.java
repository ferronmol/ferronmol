package frutasRibera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Factura implements impuestos{
	
	private String codigoFactura;
	private int codigoPedido;
	private String dniCliente;
	private double total;
	private ArrayList<ProductoPedido> productos;
    
    
	public Factura() {
		this.codigoFactura = "";
		this.codigoPedido = 0;
		this.dniCliente = "";
		this.total = 0;
		this.productos=new ArrayList<>();
	}
	
	public Factura(String codigoFactura, int codigoPedido, String dniCliente, double total,
			ArrayList<ProductoPedido> productos) {
		this.codigoFactura = codigoFactura;
		this.codigoPedido = codigoPedido;
		this.dniCliente = dniCliente;
		this.total = total;
		this.productos = productos;
	}

	public Factura(String codigoFactura, int codigoPedido, String dniCliente, double total) {
		
		this.codigoFactura = codigoFactura;
		this.codigoPedido = codigoPedido;
		this.dniCliente = dniCliente;
		this.total = total;
		this.productos=new ArrayList<>();
	}
//Crea el método cargarProductos() que lee los productos correspondientes a un
//	pedido del fichero ProductosPedidos.csv y los devuelva en el array list.

//  LEER UN CSV Y METERLO EN UN ARRAYLIST es como esto..
	//codigoPedido;codigoProducto ;  nombre   ;        descripcion          ; temporada    ;  precioKg   ;  KG
	//   1000     ;         1    ;  Aguacate  ;Se trata de un fruto .....   ; primavera    ;   5.98      ;   3
	
public ArrayList<ProductoPedido> cargarProductos() {
		
		//Leer del fichero csv
		File f=new File("ProductosPedidos.csv");
		String cadena="";
		String linea[]=null;
				
		try {
			Scanner entrada=new Scanner(f);
			cadena=entrada.nextLine();
			while(entrada.hasNext()) {
				cadena=entrada.nextLine();
				linea=cadena.split(";");
				if(this.codigoPedido==Integer.parseInt(linea[0])) // aqui le decimos que te carge de ese pedido cocnreto de la factura SUS productos
					this.productos.add(new ProductoPedido(Integer.parseInt(linea[0]), Integer.parseInt(linea[1]), linea[2], linea[3], linea[4], Double.parseDouble(linea[5]), Double.parseDouble(linea[6])) );
			}
								
			entrada.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return productos;
	}	
	
public ArrayList<ProductoPedido> getProductos() {
	return productos;
}
public void setProductos(ArrayList<ProductoPedido> productos) {
	this.productos = productos;
}
public String getCodigoFactura() {
	return codigoFactura;
}
public void setCodigoFactura(String codigoFactura) {
	this.codigoFactura = codigoFactura;
}
public int getCodigoPedido() {
	return codigoPedido;
}
public void setCodigoPedido(int codigoPedido) {
	this.codigoPedido = codigoPedido;
}
public String getDniCliente() {
	return dniCliente;
}
public void setDniCliente(String dniCliente) {
	this.dniCliente = dniCliente;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
@Override
public String toString() {
	return "Factura [codigoFactura=" + codigoFactura + ", codigoPedido=" + codigoPedido + ", dniCliente="
			+ dniCliente + ", total=" + total + "]"+productos;
}




	

public double ivaReducido() {
	 return this.total*0.04;
}

public double totalSinIva() {
	double total=0;
	for(ProductoPedido p: this.productos) {
		System.out.println(p.getNombre());
		System.out.println(p.getPrecioKg());
		System.out.println(p.getKg());
		System.out.println("\n\n");
		total+=p.getPrecioKg()*p.getKg();
	}
	this.total=total;	
	return total;
	}
	
	
	

public static void main(String[] args) { //metodo para ver que funciona
	
	Factura f=new Factura("1", 1000, "",0);
	f.cargarProductos();
	System.out.println(f.totalSinIva());		
	System.out.println(f.ivaReducido());
}
}