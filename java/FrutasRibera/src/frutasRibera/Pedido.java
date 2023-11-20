package frutasRibera;



public class Pedido {
	private int codigPedido;
	private String DNICliente;
	private double descuento;
	private int turno;
	
	
	
	public Pedido(int codigPedido, String dNICliente, double descuento, int turno) {
		super();
		this.codigPedido = codigPedido;
		DNICliente = dNICliente;
		this.descuento = descuento;
		this.turno = turno;
	}
	public int getCodigPedido() {
		return codigPedido;
	}
	public void setCodigPedido(int codigPedido) {
		this.codigPedido = codigPedido;
	}
	public String getDNICliente() {
		return DNICliente;
	}
	public void setDNICliente(String dNICliente) {
		DNICliente = dNICliente;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	@Override
	public String toString() {
		return "\n\nPedido: "+ codigPedido + "\n\tDNICliente:" + DNICliente ;
	}
	
	
}
