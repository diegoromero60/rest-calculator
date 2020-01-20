# rest-calculator
conjunto de servicios para calcular: suma, resta, multiplicación, división y potenciación, de un conjunto de números.

1. llamar al servicio [address]:9001/create-session, para crear la sesion, se retornara un codigo de sesion debeera ser almacenado
2. para agregar los numeros se deberá llamar al servicio [address]:9001/add-numbers, se podrá agregar el numero de operandos que se desee.
3. para agregar los numeros se deberá llamar al servicio [address]:9001/process-numbers, si la operacion es posible retornará el resultado. sin embargo si el conjunto de operaciones genera un desbordamiento, se retornará un error con la descripcion.

se incluye archivo swagger.json con la documentacion basica de los servicios expuestos.

se incluye en el repositorio la configuracion de un JOB de jenkins para automatizar la construccion de los artefactos. archivo: rest-calculator-jenkins-job.xml

La aplicacion centraliza las sesiones en una BD Mongo que puede ser configurada en el Jenkinsfile  con el parametro HOST_DB_CALCULATOR, multiples contnedores pueden procesar las solicitudes a los mismos se les deberá proveer la configuracion de la BD a traves del parametro HOST, ejemplo:
	docker run --env HOST=192.168.0.5:27017 diegoromero60/rest-calculator:18

para facilidad en las pruebas se incluyen un export del proyecto postman archivo: rest-calculator.postman_collection.json
