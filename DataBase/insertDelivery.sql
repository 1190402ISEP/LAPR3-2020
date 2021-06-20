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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/insertDelivery.sql
 * Ficheiro:  insertDelivery.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: insertDelivery.sql
 */

create or replace function insertDelivery(p_nif courier.nif%type, p_vehicleID int,
                                          p_pharmacyId int, p_distanceMeters float, p_timeMinutes float,
                                          p_energyCostWH float) return int
    is

    v_maxId int;

begin

    if p_nif != 0 then
        insert into DELIVERY (NIF, SCOOTERID, SCOOTERPHARMACYID, DISTANCEMETERS, TIMEMINUTES, ENERGYCOSTWH)
        values (p_nif, p_vehicleID, p_pharmacyId, p_distanceMeters, p_timeMinutes, p_energyCostWH);
    else
        insert into DELIVERY (DRONEID, DRONEPHARMACYID, DISTANCEMETERS, TIMEMINUTES, ENERGYCOSTWH)
        values (p_vehicleID, p_pharmacyId, p_distanceMeters, p_timeMinutes, p_energyCostWH);
    end if;


    select max(IDDELIVERY) into v_maxId from DELIVERY;

    return v_maxId;

end;
/
commit;
