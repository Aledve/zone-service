package yps.systems.ai.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Zone") // Etiqueta para BD
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Zone {

    @Id
    @GeneratedValue
    private String elementId;

    // Nombre de la zona (Ej: "Zona Norte", "Sótano 1")
    @Property("name")
    private String name;

    // Tipo de zona (Ej: "VIP", "ESTUDIANTES", "DOCENTES", "MOTOS")
    @Property("type")
    private String type;

    // Capacidad total de vehículos
    @Property("capacity")
    private Integer capacity;

    // Espacios actualmente ocupados (para cálculo rápido)
    @Property("occupiedSpaces")
    private Integer occupiedSpaces;

    // Ubicación descriptiva (Ej: "Junto al edificio de Ingeniería")
    @Property("locationDescription")
    private String locationDescription;

    // Estado (Habilitada/En mantenimiento)
    @Property("state")
    private Boolean state;

}
