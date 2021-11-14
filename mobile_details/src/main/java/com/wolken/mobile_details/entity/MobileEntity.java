package com.wolken.mobile_details.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
@NamedQueries({
	@NamedQuery(name="getByBrandName" ,query = "from MobileEntity where brandName=:name"),
	@NamedQuery(name="getByPrice" ,query = "from MobileEntity where price<=:price"),
	@NamedQuery(name="updatePriceByModelNo" ,query = "update MobileEntity set price=:price where modelNo=:modelNo "),
	@NamedQuery(name="updateAvailabilityByModelName" ,query = "update MobileEntity set availabele=:availabele where modelName=:modelName ")
})
public class MobileEntity {
	@Id
	@GenericGenerator(name="id",strategy = "increment")
	@GeneratedValue(generator = "id")
	@Column
	private int id;
	@Column
	private String brandName;
	@Column
	private String modelNo;
	@Column
	private String modelName;
	@Column
	private String type;
	@Column
	private byte ram;
	@Column
	private short rom;
	@Column
	private float price;
	@Column
	private Boolean availabele;
	
}
