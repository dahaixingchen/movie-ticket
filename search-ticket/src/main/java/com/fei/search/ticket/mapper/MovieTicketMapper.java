package com.fei.search.ticket.mapper;

public interface MovieTicketMapper {

    Long checkVip(Long userId);

    Long getUserId(String userName);
}
