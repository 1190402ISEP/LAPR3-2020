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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createPlace.sql
 * Ficheiro:  createPlace.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createPlace.sql
 */

create or replace procedure createPlace(p_parkID int, p_isAvailable int, p_isChargingStation int)
    is
    v_placeID   int;
    v_charingID int;
begin
    select count(*) + 1 into v_placeID from PARKPLACE;

    insert into PARKPLACE(id, parkid, ISAVAILABLE) values (v_placeID, p_parkID, p_isAvailable);

    if p_isChargingStation = 1 then
        select count(*) + 1 into v_charingID from CHARGINGSTATION;
        insert into CHARGINGSTATION(id) values (v_charingID);
        update PARKPLACE set CHARGINGSTATIONID = v_charingID where ID = v_placeID;

    end if;


end;
/
commit;