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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAvailableScooter.sql
 * Ficheiro:  getAvailableScooter.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAvailableScooter.sql
 */

create or replace FUNCTION getAvailableScooter(p_CourierNif Courier.nif%TYPE, p_pharmacyId Pharmacy.id%TYPE,
                                               p_payload Vehicle.maxPayload%TYPE) RETURN Vehicle.id%TYPE
    IS
    v_ScooterId integer := 0;

BEGIN

    select v.id
    into v_ScooterId
    from Vehicle v
             inner join Scooter s
                        on v.id = s.ID
             inner join Courier c
                        on s.ID = c.scooterVehicleID
    where s.pharmacyID = p_pharmacyId
      AND v.maxPayload >= p_payload
      AND v.state = 1
      and c.SCOOTERVEHICLEID = v.ID
      and c.NIF = p_CourierNif
    Order by v.BATTERY desc
        FETCH FIRST 1 ROW ONLY;


    return v_ScooterId;


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        select v.id
        into v_ScooterId
        from Vehicle v
                 inner join Scooter s
                            on v.id = s.ID
                 inner join Courier c
                            on s.ID = c.scooterVehicleID
        where s.pharmacyID = p_pharmacyId
          AND v.maxPayload >= p_payload
          AND v.state = 1
        Order by v.BATTERY desc
            FETCH FIRST 1 ROW ONLY;
        RETURN v_ScooterId;

END;
/
COMMIT;