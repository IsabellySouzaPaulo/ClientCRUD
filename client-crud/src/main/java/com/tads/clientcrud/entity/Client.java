package com.tads.clientcrud.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// ENTIDADE representa a tabela do banco. Cada objeto Client = uma linha da tabela
// Os nomes dos atributos seguem o diagrama do enunciado (id, name, cpf, income, birthDate, children)
@Entity
@Table(name = "tb_client") // nome da tabela no banco H2
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // o banco gera o id automaticamente
	private Long id;
	private String name;
	private String cpf;
	private Double income;

	// O JPA converte camelCase para snake_case: birthDate vira a coluna birth_date.

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;

	private Integer children;

	// Construtor vazio obrigatório para o JPA conseguir criar o objeto.
	public Client() {
	}

	public Client(Long id, String name, String cpf, Double income, Instant birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}

	// Getters e setters pra ler e alterar
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCpf() { return cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }

	public Double getIncome() { return income; }
	public void setIncome(Double income) { this.income = income; }

	public Instant getBirthDate() { return birthDate; }
	public void setBirthDate(Instant birthDate) { this.birthDate = birthDate; }

	public Integer getChildren() { return children; }
	public void setChildren(Integer children) { this.children = children; }

	// hashCode e equals comparam dois clientes pelo id.
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
}
