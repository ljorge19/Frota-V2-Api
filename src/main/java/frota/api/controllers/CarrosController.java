package frota.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frota.api.model.Carros;
import frota.api.model.User;
import frota.api.persistence.CarrosRepository;
import frota.api.model.CarroViagem;
import frota.api.model.Distancia;
import frota.api.model.Viagem;

@RestController
@RequestMapping("/frotasV2")
public class CarrosController {
	@Autowired
	private CarrosRepository tbCarrosRepository;

	// QueryParam
	@GetMapping("/listaCarros")
	public ResponseEntity<List<Carros>> getList() {
		List<Carros> carros = new ArrayList<>();

		tbCarrosRepository.findAll().forEach(carros::add);

		return new ResponseEntity<List<Carros>>(carros, HttpStatus.OK);
	}

	@GetMapping("/buscarCliente")
	public Viagem buscarCliente(@RequestParam double latitudeCliente,@RequestParam double longitudeCliente) {

		boolean verificarCarro = false;
		Viagem viagem = new Viagem();
		ArrayList<Distancia> listaDistancia = new ArrayList<Distancia>();
		Distancia distancia;
		List<Carros> carros = new ArrayList<>();
		carros = tbCarrosRepository.findAll();

		// listando os carros e calculando a quilometragem do carro até o cliente
		for (Carros carro : carros) {

			if (carro.getLatitude() != null && carro.getLongitude() != null ) {
				//verificarCarro = true;
				
				CarroViagem carroViagem = new CarroViagem();
				carroViagem.setAno(carro.getAno());
				carroViagem.setId(carro.getId());
				carroViagem.setPlaca(carro.getPlaca());
				carroViagem.setLatitude(carro.getLatitude());
				carroViagem.setLongitude(carro.getLongitude());
				//carroViagem.setStatus(carro.getStatus());
				
				
				double latitudeCarro = Double.parseDouble(carro.getLatitude());
				double longitudeCarro = Double.parseDouble(carro.getLongitude());

				Double distanciaCalculada = distance(latitudeCliente, longitudeCliente, latitudeCarro, longitudeCarro,
						"K");
				distancia = new Distancia();
				distancia.setCarroViagem(carroViagem);
				distancia.setKm(distanciaCalculada);
				listaDistancia.add(distancia);

			}
		}

		

		//if (verificarCarro = true) {
			// achando o carro mais próximo do cliente
			for (Distancia distanciaCarro : listaDistancia) {

				if (distanciaCarro.getKm() < 1) {
					viagem.setCarroViagem(distanciaCarro.getCarroViagem());
					viagem.setDistancia(distanciaCarro.getKm());
					viagem.setPreco(7);
					break;
				} else if (distanciaCarro.getKm() > 1 && distanciaCarro.getKm() < 5) {
					viagem.setCarroViagem(distanciaCarro.getCarroViagem());
					viagem.setDistancia(distanciaCarro.getKm());
					viagem.setPreco(15);
					break;
				} else if (distanciaCarro.getKm() > 5 && distanciaCarro.getKm() < 10) {
					viagem.setCarroViagem(distanciaCarro.getCarroViagem());
					viagem.setDistancia(distanciaCarro.getKm());
					viagem.setPreco(20);
				} else if (distanciaCarro.getKm() > 10) {
					viagem.setCarroViagem(distanciaCarro.getCarroViagem());
					viagem.setDistancia(distanciaCarro.getKm());
					viagem.setPreco(50);
				}
			}
		//}
		

		return viagem;
	
	}

	@GetMapping("/viagem")
	public Viagem viagem(@RequestParam double latitudeCliente,@RequestParam double longitudeCliente,
			@RequestParam double latitudeViagem,@RequestParam double longitudeViagem) {

		boolean verificarCarro = false;
		Viagem viagem = new Viagem();
		ArrayList<Distancia> listaDistancia = new ArrayList<Distancia>();
		Distancia distancia;
		List<Carros> carros = new ArrayList<>();
		carros = tbCarrosRepository.findAll();

		Double distanciaCalculada = distance(latitudeCliente, longitudeCliente, latitudeViagem, longitudeViagem, "K");
		distancia = new Distancia();
		// distancia.setCarroViagem(carroViagem);
		distancia.setKm(distanciaCalculada);
		viagem.setDistancia(distanciaCalculada);

		if (distanciaCalculada > 1) {
			viagem.setPreco(7);
		} else if (distanciaCalculada > 3) {
			viagem.setPreco(15);
		} else if (distanciaCalculada > 5) {
			viagem.setPreco(20);
		} else if (distanciaCalculada > 10) {
			viagem.setPreco(50);
		} else if (distanciaCalculada > 20) {
			viagem.setPreco(100);
		} else if (distanciaCalculada > 30) {
			viagem.setPreco(200);
		}

		return viagem;
	}

	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// :: This function converts decimal degrees to radians :
	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// :: This function converts radians to decimal degrees :
	// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
