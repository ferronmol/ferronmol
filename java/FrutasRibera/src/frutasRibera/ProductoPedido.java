package frutasRibera;

public class ProductoPedido {
	private int codigoPedido;
	private int codigoProducto;
	private String nombre;
	private String descripcion;
	private String temporada;
	private double precioKg;
	private double kg;
	
	 @Override
		public String toString() {
			return "ProductoPedido [codigoPedido=" + codigoPedido + ", codigoProducto=" + codigoProducto + ", nombre="
					+ nombre + ", descripcion=" + descripcion + ", temporada=" + temporada + ", precioKg=" + precioKg
					+ ", kg=" + kg + "]";
		}
	
	
	
	public ProductoPedido(int codigoPedido, int codigoProducto, String nombreproducto, String descripcion,
			String temporada, double precioKg, double kg) {
		super();
		this.codigoPedido = codigoPedido;
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.temporada = temporada;
		this.precioKg = precioKg;
		this.kg = kg;
	}
	public int getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public double getPrecioKg() {
		return precioKg;
	}
	public void setPrecioKg(double precioKg) {
		this.precioKg = precioKg;
	}
	public double getKg() {
		return kg;
	}
	public void setKg(double kg) {
		this.kg = kg;
	}
	
	public String imprimirProducto() {
		return ("\t"+this.nombre+"\t"+this.precioKg+"�/kg\t"+this.kg+"kg");
	
	}
	
	

}
