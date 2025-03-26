package techzen.module4_c1224.service.dto.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntrospectResponse {
    private boolean isValid;
}
