# Mercadolibre
Challenge mutantes mercadolibre

# Examen Mercadolibre
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En alguno de los siguiente lenguajes: Java / Golang / C-C++ / Javascript (node) / Python / Ruby): 

boolean isMutant(String[] dna); // Ejemplo Java

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representan cada base nitrogenada del ADN.

![image](https://user-images.githubusercontent.com/45123938/116382234-4f987500-a7db-11eb-99d4-26aa9ea62b8d.png)

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales , de forma oblicua, horizontal o vertical.

Ejemplo (Caso mutante):

String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}; En este caso el llamado a la función isMutant(dna) devuelve “true”.

Desarrolla el algoritmo de la manera más eficiente posible.

# SOLUCION

Para ejecutar los servicios Localmente:

1. Se puede ejecutar fácilmente desde local a través de un IDE, como por ejemplo Eclipse, clonando este repositorio y creando el archivo env en la ruta src/main/resources con el siguiente contenido: 
![image](https://user-images.githubusercontent.com/45123938/116384602-a30bc280-a7dd-11eb-8d10-edeed021ccb3.png)

2. Ejecutar en modo normal o debugg y para testearlo se pueden lanzar peticiones desde el proyecto de SoapUI llamado ChallengeMercadolibre-soapui-project.xml que se adjunta en la carpeta soapUITests.

Para ejecutar los servicios en AWS:

1. Se desplegaron los servicios a través del servicio Elastic Beanstalk, desde donde se montó el archivo jar generado.

2. Cuando la aplicación inicia se conecta a la base de datos Dynamo con credenciales que obtiene del SecretManager.

3. Estos servicios se pueden testear con el proyecto de SoapUI llamado ChallengeMutantsAWS-soapui-project.xml que se adjunta en la carpeta soapUITests.
