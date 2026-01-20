package yps.systems.ai.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import yps.systems.ai.model.Zone;

import java.util.List;

@Repository
public interface IZoneRepository extends Neo4jRepository<Zone, String> {

    // Buscar zonas por tipo (Ej: Todas las zonas VIP)
    List<Zone> findAllByType(String type);

    // Consulta para obtener zonas con espacio disponible
    @Query("MATCH (z:Zone) WHERE z.occupiedSpaces < z.capacity RETURN z")
    List<Zone> findAvailableZones();
    
    // Consulta para actualizar ocupación (Incrementar)
    @Query("MATCH (z:Zone) WHERE elementId(z) = $elementId SET z.occupiedSpaces = z.occupiedSpaces + 1 RETURN z")
    Zone incrementOccupancy(String elementId);

    // Consulta para actualizar ocupación (Decrementar)
    @Query("MATCH (z:Zone) WHERE elementId(z) = $elementId AND z.occupiedSpaces > 0 SET z.occupiedSpaces = z.occupiedSpaces - 1 RETURN z")
    Zone decrementOccupancy(String elementId);
}
