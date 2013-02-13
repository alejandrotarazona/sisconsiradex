//ESTAS SON ALGUNAS FUNCIONES PARA VALIDAR que TOMÉ DE DISTINTOS SITIOS
 

// ESTAS SON DE formulario.js DEL SIGPIO

function IsNumeric(valor) 
{ 
    var log=valor.length; var sw="S"; 
    for (x=0; x<log; x++) 
    { v1=valor.substr(x,1); 
        v2 = parseInt(v1); 
        //Compruebo si es un valor numérico 
        if (isNaN(v2)) { sw= "N";} 
    } 
    if (sw=="S") {return true;} else {return false; } 
} 
var primerslap=false; 
var segundoslap=false; 

function formateafecha(fecha) 
{ 
    var largo = fecha.length; 
    var dia; 
    var mes; 
    var ano; 
    if ((largo>=2) && (primerslap==false)) { dia=fecha.substr(0,2); 
        if ((IsNumeric(dia)==true) && (dia<=31) && (dia!="00")) { fecha=fecha.substr(0,2)+"/"+fecha.substr(3,7); primerslap=true; } 
        else { fecha=""; primerslap=false;} 
    } 
    else 
    { dia=fecha.substr(0,1); 
        if (IsNumeric(dia)==false) 
        {fecha="";} 
        if ((largo<=2) && (primerslap=true)) {fecha=fecha.substr(0,1); primerslap=false; } 
    } 
    if ((largo>=5) && (segundoslap==false)) 
    { mes=fecha.substr(3,2); 
        if ((IsNumeric(mes)==true) &&(mes<=12) && (mes!="00")) { fecha=fecha.substr(0,5)+"/"+fecha.substr(6,4); segundoslap=true; } 
        else { fecha=fecha.substr(0,3); segundoslap=false;} 
    } 
    else { if ((largo<=5) && (segundoslap=true)) { fecha=fecha.substr(0,4); segundoslap=false; } } 
    if (largo>=7) 
    { ano=fecha.substr(6,4); 
        if (IsNumeric(ano)==false) { fecha=fecha.substr(0,6); } 
        else { if (largo==10){ if ((ano==0) || (ano<1900) || (ano>2100)) { fecha=fecha.substr(0,6); } } } 
    } 
    if (largo>=10) 
    { 
        fecha=fecha.substr(0,10); 
        dia=fecha.substr(0,2); 
        mes=fecha.substr(3,2); 
        ano=fecha.substr(6,4); 
        // Año no viciesto y es febrero y el dia es mayor a 28 
        if ( (ano%4 != 0) && (mes ==02) && (dia > 28) ) { fecha=fecha.substr(0,2)+"/"; } 
    } 
    return (fecha); 
}

//Valida que un campo solo se puedan escribir numeros, ningun otro caracter.
function validar_texto(e){
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla==8){
        return true;
    }
        
    // Patron de entrada, en este caso solo acepta numeros
    patron =/[0-9]/;
    
    tecla_final = String.fromCharCode(tecla);
    
    return patron.test(tecla_final);
}

// ESTAS SON ALGUNAS DE LAS FUNCIONES DE formularios.js DEL SIGPIO 
function validaChar(q)
{
  var x= q
  var y=false
  for ( i = 0; i < x.length; i++ ) {  
    if ( x.charAt(i) != " ") {  
      if(x.charAt(i) =="0" ||x.charAt(i) == "1" ||x.charAt(i) == "2" || x.charAt(i) == "3" || x.charAt(i) == "4"
        || x.charAt(i) == "5" || x.charAt(i) == "6" ||x.charAt(i) == "7" || x.charAt(i) == "8" || x.charAt(i) == "9"){
        return false
      }else{
        y = true
      }
    }     
                
  }
  if(y== true){
    return true
  }else{
    return false
  }
}

function validaNum(q)
{
  var x= q;
  var y=false;
  for ( i = 0; i < x.length; i++ ) {  
    if ( x.charAt(i) != " ") {  
      if(x.charAt(i) =="0" ||x.charAt(i) == "1" ||x.charAt(i) == "2" || x.charAt(i) == "3" || x.charAt(i) == "4"
        || x.charAt(i) == "5" || x.charAt(i) == "6" ||x.charAt(i) == "7" || x.charAt(i) == "8" || x.charAt(i) == "9"){
        y = true;
      }else{
        return false;
      }
    }     
                
  }
  if(y== true){
    return true;
  }else{
    return false;
  }
}

function validaProm(q)
{
  var x= q;
  var y=false;
  var p= false;
  for ( i = 0; i < x.length; i++ ) {  
    if ( x.charAt(i) != " ") {  
      if(x.charAt(i) =="0" ||x.charAt(i) == "1" ||x.charAt(i) == "2" || x.charAt(i) == "3" || x.charAt(i) == "4"
        || x.charAt(i) == "5" || x.charAt(i) == "6" ||x.charAt(i) == "7" || x.charAt(i) == "8" || x.charAt(i) == "9"){
        y = true;
      }else if(x.charAt(i) == "." && p== false){
        p =true;
      }else if(x.charAt(i) == "." && p== true){
        return false;
      }else{
        return false;
      }
    }     
                
  }
  if(y== true){
    if(q.valueOf()<=20.00 && q.valueOf()>=0.01){
      return true;
    }else{
      return false;
    }
  }else{
    return false;
  }
}

function validaTlf(valor){
  if(valor=="0" || valor == ""){
    return true
  }else{
    if(!validaNum(valor)){
      return false
    }
    if (valor.length < 7 || valor.length >11){
      return false
    }
  }
    
  return true
}

function validarEmail(valor) {
  if(valor == ""){
    return true
  }else{
    var x=/^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/
    if(!x.test(valor))    {
      return false;
    }else{
      return true;
    }
  }
}

function validarOpc(valor){
  if (valor <=0){
    return false;
  }else{
    return true;
  }
  return true;
}

function validarEmpty(valor){
  if (valor == ""){
    return true
  }else{
    return false
  }
    
}

function validarClave(valor){
  var espacios = false;
  var cont = 0;
 
  while (!espacios && (cont < valor.length)) {
    if (valor.charAt(cont) == " ")
      espacios = true;
    cont++;
  }
  if(espacios || valor.length==0){
    return false
  }else{
    return true
  }
}


//ESTAS SON DE LA PAGINA WEB http://www.bloogie.es/tecnologia/programacion/460-validacion-de-formularios-y-email-con-javascript

//validar campos del formulario vacíos 
function isBlankAndEmpty(str){ 
    if (!str || str.length === 0) {
        return true; 
    } return !/\S/.test(str);
}
//validar direcciones de email 
function isValidEmail(strEmail){
    validRegExp = /^[^@]+@[^@]+.[^@]$/i; 
    if (strEmail.search(validRegExp) == -1) {
        return false; 
    } return true;
} 
//función para validar el formulario, se llamará en el evento onsubmit del formulario 
function check(form){
    //cambia nombre, email y mensaje por el valor del atributo name de los campos de tu formulario que quieres validar 
    if (isBlankAndEmpty(form.nombre.value)){
        alert("Por favor, dinos tu nombre."); 
    } if (isBlankAndEmpty(form.mensaje.value)){ 
        alert("Se te olvidó escribir el mensaje que quieres enviarnos");
    } if (!isValidEmail(form.email.value) || isBlankAndEmpty(form.email.value)){
        alert('La dirección de email parece errónea.\nPor favor, corrígela y vuelve a intentarlo.');
    } return false; 
}
 