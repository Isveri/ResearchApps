package pl.piomin.services.grpc.customer.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;
}
