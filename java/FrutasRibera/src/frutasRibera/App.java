package frutasRibera;

import java.sql.SQLException;
import java.util.ArrayList;

public class App {
	
	
	
	private ArrayList<Empleado> emp;
	public App() {
		this.emp=new ArrayList<>();
	}

	public ArrayList<Empleado> getEmp() {
		return emp;
	}


	public void setEmp(ArrayList<Empleado> emp) {
		this.emp = emp;
	}

	public static void main(String[] args) {
		App a=new App();
		BD.getInstance().cargarParametrosConexion();
		try {
			BD.getInstance().consultaBD("delete from Factura");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		a.setEmp(BD.getInstance().cargaEmpleados());
		
		if(args.length==0) {
			for(Empleado e: a.getEmp()) {
				Factura f=new Factura();
				//el empleado elegido genera las facturas de sus pedidos
				System.out.print("El empleado "+e.getNombre()+" con DNI "+e.getDNI());
				e.asignarPedidosTurno();
				int contador=0;
				for(Pedido p: e.getPedidosAsignados()) {
					System.out.println(p);
					f=e.generarFactura(p);
					contador++;
					try {
						
						BD.getInstance().consultaBD("Insert into Factura values('"+f.getCodigoFactura()+"',"+f.getCodigoPedido()+",'"+f.getDniCliente()+"',"+f.getTotal()+")");
						BD.getInstance().cerrarConsulta();
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}//for
				System.out.println(" prepara "+contador+" pedidos");
			}
		
		}//if vacio
		
		if(args.length>0) {
			if(args[0].contains("--empleado")) {
				String []cadena=args[0].split("=");
				String dni=cadena[1];
				
				for(Empleado e: a.getEmp()) {
					Factura f=new Factura();
						//el empleado elegido genera las facturas de sus pedidos
					if(dni.equals(e.getDNI())) {
						System.out.print("El empleado "+e.getNombre()+" con DNI "+e.getDNI());
						e.asignarPedidosTurno();
						int contador=0;
						for(Pedido p: e.getPedidosAsignados()) {
						//System.out.println(p);
							f=e.generarFactura(p);
							contador++;
							try {
								BD.getInstance().consultaBD("delete from Factura");
								BD.getInstance().consultaBD("Insert into Factura values('"+f.getCodigoFactura()+"',"+f.getCodigoPedido()+",'"+f.getDniCliente()+"',"+f.getTotal()+")");
								BD.getInstance().cerrarConsulta();
							
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
									e1.printStackTrace();
							}
							
					}//for
						System.out.println(" prepara "+contador+" pedidos");
										
					}//if dni
				}
						
				}//if dni empleado	
			}//if args
		
	}//main
	
}//clase