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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getCordsOfPharmacysWithAvailableChargingStation.sql
 * Ficheiro:  getCordsOfPharmacysWithAvailableChargingStation.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getCordsOfPharmacysWithAvailableChargingStation.sql
 */

create or replace function getCordsOfPharmacysWithAvailableChargingStation(p_tipo int) return sys_refcursor
    is
    v_return sys_refcursor;
begin
    open v_return for
        select p.id, p.ADDRESS
        from PHARMACY p
                 inner join PARK pa on pa.PHARMACYID = p.ID
                 inner join PARKPLACE pp on pp.PARKID = pa.ID
                 inner join
             CHARGINGSTATION cs on pp.CHARGINGSTATIONID = cs.ID
        where pa.PARKTYPEID = p_tipo
          and pp.ISAVAILABLE = 1
        group by p.id, p.ADDRESS;

    return v_return;

end;
/
commit;