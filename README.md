# POR Métodos de Ingeniería de Software
Aplicación web con un estilo de arquitectura de microservicios desarrollada como proyecto para la asignatura Métodos de Ingeniería de Software de la USACH.

El foco de este proyecto está en implementar las distintas funcionalidades, cumplir con las reglas de negocio, utilizar una arquitectura de microservicios y contenerizar las aplicaciones.

Las funcionalidades están dadas por un enunciado que nos solicita crear un sistema para gestionar los préstamos de equipos del Departamento de Informática, las reglas de negocio a considerar son las siguientes:
 
- Si un profesor devuelve un data proyector en estado “Con Daños” por tres veces
queda inhabilitado para pedir data proyectores.
- Un data proyector no puede quedar en calidad de préstamo por más de 6 horas.
- Si un profesor devuelve un data proyector después de la 6 hrs., entonces no
podrá pedir data proyectores hasta la próxima semana.
- Los data proyectores EPSON solamente pueden ser prestados para Dictado de
Clases y Exámenes de Título.
- Los data proyectores ViewSonic solamente pueden ser prestados para Reuniones
Varias.
- El encargado de gestionar los préstamos de los data proyectores necesita que el
sistema le muestre un reporte por pantalla donde pueda ver los préstamos hechos a los
data proyectores. El sistema debe permitir seleccionar un data proyector y luego mostrar
por pantalla “todos” los préstamos del data proyector seleccionado. El listado debe estar
ordenado por fecha de préstamo y luego por hora de préstamo. Este reporte debe
contener la siguiente información:
1. Fecha préstamo
2. Hora préstamo
3. Profesor al que se le hizo el préstamo
4. Fecha Devolución
5. Hora de Devolución
6. Número de Hrs en poder del profesor
7. Estado en que fue devuelto
8. Uso que se le dio al proyector

## Tecnologias relevantes

- Docker y docker-compose para correr dentro de contenedores las distintas aplicaciones.
- Spring Boot para el desarrollo de los tres microservicios, un config server y un gateway.
- MySQL como base de datos (se crea una por microservicio).
- Next.js para el desarrollo del Frontend.
- Apache Kafka y ZooKeeper para la comunicación de los microservicios.
- Spring Cloud OpenFeign para la comunicación de los microservicios.


## Imágenes UI

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/equipos-1.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/equipos-2.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/ingresar-devolucion-1.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/ingresar-equipos-1.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/ingresar-equipos-2.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/ingresar-prestamo-1.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/ingresar-profesor-1.png)

![alt text](https://github.com/hgallardoaraya/por-mingeso/blob/master/imagenes-ui/reporte-1.png)






