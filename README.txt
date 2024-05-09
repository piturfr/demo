DESCARGA PROYECTO
git clone https://github.com/piturfr/demo.git

COMPILACION
$base_path/demo
Ejecutar: mvn clean install

DESPLIEGUE EN ENTORNO LOCAL
En el IDE de desarrollo:
Importar proyecto Maven -> $base_path/demo
Arrancar microservicio demo-api-infrastructure

TESTING
Desde la API generada con Swagger están accesibles los endpoints de la aplicación:
http://localhost:8080/swagger-ui/index.html
