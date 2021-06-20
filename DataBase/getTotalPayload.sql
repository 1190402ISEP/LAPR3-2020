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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getTotalPayload.sql
 * Ficheiro:  getTotalPayload.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getTotalPayload.sql
 */

create or replace function getTotalPayload(p_OrderId Orders.id%TYPE) return Orders.totalweight%TYPE
  is
    v_number Orders.totalweight%TYPE;
BEGIN
     select totalWeight into v_number
     from Orders
     where id = p_OrderId;

     return v_number;
END;
/
commit;