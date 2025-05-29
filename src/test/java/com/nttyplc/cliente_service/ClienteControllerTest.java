package com.nttyplc.cliente_service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    private final ClienteController controller = new ClienteController();

    @Test
    void cuandoFaltanParametros_retornaBadRequest() {
        ResponseEntity<?> response = controller.consultarCliente(null, "123");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        response = controller.consultarCliente("C", null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        response = controller.consultarCliente("", "123");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void cuandoTipoDocumentoInvalido_retornarBadRequest() {
        ResponseEntity<?> response = controller.consultarCliente("X", "12345678");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void cuandoClienteNoExiste_retornarNotFound() {
        ResponseEntity<?> response = controller.consultarCliente("C", "99999999");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void cuandoClienteExiste_retornarOkConDatos() {
        ResponseEntity<?> response = controller.consultarCliente("C", "23445322");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Object body = response.getBody();
        assertNotNull(body);
        assertTrue(body instanceof java.util.Map);

        var cliente = (java.util.Map<?, ?>) body;
        assertEquals("Juan", cliente.get("primerNombre"));
        assertEquals("Bogotá", cliente.get("ciudadResidencia"));
    }
}
