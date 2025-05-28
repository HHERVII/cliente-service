package com.nttyplc.cliente_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @GetMapping
    public ResponseEntity<?> consultarCliente(@RequestParam String tipoDocumento, @RequestParam String numeroDocumento) {
        // Validar parámetros obligatorios
        if (tipoDocumento == null || numeroDocumento == null || tipoDocumento.isEmpty() || numeroDocumento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo y número de documento son obligatorios");
        }

        // Validar tipo de documento
        if (!tipoDocumento.equalsIgnoreCase("C") && !tipoDocumento.equalsIgnoreCase("P")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de documento inválido. Solo C o P");
        }

        // Validar cliente mockeado
        if (tipoDocumento.equalsIgnoreCase("C") && numeroDocumento.equals("23445322")) {
            // Datos quemados
            Map<String, String> cliente = Map.of(
                    "primerNombre", "Juan",
                    "segundoNombre", "Carlos",
                    "primerApellido", "Pérez",
                    "segundoApellido", "Gómez",
                    "telefono", "3001234567",
                    "direccion", "Calle 123 #45-67",
                    "ciudadResidencia", "Bogotá"
            );
            return ResponseEntity.ok(cliente);
        }

        // Si no encuentra cliente
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    // Manejo global errores 500 se puede agregar más adelante si quieres
}

