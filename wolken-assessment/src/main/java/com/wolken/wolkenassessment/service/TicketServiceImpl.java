package com.wolken.wolkenassessment.service;

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

import com.wolken.wolkenassessment.dto.TicketDTO;
import com.wolken.wolkenassessment.entity.TicketEntity;
import com.wolken.wolkenassessment.repository.TicketRepository;
import com.wolken.wolkenassessment.repository.UserRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TicketService ticketService;
	
	SimpleDateFormat date=new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public String validateAndAddTicket(TicketDTO ticketDTO) {
		//UserEntity userEntity = userRepository.findById(ticketDTO.getCustomerId());
		try {
			if(!ticketDTO.getAgentId().equals(null) && !ticketDTO.getAgentId().equals("")) {
				if(!ticketDTO.getRequestType().equals(null) && !ticketDTO.getRequestType().equals("")) {
					if(!ticketDTO.getIssue().equals(null) && !ticketDTO.getIssue().equals("")) {
						if(!ticketDTO.getDate().equals(null) && !ticketDTO.getDate().equals("")) {
							if(!ticketDTO.getUrgency().equals(null) && !ticketDTO.getUrgency().equals("")) {
								if(ticketDTO.getUrgency().equalsIgnoreCase("urgent") || ticketDTO.getUrgency().equalsIgnoreCase("medium") || ticketDTO.getUrgency().equalsIgnoreCase("low")) {
									if(!ticketDTO.getStatus().equals(null) && !ticketDTO.getStatus().equals("")) {
										if(!ticketDTO.getProductId().equals(null) && !ticketDTO.equals("")) {
											if(!ticketDTO.getProductName().equals(null) && !ticketDTO.equals("")) {
												if(!ticketDTO.getAssignedTo().equals(null) && !ticketDTO.getAssignedTo().equals("")) {
//													if(ticketDTO.getCustomerId()!=0) {
//														if(userEntity!=null) {
															TicketEntity ticketEntity = new TicketEntity();
															Date addDate = new SimpleDateFormat("dd/MM/yyyy").parse(ticketDTO.getDate());
															ticketEntity.setDate(addDate);
															BeanUtils.copyProperties(ticketDTO, ticketEntity);
															ticketRepository.save(ticketEntity);
															logger.info("Ticket saved");
															return "Ticket saved";
//														}else {
//															logger.info("Invalid customer id");
//															return "Invalid customer id";
//														}													
//													}else {
//														logger.info("Invalid customer id");
//														return "Invalid customer id";
//													}
												}else {
													logger.info("Invalid assigned to");
													return "Invalid assigned to";
												}
											}else {
												logger.info("Invalid product name");
												return "Invalid product name";
											}
										}else {
											logger.info("Invalid product id");
											return "Invalid product id";
										}
										
									}else {
										logger.info("Invalid status");
										return "Invalid status";
									}
								}else {
									logger.info("Invalid urgency");
									return "Invalid urgency";
								}
							}else {
								logger.info("Invalid urgency");
								return "Invalid urgency";
							}
						}else {
							logger.info("Invalid date");
							return "Invalid date";
						}
					}else {
						logger.info("Invalid issue");
						return "Invalid issue";
					}
				}else {
					logger.info("Invalid request type");
					return "Invalid request type";
				}
			}else {
				logger.info("Invalid agent id");
				return "Invalid agent id";
			}
		} catch (NullPointerException | TypeMismatchException | ParseException e) {
			logger.error(e.getMessage(),e.getClass().getSimpleName());
			return e.getMessage();
		}
	}

	@Override
	public List<TicketDTO> validateAndGetAll() {
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		try {
			List<TicketEntity> ticketEntities = ticketRepository.findAll();
			
			for (TicketEntity ticketEntity : ticketEntities) {
				TicketDTO ticketDTO = new TicketDTO();
				BeanUtils.copyProperties(ticketEntity, ticketDTO);
				ticketDTO.setDate(date.format(ticketEntity.getDate()));
				ticketDTOs.add(ticketDTO);
			}
			logger.info(""+ticketDTOs);
		} catch (NullPointerException | TypeMismatchException e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return ticketDTOs;
	}


	@Override
	public String updateTicketById(TicketDTO ticketDTO){
		try {
			TicketEntity ticketEntity = ticketRepository.findById(ticketDTO.getTicketId());
			if(ticketEntity!=null) {
				if(ticketDTO.getAgentId()!=null) {
					ticketEntity.setAgentId(ticketDTO.getAgentId());
				}
				if(ticketDTO.getRequestType()!=null) {
					ticketEntity.setRequestType(ticketDTO.getRequestType());
				}
				if(ticketDTO.getIssue()!=null) {
					ticketEntity.setIssue(ticketDTO.getIssue());
				}
				if(ticketDTO.getDate()!=null) {
					Date addDate = new SimpleDateFormat("dd/MM/yyyy").parse(ticketDTO.getDate());
					ticketEntity.setDate(addDate);
				}
				if(ticketDTO.getUrgency()!=null) {
					ticketEntity.setUrgency(ticketDTO.getUrgency());
				}
				if(ticketDTO.getStatus()!=null) {
					ticketEntity.setStatus(ticketDTO.getStatus());
				}
				if(ticketDTO.getAssignedTo()!=null) {
					ticketEntity.setAssignedTo(ticketDTO.getAssignedTo());
				}
//				if(ticketDTO.getCustomerId()!=0) {
//					ticketEntity.setCustomerId(ticketDTO.getCustomerId());
//				}
				ticketRepository.save(ticketEntity);
				logger.info("Data updated");
				return "Data updated";
			}else {
				logger.info("Ticket id not found");
				return "Ticket id not found";
			}
		} catch (NullPointerException | TypeMismatchException | ParseException e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return null;
	}

	@Override
	public String updateStatusById(int ticketId, String status) {
		try {
			if(ticketId!=0) {
				if(status!=null) {
					TicketEntity ticketEntity = ticketRepository.findById(ticketId);
					if(ticketEntity!=null) {
						if(status=="pending" || status=="completed") {
							ticketEntity.setStatus(status);
							ticketRepository.save(ticketEntity);
							logger.info("Status Updated");
							return "Status Updated";
						}else {
							logger.info("Invalid status");
							return "Invalid status";
						}
					}else {
						logger.info("Ticket id not found");
						return "Ticket id not found";
					}
				}else {
					logger.info("Invalid status");
					return "Invalid status";
				}
			}else {
				logger.info("Invalid ticket id");
				return "Invalid ticket id";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return null;
	}

	
	@Override
	public List<TicketDTO> findMyTickets(int customerId) {
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		try {
			if(customerId!=0) {
//				List<TicketEntity> ticketEntities = ticketRepository.findByCustomerId(customerId);
//				if(ticketEntities!=null) {
//						TicketDTO ticketDTO=new TicketDTO();
//						for (TicketEntity ticketEntity : ticketEntities) {
//							BeanUtils.copyProperties(ticketEntity, ticketDTO);
//							ticketDTO.setDate(date.format(ticketEntity.getDate()));
//							ticketDTOs.add(ticketDTO);
//						}
//				}else {
//					logger.info("Customer id not found");
//				}
			}else {
				logger.info("Invalid customer id");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e.getClass().getName());
		}
		return ticketDTOs;
	}

	@Override
	public String saveAll(List<TicketDTO> ticketDTOs) {
		List<TicketEntity> ticketEntities1 =new ArrayList<>();
		try {
			for (TicketDTO ticketDTO : ticketDTOs) {
//				UserEntity userEntity = userRepository.findById(ticketDTO.getCustomerId());
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
//															if (ticketDTO.getCustomerId() != 0) {
//																
//																if (userEntity != null) {
																	TicketEntity ticketEntity = new TicketEntity();
																	Date addDate = new SimpleDateFormat("dd/MM/yyyy")
																			.parse(ticketDTO.getDate());

																	ticketEntity.setDate(addDate);
																	BeanUtils.copyProperties(ticketDTO, ticketEntity);
																	ticketEntities1.add(ticketEntity);
																	logger.info(""+ticketEntities1);
																	
//																} else {
//																	logger.info("Invalid customer id");
//																	return "Invalid customer id";
//																}
//															} else {
//																logger.info("Invalid customer id");
//																return "Invalid customer id";
//															}
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
			logger.info(""+ticketEntities1);
			ticketRepository.saveAll(ticketEntities1);
			logger.info("Data Saved");
			return "Data Saved";	
		} catch (NullPointerException | TypeMismatchException | ParseException e) {
			logger.error(e.getMessage(), e.getClass().getSimpleName());
			return e.getMessage();
		}
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
}
