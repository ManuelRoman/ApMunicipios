<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="WEB-INF/gesError.jsp?pagOrigen=index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menú</title>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery320.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/funciones.js"></script>
<style>
	#divMunProv{
	display: none;
	}
	#divCCAAMun{
	display: none;
	}
	#divAddMun{
	display: none;
	}
	#divModMun{
	display: none;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="page-header">
    		<h1 class="text-center">Ejercicio Municipios</h1>      
  		</div>
  		<!-- Div general -->
  		<div class="row">
  			<div class="col-md-4 col-md-offset-4 text-center" id="general">
  				<h4>Seleccione una acción:</h4>
				<button id="munProv" class="btn btn-default">Ver municipios de una provincia</button><br/><br/>
				<button id="CCAAMun" class="btn btn-default">Ver la comunidad de un municipio</button><br/><br/>
				<button id="addMun" class="btn btn-default">Añadir un municipio</button><br/><br/>
				<button id="modMun" class="btn btn-default">Cambiar el nombre de un municipio</button><br/>
  			</div>
  		</div><hr/>
  		<!--Formulario Ver municipios de una provincia-->
  		<div class="row">
			<div class="col-md-6 col-md-offset-3 text-center" id="divMunProv">
				<h3>Ver municipios de una provincia</h3>
				<form action="/apmunicipios/controlador" method="post" id="verMunProv">
					<input type="hidden" name="accion" value="mostrarMunProv">
					<div class="form-group">
						<label for="selComunidad">Seleccione una Comunidad:</label>
						<select class="form-control" id="selComunidad" name="selComunidad">
							<option>Seleccione una comunidad</option>
						</select>
					</div>
					<div class="form-group">
						<label for="selProvincia">Seleccione una Provincia:</label>
						<select class="form-control" id="selProvincia" name="selProvincia" required></select>
					</div>
					<input type="submit" value="Ver" class="btn btn-default">
				</form>
			<!--Tabla de municipios de una provincia -->
			<table class="table table-hover" id="tablaMunicipios"></table>
			</div>
		</div>
		<!--Formulario Ver la comunidad de un municipio -->
  		<div class="row">
			<div class="col-md-6 col-md-offset-3 text-center" id="divCCAAMun">
				<h3>Ver la comunidad de un municipio</h3>
				<form action="/apmunicipios/controlador" method="post" id="verCCAAMun" accept-charset="utf-8">
					<input type="hidden" name="accion" value="mostrarCCAAMun">
					<div class="form-group">
						<label for="selProvincia2">Seleccione una Provincia:</label>
						<select class="form-control" id="selProvincia2" name="selProvincia"></select>
					</div>
					<div class="form-group">
						<label for="nomMun">Nombre del Municipio:</label>
						<input class="form-control" id="nomMun" type="text" name="nomMun" required="required" maxlength="100"><br>
					</div>
					<input type="submit" value="Ver" class="btn btn-default">
				</form>
			<!--Resultado de la búsqueda comunidad del municipio -->
			<div class="col-md-8 col-md-offset-2" id="relsultadoCCAAMun"></div>
			</div>
		</div>
		<!--Formulario para añadir un municipio -->
  		<div class="row">
			<div class="col-md-6 col-md-offset-3 text-center" id="divAddMun">
				<h3>Añadir un municipio</h3>
				<form action="/apmunicipios/controlador" method="post" id="formAddMun">
					<input type="hidden" name="accion" value="agregarMun">
					<div class="form-group">
						<label for="selComunidad2">Seleccione una Comunidad:</label>
						<select class="form-control" id="selComunidad2" name="selComunidad">
							<option>Seleccione una comunidad</option>
						</select>
					</div>
					<div class="form-group">
						<label for="selProvincia3">Seleccione una Provincia:</label>
						<select class="form-control" id="selProvincia3" name="selProvincia" required></select>
					</div>
					<div class="form-group">
						<label for="nomMun2">Nombre del Municipio:</label>
						<input class="form-control" id="nomMun2" type="text" name="nomMun" required="required"><br>
					</div>
					<input type="submit" value="Añadir" class="btn btn-default">
				</form>
				<!--Resultado de añadir un municipio -->
				<div class="col-md-8 col-md-offset-2" id="relsultadoAddMun"></div>
			</div>
		</div>
		<!--Formulario para cambiar el nombre de un municipio -->
  		<div class="row">
			<div class="col-md-6 col-md-offset-3 text-center" id="divModMun">
				<h3>Cambiar el nombre de un municipio</h3>
				<form action="/apmunicipios/controlador" method="post" id="formModMun">
					<input type="hidden" name="accion" value="cambiarNomMun">
					<div class="form-group">
						<label for="nomAntMun">Nombre anterior del Municipio:</label>
						<input class="form-control" id="nomAntMun" type="text" name="nomAnt" required="required" pattern="\S*"><br>
					</div>
					<div class="form-group">
						<label for="nomNuevoMun">Nuevo nombre del Municipio:</label>
						<input class="form-control" id="nomNuevoMun" type="text" name="nomNuevoMun" required="required" pattern="\S*"><br>
					</div>
					 <button type="button" class="btn btn-default" id="ComprobarMun">Comprobar datos</button> 
					<input type="submit" value="Modificar" class="btn btn-default">
				</form>
			<!--Datos del municipios a cambiar -->
			<table class="table table-hover" id="tablaMunicipio"></table>
			<!--Resultado de modificar un municipio -->
			<div class="col-md-8 col-md-offset-2" id="relsultadoModMun"></div>
			</div>
		</div><hr/>
  		<!-- Mensajes al usuario -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="text-center" id="mensajes"></div>
			</div>
		</div>
  	</div>
</body>
</html>