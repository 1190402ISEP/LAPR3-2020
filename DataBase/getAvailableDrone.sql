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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getAvailableDrone.sql
 * Ficheiro:  getAvailableDrone.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getAvailableDrone.sql
 */

create or replace function getAvailableDrone(p_pharmacyID pharmacy.id%type, p_payload float) return int
    is
    v_droneID int;

begin
    select d.id
    into v_droneID
    from drone d
             inner join VEHICLE v on d.id = v.ID and d.PHARMACYID = v.PHARMACYID
    where (v.STATE = 1 or v.STATE = 4)
      and v.MAXPAYLOAD >= p_payload
      and v.PHARMACYID = p_pharmacyID
    order by v.BATTERY desc
        fetch first row only;

    return v_droneID;
EXCEPTION
    when no_data_found then
        return null;

end;
/
commit;