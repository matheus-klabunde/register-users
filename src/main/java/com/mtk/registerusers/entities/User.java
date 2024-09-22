package com.mtk.registerusers.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "my_user")
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer age;
	@Column(unique = true)
	private String email;
}
