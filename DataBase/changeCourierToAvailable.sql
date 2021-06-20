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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/changeCourierToAvailable.sql
 * Ficheiro:  changeCourierToAvailable.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: changeCourierToAvailable.sql
 */

create or replace procedure changeCourierToAvailable(p_nif Courier.nif%type)
    is

begin
    update COURIER set STATE = 1 where NIF = p_nif;

end;
/
commit;