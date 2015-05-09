package sk.stuba.fei.feidroid.appconfig.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "APP_CONFIG")
@NamedQueries({ @NamedQuery(name = "AppConfig.findAll", query = "SELECT a FROM AppConfig a"),
    @NamedQuery(name = "AppConfig.findById", query = "SELECT a FROM AppConfig a WHERE a.id = :idParam"),
    @NamedQuery(name = "AppConfig.findByIds", query = "SELECT a FROM AppConfig a WHERE a.id IN :idListParam"),
    @NamedQuery(name = "AppConfig.findByKey", query = "SELECT a FROM AppConfig a WHERE a.keyName = :keyParam") })
public class AppConfig {
	public static final String CONFIG_VALUE_TRUE = "TRUE";
	public static final String CONFIG_VALUE_FALSE = "FALSE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "KEY_NAME")
	private String keyName;

	@Column(name = "CONFIG_VALUE")
	private String value;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
