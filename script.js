/*Logica para manejar el formulario de login*/
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    const requestBody = {
        email: email,
        password: password
    };

    /*Aca consumo la API de manera local*/
    fetch('http://localhost:8080/usuario/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    /*Se prepara la respuesta*/
    .then(response => {
        return response.json().then(data => ({
            status: response.status,
            body: data
        }));
    })
    /*Capturo el elemento y lo devuelvo*/
    .then(response => {
        const responseDiv = document.getElementById('responseLogin');
        if (response.status === 200) {
            responseDiv.innerHTML = '<p>Inicio de sesión exitoso</p>';
        } else {
            responseDiv.innerHTML = `<p>Error: Usuario/contraseña incorrectos</p>`;
        }
    })
    /*Si surge una excepcion saltan a esta parte*/
    .catch(error => {
        console.error('Error:', error);
        const responseDiv = document.getElementById('responseLogin');
        responseDiv.innerHTML = '<p>Error: ' + error.message + '</p>';
    });
});

/*Logica para el formulario de registro*/
document.getElementById('registroForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const requestBody = {
        nombre: nombre,
        apellido: apellido,
        email: email,
        password: password
    };
    /*Consumo la API de manera local*/
    fetch('http://localhost:8080/usuario/registro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        return response.json().then(data => ({
            status: response.status,
            body: data
        }));
    })
    .then(response => {
        const responseDiv = document.getElementById('responseRegistro');
        if (response.status === 201) {
            responseDiv.innerHTML = '<p>Usuario registrado exitosamente</p>';
        } else {
            let errorMessages = '<ul>';
            for (const [message] of Object.entries(response.body)) {
                errorMessages += `<p> ${message}</p>`;
            }
            errorMessages += '</ul>';
            responseDiv.innerHTML = `<p>Error:</p> ${errorMessages}`;
        }
    })
    .catch(error => {
        console.error('Error:', error);
        const responseDiv = document.getElementById('responseRegistro');
        responseDiv.innerHTML = '<p>Error: ' + error.message + '</p>';
    });
});

document.getElementById('showRegistro').addEventListener('click', function() {
    document.getElementById('loginContainer').classList.add('hidden');
    document.getElementById('registroContainer').classList.remove('hidden');
});

document.getElementById('showLogin').addEventListener('click', function() {
    document.getElementById('registroContainer').classList.add('hidden');
    document.getElementById('loginContainer').classList.remove('hidden');
});

document.getElementById('forgotPassword').addEventListener('click', function(event) {
    event.preventDefault();

    const email = document.getElementById('loginEmail').value;

    if (!email) {
        const responseDiv = document.getElementById('responseLogin');
        responseDiv.innerHTML = '<p>Por favor, ingresa tu correo electrónico.</p>';
        return;
    }

    fetch('http://localhost:8080/usuario/restablecer-contrasena?email=' + encodeURIComponent(email), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la solicitud: ' + response.status);
        }
        return response.text(); // Recibir como texto en lugar de JSON
    })
    .then(data => {
        const responseDiv = document.getElementById('responseLogin');
        responseDiv.innerHTML = '<p>' + data + '</p>'; // Mostrar el mensaje directamente
    })
    .catch(error => {
        console.error('Error:', error);
        const responseDiv = document.getElementById('responseLogin');
        responseDiv.innerHTML = '<p>Error: ' + error.message + '</p>';
    });
});