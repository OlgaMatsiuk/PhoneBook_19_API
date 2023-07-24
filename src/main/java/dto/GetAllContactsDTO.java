package dto;

import java.util.List;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class GetAllContactsDTO {
    List<ContactDTO> contacts;
    


}
