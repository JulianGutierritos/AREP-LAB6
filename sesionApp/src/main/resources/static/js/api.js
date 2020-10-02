var api = (function () {

	return {
		autenticar: function (callback) {
			var usuario = {correo : $("#correo").val() , passwd : $("#passwd").val()};
			var postRequest = $.ajax({
				url: "autenticar",
				type: 'POST',
				data: JSON.stringify(usuario),
			});
			postRequest.then(
				function (resp) {
					window.location.replace("/usuario.html");
				},
				function (resp) {
					alert("Usuario incorrecto");
				}
			);
		},
		getNombre: function (callback) {
			var getRequest = $.ajax({
				url: "getNombre",
				type: 'GET',
			});
			getRequest.then(
				function (resp) {
					var cad = "Hola, " + resp + "!";
					$("#encabezado").text(cad);
				},
				function (resp) {
				}
			);
		},
		logOut: function (callback) {
			var getRequest = $.ajax({
				url: "logOut",
				type: 'GET',
			});
			getRequest.then(
				function (resp) {
					window.location.replace("/index.html");
				},
				function (resp) {
				}
			);
		},
		hora: function (callback) {
			var getRequest = $.ajax({
				url: "getHora",
				type: 'GET',
			});
			getRequest.then(
				function (resp) {
					$("#hora").text(resp);
				},
				function (resp) {
				}
			);
		}
	};
})();
