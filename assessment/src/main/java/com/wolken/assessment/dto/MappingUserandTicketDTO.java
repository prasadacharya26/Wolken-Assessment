package com.wolken.assessment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MappingUserandTicketDTO {
	private int mapId;
	private UserDTO userDTO;
	private TicketDTO ticketDTO;
}
