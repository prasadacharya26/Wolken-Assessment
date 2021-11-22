package com.wolken.assessment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolken.assessment.dto.TicketDTO;
import com.wolken.assessment.entity.TicketEntity;
import com.wolken.assessment.entity.UserEntity;
import com.wolken.assessment.repository.TicketRepository;
import com.wolken.assessment.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserandTicketService userandTicketService;

	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String validateAndAddTicket(TicketDTO ticketDTO){
		UserEntity userEntity = userRepository.findById(ticketDTO.getCustomerId());
		try {
			if (!ticketDTO.getAgentId().equals(null) && !ticketDTO.getAgentId().equals("")) {
				if (!ticketDTO.getRequestType().equals(null) && !ticketDTO.getRequestType().equals("")) {
					if (!ticketDTO.getIssue().equals(null) && !ticketDTO.getIssue().equals("")) {
						if (!ticketDTO.getDate().equals(null) && !ticketDTO.getDate().equals("")) {
							if (!ticketDTO.getUrgency().equals(null) && !ticketDTO.getUrgency().equals("")) {
								if (ticketDTO.getUrgency().equalsIgnoreCase("urgent")
										|| ticketDTO.getUrgency().equalsIgnoreCase("medium")
										|| ticketDTO.getUrgency().equalsIgnoreCase("low")) {
									if (!ticketDTO.getStatus().equals(null) && !ticketDTO.getStatus().equals("")) {
										if (!ticketDTO.getProductId().equals(null) && !ticketDTO.equals("")) {
											if (!ticketDTO.getProductName().equals(null) && !ticketDTO.equals("")) {
												if (!ticketDTO.getAssignedTo().equals(null)
														&& !ticketDTO.getAssignedTo().equals("")) {
													if (ticketDTO.getStatus().equalsIgnoreCase("pending") || ticketDTO.getStatus().equalsIgnoreCase("completed")) {
														if (ticketDTO.getCustomerId() != 0) {
															
															if (userEntity != null) {
																TicketEntity ticketEntity = new TicketEntity();
																Date addDate = new SimpleDateFormat("dd/MM/yyyy")
																		.parse(ticketDTO.getDate());

																ticketEntity.setDate(addDate);
																BeanUtils.copyProperties(ticketDTO, ticketEntity);
																ticketRepository.save(ticketEntity);
																UserEntity userEntity1 = userRepository
																		.findById(ticketDTO.getCustomerId());
																String mapping = userandTicketService
																		.mappingUserandTicket(userEntity1, ticketEntity);
																logger.info("Ticket saved");
																logger.info(mapping);
																logger.info("" + userEntity1);
																return "Ticket saved";
															} else {
																logger.info("Invalid customer id");
																return "Invalid customer id";
															}
														} else {
															logger.info("Invalid customer id");
															return "Invalid customer id";
														}
													}else {
														logger.info("Invalid status(enter pending/completed)");
														return "Invalid urgency(enter  pending/completed)";
													}
													
												} else {
													logger.info("Invalid assigned to");
													return "Invalid assigned to";
												}
											} else {
												logger.info("Invalid product name");
												return "Invalid product name";
											}
										} else {
											logger.info("Invalid product id");
											return "Invalid product id";
										}

									} else {
										logger.info("Invalid status");
										return "Invalid status";
									}
								} else {
									logger.info("Invalid urgency");
									return "Invalid urgency";
								}
							} else {
								logger.info("Invalid urgency");
								return "Invalid urgency";
							}
						} else {
							logger.info("Invalid date");
							return "Invalid date";
						}
					} else {
						logger.info("Invalid issue");
						return "Invalid issue";
					}
				} else {
					logger.info("Invalid request type");
					return "Invalid request type";
				}
			} else {
				logger.info("Invalid agent id");
				return "Invalid agent id";
			}
		} catch (NullPointerException | TypeMismatchException | ParseException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<TicketDTO> validateAndGetAll() {
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		List<TicketEntity> ticketEntities = ticketRepository.findAll();
		try {
			for (TicketEntity ticketEntity : ticketEntities) {
				logger.info("" + ticketEntities);
				TicketDTO ticketDto = new TicketDTO();
				BeanUtils.copyProperties(ticketEntity, ticketDto);
				ticketDto.setDate(date.format(ticketEntity.getDate()));
				ticketDTOs.add(ticketDto);
				logger.info("" + ticketDTOs);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return ticketDTOs;
	}

	@Override
	public String updateTicketById(TicketDTO ticketDTO) {
		try {
			TicketEntity ticketEntity = ticketRepository.findById(ticketDTO.getTicketId());
			if (ticketEntity != null) {
				if (ticketDTO.getAgentId() != null) {
					ticketEntity.setAgentId(ticketDTO.getAgentId());
				}
				if (ticketDTO.getRequestType() != null) {
					ticketEntity.setRequestType(ticketDTO.getRequestType());
				}
				if (ticketDTO.getIssue() != null) {
					ticketEntity.setIssue(ticketDTO.getIssue());
				}
				if (ticketDTO.getDate() != null) {
					Date addDate = new SimpleDateFormat("dd/MM/yyyy").parse(ticketDTO.getDate());
					ticketEntity.setDate(addDate);
				}
				if (ticketDTO.getUrgency() != null) {
					if (ticketDTO.getUrgency().equalsIgnoreCase("urgent")|| ticketDTO.getUrgency().equalsIgnoreCase("medium")|| ticketDTO.getUrgency().equalsIgnoreCase("low")) {
						ticketEntity.setUrgency(ticketDTO.getUrgency());
					}else {
						logger.info("Invalid urgency(enter urgent/medium/low)");
						return "Invalid urgency(enter urgent/medium/low)";
					}
				}
				if (ticketDTO.getStatus() != null) {
					if (ticketDTO.getStatus().equalsIgnoreCase("pending") || ticketDTO.getStatus().equalsIgnoreCase("completed")) {
						ticketEntity.setStatus(ticketDTO.getStatus());
					}else {
						logger.info("Invalid status(enter pending/completed)");
						return "Invalid urgency(enter  pending/completed)";
					}
				}
				if (ticketDTO.getAssignedTo() != null) {
					ticketEntity.setAssignedTo(ticketDTO.getAssignedTo());
				}
				if (ticketDTO.getCustomerId() != 0) {
					ticketEntity.setCustomerId(ticketDTO.getCustomerId());
				}
				ticketRepository.save(ticketEntity);
				String mapping = userandTicketService.updateMapping(ticketEntity);
				logger.info(mapping);
				logger.info("Data updated");
				return "Data updated";
			} else {
				logger.info("Ticket id not found");
				return "Ticket id not found";
			}
		} catch (NullPointerException | TypeMismatchException | ParseException e) {
			logger.error(e.getMessage(), e.getClass().getName());
			return e.getMessage();
		}
	}

	@Override
	public String updateStatusById(int ticketId, String status) {
		try {
			if (ticketId != 0) {
				if (status != null) {
					if (status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("completed")) {
						TicketEntity ticketEntity = ticketRepository.findById(ticketId);
						if (ticketEntity != null) {
							ticketEntity.setStatus(status);
							ticketRepository.save(ticketEntity);
							String mapping = userandTicketService.updateMapping(ticketEntity);
							logger.info(mapping);
							logger.info("Status Updated");
							return "Status Updated";
						} else {
							logger.info("Ticket id not found");
							return "Ticket id not found";
						}
					} else {
						logger.info("Invalid status");
						return "Invalid status(enter pending/completed)";
					}
				} else {
					logger.info("Invalid status");
					return "Invalid status";
				}
			} else {
				logger.info("Invalid ticket id");
				return "Invalid ticket id";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return null;
	}

	@Override
	public List<TicketDTO> findMyTickets(int customerId) {
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		List<TicketEntity> ticketEntities = ticketRepository.findByCustomerId(customerId);
		try {
			if (customerId != 0) {
				if (ticketEntities != null) {
					for (TicketEntity ticketEntity : ticketEntities) {
						logger.info("" + ticketEntities);
						TicketDTO ticketDTO = new TicketDTO();
						BeanUtils.copyProperties(ticketEntity, ticketDTO);
						ticketDTO.setDate(date.format(ticketEntity.getDate()));
						ticketDTOs.add(ticketDTO);
						logger.info("" + ticketDTOs);
					}
				} else {
					logger.info("Customer id not found");
				}
			} else {
				logger.info("Invalid customer id");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return ticketDTOs;
	}

	@Override
	public TicketDTO checkProgress(int tickedId) {
		TicketEntity ticketEntity = ticketRepository.findById(tickedId);
		TicketDTO ticketDTO =new TicketDTO();
		try {
			if(ticketEntity!=null) {
				BeanUtils.copyProperties(ticketEntity, ticketDTO);
				ticketDTO.setDate(date.format(ticketEntity.getDate()));
				logger.info(""+ticketDTO);
			}
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getName());
		}
		return ticketDTO;
	}

	@Override
	public String validateAndSaveAll(List<TicketDTO> ticketDTOs) throws ParseException {
		List<TicketEntity> ticketEntities =new ArrayList<>();
		try {
			for (TicketDTO ticketDTO : ticketDTOs) {
				UserEntity userEntity = userRepository.findById(ticketDTO.getCustomerId());
				if (!ticketDTO.getAgentId().equals(null) && !ticketDTO.getAgentId().equals("")) {
					if (!ticketDTO.getRequestType().equals(null) && !ticketDTO.getRequestType().equals("")) {
						if (!ticketDTO.getIssue().equals(null) && !ticketDTO.getIssue().equals("")) {
							if (!ticketDTO.getDate().equals(null) && !ticketDTO.getDate().equals("")) {
								if (!ticketDTO.getUrgency().equals(null) && !ticketDTO.getUrgency().equals("")) {
									if (ticketDTO.getUrgency().equalsIgnoreCase("urgent")
											|| ticketDTO.getUrgency().equalsIgnoreCase("medium")
											|| ticketDTO.getUrgency().equalsIgnoreCase("low")) {
										if (!ticketDTO.getStatus().equals(null) && !ticketDTO.getStatus().equals("")) {
											if (!ticketDTO.getProductId().equals(null) && !ticketDTO.equals("")) {
												if (!ticketDTO.getProductName().equals(null) && !ticketDTO.equals("")) {
													if (!ticketDTO.getAssignedTo().equals(null)
															&& !ticketDTO.getAssignedTo().equals("")) {
														if (ticketDTO.getStatus().equalsIgnoreCase("pending") || ticketDTO.getStatus().equalsIgnoreCase("completed")) {
															if (ticketDTO.getCustomerId() != 0) {
																if (userEntity != null) {
																	TicketEntity ticketEntity = new TicketEntity();
																	Date addDate = new SimpleDateFormat("dd/MM/yyyy")
																			.parse(ticketDTO.getDate());

																	ticketEntity.setDate(addDate);
																	BeanUtils.copyProperties(ticketDTO, ticketEntity);
																	ticketEntities.add(ticketEntity);
																	logger.info(""+ticketEntities);
																	logger.info(""+userEntity);
																} else {
																	logger.info("Invalid customer id");
																	return "Invalid customer id";
																}
															} else {
																logger.info("Invalid customer id");
																return "Invalid customer id";
															}
														}else {
															logger.info("Invalid status(enter pending/completed)");
															return "Invalid urgency(enter  pending/completed)";
														}
														
													} else {
														logger.info("Invalid assigned to");
														return "Invalid assigned to";
													}
												} else {
													logger.info("Invalid product name");
													return "Invalid product name";
												}
											} else {
												logger.info("Invalid product id");
												return "Invalid product id";
											}

										} else {
											logger.info("Invalid status");
											return "Invalid status";
										}
									} else {
										logger.info("Invalid urgency");
										return "Invalid urgency";
									}
								} else {
									logger.info("Invalid urgency");
									return "Invalid urgency";
								}
							} else {
								logger.info("Invalid date");
								return "Invalid date";
							}
						} else {
							logger.info("Invalid issue");
							return "Invalid issue";
						}
					} else {
						logger.info("Invalid request type");
						return "Invalid request type";
					}
				} else {
					logger.info("Invalid agent id");
					return "Invalid agent id";
				}
			}
			logger.info(""+ticketEntities);
			ticketRepository.saveAll(ticketEntities);
			String mapping = userandTicketService
					.mappingUserandTicket(ticketEntities);
			logger.info(mapping);
			logger.info("Data Saved");
			return "Data Saved";
			
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
		}
		return null;
	}

}
