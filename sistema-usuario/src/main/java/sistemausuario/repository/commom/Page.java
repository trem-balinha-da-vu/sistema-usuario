package sistemausuario.repository.commom;

import java.io.Serializable;
import lombok.Data;

@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -3313325525077947576L;

    public Page(T data, Long totalCount) {
        this.totalCount = totalCount;
        this.data = data;
    }

    private Long totalCount;

    private T data;
}

