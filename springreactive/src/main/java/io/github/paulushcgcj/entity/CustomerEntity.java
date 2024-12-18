package io.github.paulushcgcj.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "customers", schema = "smash")
@Data
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class CustomerEntity {

  @Column("customer_id")
  @Id
  private UUID id;
  @Column("first_name")
  private String firstName;
  @Column("last_name")
  private String lastName;
  @Column("email")
  private String email;
  @Column("phone_number")
  private String phoneNumber;
  @Column("address_line1")
  private String addressLine1;
  @Column("address_line2")
  private String addressLine2;
  @Column("city")
  private String city;
  @Column("state")
  private String state;
  @Column("postal_code")
  private String postalCode;
  @Column("country")
  private String country;
  @Column("creation_date")
  private LocalDateTime creationDate;
  @Column("update_date")
  private LocalDateTime updateDate;
  @Column("created_by_user")
  private String createdByUser;
  @Column("updated_by_user")
  private String updatedByUser;
  @Column("revision")
  private Integer revision;
}
