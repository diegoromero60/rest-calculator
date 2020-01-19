package co.com.restcalculator.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Status {
	@JsonProperty("Message")
	private String message;
	@JsonProperty("Code")
	private Integer code;
}
