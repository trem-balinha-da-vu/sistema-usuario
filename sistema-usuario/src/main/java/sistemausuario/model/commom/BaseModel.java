package sistemausuario.model.commom;

import java.io.Serializable;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel<T extends BaseModel<T>> implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    public Long id;

    @Column(nullable = false, name = "uuid")
    public String uuid;

    @PrePersist
    public void initializeUUID() {
        if (Strings.isBlank(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
    }
	
}
