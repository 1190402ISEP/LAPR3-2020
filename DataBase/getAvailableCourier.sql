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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAvailableCourier.sql
 * Ficheiro:  getAvailableCourier.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAvailableCourier.sql
 */

create or replace FUNCTION getAvailableCourier(p_pharmacyId Courier.scooterPharmacyID%TYPE) RETURN Vehicle.id%TYPE
    IS
    v_CourierNif integer;

BEGIN
    
    select c.nif into v_CourierNif
    from Courier c
    where c.scooterPharmacyID = p_pharmacyId AND c.state = 1
    FETCH FIRST 1 ROW ONLY;

    return v_CourierNif;


    EXCEPTION WHEN NO_DATA_FOUND THEN
        RETURN 0;

END;
/