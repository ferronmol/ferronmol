package frutasRibera;

public class Cliente extends Persona{
	
	Pedido p;



	public Cliente(String dNI, String nombre, String apellidos, String direccion, Pedido p) {
		super(dNI, nombre, apellidos, direccion);
		this.p = p;
	}

	public Cliente(String dNI, String nombre, String apellidos, String direccion) {
		super(dNI, nombre, apellidos, direccion);
		
	}
	
	
	
}
