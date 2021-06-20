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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientCordByEmail.sql
 * Ficheiro:  getClientCordByEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientCordByEmail.sql
 */

create or replace function getClientCordByEmail(p_email client.useremail%type) return mappoint.coordinates%type
    is
    v_cord mapPoint.coordinates%type;
begin

    select COORDINATES
    into v_cord from CLIENT where USEREMAIL = p_email;

    return v_cord;

end;
/
commit;