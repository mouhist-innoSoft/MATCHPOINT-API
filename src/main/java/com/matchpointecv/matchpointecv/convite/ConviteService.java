package com.matchpointecv.matchpointecv.convite;


public interface ConviteService {

    Convite getById(Long id);

    ConviteDTO save(ConviteDTO conviteDTO);
}
