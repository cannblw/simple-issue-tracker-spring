package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();
}
