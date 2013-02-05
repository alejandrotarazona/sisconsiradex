/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
<script type="text/javascript" src="/Siradex/javascript/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function doAjaxPost() {
		//	Conseguir los valores del form
		var catalogo = $('#catalogo_sel').val();

		$.ajax({
			type: "POST",
			url: "/Siradex/Administrador/conseguir_catalogo.do",
			cache: false,
			data: {"catalogo_sel" : catalogo}
		}).done(function(response) {
			alert("hola");
			$("#lista_catalogo").html(response);
		});
	}
</script>
