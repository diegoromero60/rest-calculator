package co.com.restcalculator.domain.session;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Document(collection = "sessions-calculator")
public class Session {

	@Id
	@JsonProperty("Id")
	private String id;

	@JsonProperty("CreationDate")
	private Date creationDate;

	@JsonIgnore
	private List<Float> numbersList;
}
