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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/initiateMapPoints.sql
 * Ficheiro:  initiateMapPoints.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: initiateMapPoints.sql
 */

create or replace procedure initiateMapPoints(p_cord Mappoint.COORDINATES%type, p_alt mapPoint.altitude%type, p_nome MapPoint.designation%type)
    is
begin

    insert into MAPPOINT (COORDINATES, ALTITUDE, designation) values (p_cord, p_alt, p_nome);
end;
/

commit;