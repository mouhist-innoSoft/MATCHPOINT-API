package com.matchpointecv.matchpointecv.convite;


import lombok.Data;

@Data
public class ConviteDTO {

    private Long id;
    private Long remetente;
    private Long destinatario;
    private Long jogo;
    private String status;
}
