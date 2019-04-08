package frota.api.model;

public class Viagem {

	private double distancia;
	private CarroViagem carroViagem;
	private long preco;
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public CarroViagem getCarroViagem() {
		return carroViagem;
	}
	public void setCarroViagem(CarroViagem carroViagem) {
		this.carroViagem = carroViagem;
	}
	public long getPreco() {
		return preco;
	}
	public void setPreco(long preco) {
		this.preco = preco;
	}


	
}
