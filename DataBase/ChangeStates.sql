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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/ChangeStates.sql
 * Ficheiro:  ChangeStates.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: ChangeStates.sql
 */

create or replace procedure changeStates(p_vehicleID Scooter.ID%TYPE, p_CourierNIF Courier.NIF%TYPE, p_phaID integer)
    IS

BEGIN

    if p_CourierNIF != 0 then
        UPDATE Courier SET state=2 WHERE nif = p_CourierNIF;

    end if;

    UPDATE Vehicle SET state=2 WHERE id = p_vehicleID and PHARMACYID = p_phaID;

END;
/

commit;