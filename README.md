# Frota-V2-Api

# Metodo de listagem de carros 
```sh
curl -X GET \
https://frota-v2-api.herokuapp.com/frotasV2/listaCarros
```




# chamada para trazer o carro mais próximo ao cliente passando latitude e longitude via URL 
```sh
curl -X GET \
https://frota-v2-api.herokuapp.com/frotasV2/buscarCliente?latitudeCliente=-23.589508&longitudeCliente=-46.634396
```



# chamada para calcular a distancia do cliente e sua determinada viagem, retornar a kilometragem e o preço da viagem
```sh
curl -X GET \
https://frota-v2-api.herokuapp.com/frotasV2/viagem?latitudeCliente=-23.589508&longitudeCliente=-46.634396&latitudeViagem=-23.574165&longitudeViagem=-46.623248
```





# ***** coordenadas para teste ********

dentro do banco existe um carro com o endereço da fiap e o endereço do cliente está próximo ao metrô V.Mariana




# FIAP Av. Lins de Vasconcelos, 1222 - Aclimação, São Paulo - SP, 01538-001 

Coordenadas: -23.574165, -46.623248




# Fundação para O Remédio Popular Furp Av. Prof. Noé Azevedo, 311 - Vila Mariana, São Paulo - SP, 04117-040

Coordenadas: -23.589508, -46.634396
