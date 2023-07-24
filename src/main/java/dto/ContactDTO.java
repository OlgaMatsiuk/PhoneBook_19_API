package dto;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor

public class ContactDTO {
    String name;
    String id;
    String lastName;
    String phone;
    String email;
    String address;
    String description;



}
