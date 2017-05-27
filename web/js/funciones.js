$(document).ready(function() {
	
	$("#munProv").click(function() {
		$("#divMunProv").show();
		$("#divCCAAMun").hide();
		$("#divAddMun").hide();
		$('#divModMun').hide();
		$('#mensajes').empty();
	});
	
	$("#CCAAMun").click(function() {
		$("#divMunProv").hide();
		$("#divCCAAMun").show();
		$("#divAddMun").hide();
		$('#divModMun').hide();
		$('#mensajes').empty();
	});
	
	$("#addMun").click(function() {
		$("#divMunProv").hide();
		$("#divCCAAMun").hide();
		$("#divAddMun").show();
		$('#divModMun').hide();
		$('#mensajes').empty();
	});
	
	$("#modMun").click(function() {
		$("#divMunProv").hide();
		$("#divCCAAMun").hide();
		$("#divAddMun").hide();
		$('#divModMun').show();
		$('#mensajes').empty();
	});
	
	// Participan en la opción 1 y 3
	peticionAjax("/apmunicipios/controlador?accion=mostrarCCAA", "", rellenarSelectCCAA);
	
	// Participa en la opción 1
	$("#selComunidad").change(function() {
		peticionAjax("/apmunicipios/controlador?accion=mostrarProvCCAA", $("#verMunProv").serialize(), rellenarSelectProv);
	});
	
	$('#verMunProv').submit(function(event){
		event.preventDefault();
		peticionAjax($(this).attr('action'), $(this).serialize(), mostrarTablaMun);
	});
	
	// Participan en la opción 2
	setTimeout(
		function(){
			peticionAjax("/apmunicipios/controlador?accion=mostrarProvincias", "", rellenarSelectProv3);
		}, 2000);
	
	$("#nomMun").keyup(function(evento) {
		if((evento.keyCode>=48 && evento.keyCode<=57) || (evento.keyCode>=65 && evento.keyCode<=90) || (evento.keyCode >= 96 && evento.keyCode <= 105)){
			peticionAjax("/apmunicipios/controlador?accion=autoMunProv", $("#verCCAAMun").serialize(), autoNomMunicipio);
		}
	});
	
	$('#verCCAAMun').submit(function(event){
		event.preventDefault();
		peticionAjax($(this).attr('action'), $(this).serialize(), mostrarCCAA);
	});

	// Participan en la opción 3
	$("#selComunidad2").change(function() {
		peticionAjax("/apmunicipios/controlador?accion=mostrarProvCCAA", $("#formAddMun").serialize(), rellenarSelectProv2);
	});
	
	$('#formAddMun').submit(function(event){
		event.preventDefault();
		peticionAjax($(this).attr('action'), $(this).serialize(), resultadoAddMun);
	});
	
	// Participan en la opción 3
	$("#nomAntMun").keyup(function(evento) {
		if((evento.keyCode>=48 && evento.keyCode<=57) || (evento.keyCode>=65 && evento.keyCode<=90) || (evento.keyCode >= 96 && evento.keyCode <= 105)){
			peticionAjax("/apmunicipios/controlador?accion=autocompletarMun", $("#formModMun").serialize(), autoNomMunicipio2);
		}
	});
	
	$('#ComprobarMun').click(function(event){
		peticionAjax("/apmunicipios/controlador?accion=mostrarDatMun", $("#formModMun").serialize(), tablaDatosMun);
	});
	
	$('#formModMun').submit(function(event){
		event.preventDefault();
		peticionAjax($(this).attr('action'), $(this).serialize(), resultadoModMun);
	});
		
});

//Petición ajax que recibe la url a la cual debe dirigirse, los datos a enviar y la función que debe procesar los
// datos de respuesta
var peticionAjax = function(url, datos, llegadaDatos){
	$.ajax({
        async:true,
        type: "POST",
        dataType: "json", //tipo de dato que se va ha recibir
        contentType: "application/x-www-form-urlencoded;charset=UTF-8", //como se envia los datos
        url:url,
        data:datos, //cadena, los datos de la petición
        beforeSend: inicioEnvio,
        success: llegadaDatos, //función que recupera los datos devueltos
        timeout: 4000, //tiempo máximo a esperar para la respuesta
        error: problemas //Si hay algún problema, se ejecuta
    });
}

function rellenarSelectCCAA(datos){
	console.log(datos);
	$.each(datos, function(index,value){
		$("#selComunidad").append($("<option>").text(value).attr("value",index));
		$("#selComunidad2").append($("<option>").text(value).attr("value",index));
	});
}

function rellenarSelectProv(datos){
	console.log(datos);
	$("#selProvincia").empty();
	$.each(datos, function(index,value){
		$("#selProvincia").append($("<option>").text(value).attr("value",index));
	});
}

function rellenarSelectProv2(datos){
	console.log(datos);
	$("#selProvincia3").empty();
	$.each(datos, function(index,value){
		$("#selProvincia3").append($("<option>").text(value).attr("value",index));
	});
}

function rellenarSelectProv3(datos){
	console.log(datos);
	$("#selProvincia2").empty();
	$.each(datos, function(index,value){
		$("#selProvincia2").append($("<option>").text(value).attr("value",index));
	});
}

function mostrarTablaMun(datos){
	console.log(datos);
	$("#tablaMunicipios").empty();
	$('#tablaMunicipios').append('<thead><tr><th>Código</th><th>Nombre</th><th>DC</th></tr></thead>');
	$.each(datos, function(index,value){
		$('#tablaMunicipios').append('<tbody><tr><td>'+value[0]+'</td><td>'+value[1]+'</td><td>'+value[2]+'</td></tr></tbody>');
	});
}

function autoNomMunicipio(datos){
	console.log(datos);
    var caja=document.getElementById("nomMun");
   	//si se ha recibido una palabra de respuesta se introduce en el control y se seleccionan los caracteres aún no tecleados
   	if(datos!=""){                    
       	var inicioSel=caja.value.length;
       	caja.value=datos;
       	caja.selectionStart=inicioSel;
       	caja.selectionEnd=caja.value.length;
   	}
}

function mostrarCCAA(datos) {
	console.log(datos);
	$("#relsultadoCCAAMun").empty();
	$("#relsultadoCCAAMun").append("<h4 class='bg-info'>"+datos+"</h4>");	
}

function resultadoAddMun(datos) {
	console.log(datos);
	$("#relsultadoAddMun").empty();
	$("#relsultadoAddMun").append("<h4 class='bg-info'>Municipio "+datos+" guardado en la bb.dd.</h4>");
	resetForm($('#formAddMun'));
}

function autoNomMunicipio2(datos){
	console.log(datos);
    var caja=document.getElementById("nomAntMun");
   	//si se ha recibido una palabra de respuesta se introduce en el control y se seleccionan los caracteres aún no tecleados
   	if(datos!=""){                    
       	var inicioSel=caja.value.length;
       	caja.value=datos;
       	caja.selectionStart=inicioSel;
       	caja.selectionEnd=caja.value.length;
   	}
}

function tablaDatosMun(datos){
	console.log(datos);
	$("#tablaMunicipio").empty();
	$('#tablaMunicipio').append('<thead><tr><th>Código</th><th>Nombre</th><th>DC</th></tr></thead><tbody><tr>');
	$.each(datos, function(index,value){
		$('#tablaMunicipio').append('<td>'+value+'</td>');
	});
	$('#tablaMunicipio').append('</tr></tbody>');
}

function resultadoModMun(datos) {
	console.log(datos);
	$("#relsultadoModMun").empty();
	$("#relsultadoModMun").append("<h4 class='bg-info'>Municipio "+datos+" modificado en la bb.dd.</h4>");
	resetForm($('#formModMun'));
}

function resetForm($form) {
    $form.find('input:text, input:password, input:file, select, textarea').val('');
    $form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
}

function inicioEnvio() {
    //no se hace nada
}

//Función que recibe los datos de la petición ajax cuando hay algún error
function problemas() {
	console.log("Hay un problema en el servidor, intentelo más tarde.");
	$("#mensajes").empty();
	$("#mensajes").append("<h4 class='bg-danger'>Hay un problema en la aplicación, intentelo más tarde.</h4>");	
}