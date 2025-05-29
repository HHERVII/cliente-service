package com.nttyplc.cliente_service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServiceDB {

    private static final List<Map<String, String>> CLIENTES = List.of(
        Map.of(
            "tipoDocumento", "C",
            "numeroDocumento", "23445322",
            "primerNombre", "Juan",
            "segundoNombre", "Carlos",
            "primerApellido", "Pérez",
            "segundoApellido", "Gómez",
            "telefono", "3001234567",
            "direccion", "Calle 123 #45-67",
            "ciudadResidencia", "Bogotá"
        ),
        Map.of(
            "tipoDocumento", "P",
            "numeroDocumento", "A1234567",
            "primerNombre", "María",
            "segundoNombre", "Elena",
            "primerApellido", "Rodríguez",
            "segundoApellido", "López",
            "telefono", "3117654321",
            "direccion", "Carrera 10 #20-30",
            "ciudadResidencia", "Medellín"
        ),
        Map.of(
            "tipoDocumento", "C",
            "numeroDocumento", "98765432",
            "primerNombre", "Carlos",
            "segundoNombre", "Andrés",
            "primerApellido", "Martínez",
            "segundoApellido", "Salazar",
            "telefono", "3001239876",
            "direccion", "Avenida 5 #40-50",
            "ciudadResidencia", "Cali"
        ),
        Map.of(
            "tipoDocumento", "P",
            "numeroDocumento", "B7654321",
            "primerNombre", "Lucía",
            "segundoNombre", "Fernanda",
            "primerApellido", "Gómez",
            "segundoApellido", "Castillo",
            "telefono", "3209876543",
            "direccion", "Calle 15 #25-35",
            "ciudadResidencia", "Barranquilla"
        ),
        Map.of(
            "tipoDocumento", "C",
            "numeroDocumento", "12345678",
            "primerNombre", "Andrés",
            "segundoNombre", "Felipe",
            "primerApellido", "Torres",
            "segundoApellido", "Hernández",
            "telefono", "3101122334",
            "direccion", "Diagonal 22 #45-67",
            "ciudadResidencia", "Bucaramanga"
        )
    );

    public Optional<Map<String, String>> buscarCliente(String tipoDocumento, String numeroDocumento) {
        return CLIENTES.stream()
                .filter(c -> c.get("tipoDocumento").equalsIgnoreCase(tipoDocumento) &&
                             c.get("numeroDocumento").equalsIgnoreCase(numeroDocumento))
                .findFirst();
    }
}
