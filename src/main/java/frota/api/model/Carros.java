package frota.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Carros {

	@Id
	public String id;
	@Indexed(unique=true)
	private String ano;
	private String placa;
	private String cor;
	private String modelo;
	private String longitude;
	private String latitude;
	private String status;

	public Carros() {
		super();
	}

	public Carros(String ano, String placa, String cor,String modelo, String longitude, String latitude, String status) {
		super();
		this.ano = ano;
		this.placa = placa;
		this.cor = cor;
		this.modelo = modelo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getmodelo() {
		return modelo;
	}

	public void setmodelo(String modelo) {
		this.modelo = modelo;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Carros [id=" + id + ", ano=" + ano + ", placa=" + placa + ", cor=" + cor + ", modelo=" + modelo
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", status=" + status + "]";
	}
}
