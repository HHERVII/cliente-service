package com.nttyplc.cliente_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    // Crear instancia del servicio que tiene los datos mockeados
    private final ServiceDB serviceDB = new ServiceDB();

    @GetMapping
    public ResponseEntity<?> consultarCliente(
            @RequestParam(required = false) String tipoDocumento,
            @RequestParam(required = false) String numeroDocumento) {
        try {
            if (tipoDocumento == null || numeroDocumento == null ||
                tipoDocumento.isEmpty() || numeroDocumento.isEmpty()) {
                logger.warn("Faltan parámetros obligatorios: tipoDocumento o numeroDocumento");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, 400,
                        "Tipo de documento y número de documento son obligatorios.", null));
            }

            if (!tipoDocumento.equalsIgnoreCase("C") && !tipoDocumento.equalsIgnoreCase("P")) {
                logger.warn("Tipo de documento inválido: {}", tipoDocumento);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, 400,
                        "Tipo de documento inválido. Solo se aceptan 'C' o 'P'.", null));
            }

            var clienteOpt = serviceDB.buscarCliente(tipoDocumento, numeroDocumento);
            if (clienteOpt.isPresent()) {
                var cliente = clienteOpt.get();
                Map<String, String> respuesta = Map.of(
                    "primerNombre", cliente.get("primerNombre"),
                    "segundoNombre", cliente.get("segundoNombre"),
                    "primerApellido", cliente.get("primerApellido"),
                    "segundoApellido", cliente.get("segundoApellido"),
                    "telefono", cliente.get("telefono"),
                    "direccion", cliente.get("direccion"),
                    "ciudadResidencia", cliente.get("ciudadResidencia")
                );
                logger.info("Cliente encontrado: {} {}", respuesta.get("primerNombre"), respuesta.get("primerApellido"));
                return ResponseEntity.ok(new ApiResponse<>(true, 200,
                "Consulta exitosa", respuesta));
            }

            logger.info("Cliente no encontrado: {} {}", tipoDocumento, numeroDocumento);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>(false, 404,
                    "Cliente no encontrado", null));


        } catch (Exception e) {
            logger.error("Error inesperado en consulta cliente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(false, 500,
                    "Error interno del servidor"+ e, null));
        }
    }
}
