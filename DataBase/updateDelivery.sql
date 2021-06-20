/*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateDelivery.sql
 * Ficheiro:  updateDelivery.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateDelivery.sql
 */

create or replace procedure updateDelivery(p_gastoEnergeticoRestante float, p_tempoRestante float,
                                           p_distaciaRestante float, p_idDelivery INT)
    is
    v_tempo float;
    v_dist  float;
    v_cost  float;

begin

    select TIMEMINUTES
    into v_tempo
    from DELIVERY
    where IDDELIVERY = p_idDelivery;

    select DISTANCEMETERS into v_dist from DELIVERY where IDDELIVERY = p_idDelivery;
    select ENERGYCOSTWH into v_cost from DELIVERY where IDDELIVERY = p_idDelivery;

    update DELIVERY
    set TIMEMINUTES    = v_tempo + p_tempoRestante,
        ENERGYCOSTWH   = v_cost + p_gastoEnergeticoRestante,
        DISTANCEMETERS = v_dist + p_distaciaRestante
    where IDDELIVERY = p_idDelivery;
end;
/
commit;