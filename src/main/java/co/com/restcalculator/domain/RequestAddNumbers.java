package co.com.restcalculator.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RequestAddNumbers {
	@NotBlank(message = "Session code cannot be missing or empty")
	@JsonProperty("SessionCode")
	private String sessionCode;
	@NotEmpty(message = "List numbers cannot be missing or empty")
	@JsonProperty("NumbersList")
	private List<Float> numbersList;
}
