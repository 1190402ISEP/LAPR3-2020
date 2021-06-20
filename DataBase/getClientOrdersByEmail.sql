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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientOrdersByEmail.sql
 * Ficheiro:  getClientOrdersByEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientOrdersByEmail.sql
 */

create or replace function getClientOrdersByEmail(p_email client.useremail%type) return sys_refcursor
    is
    v_orders sys_refcursor;
begin

    open v_orders
        for select o.id,o.orderdate,o.orderstate from orders o inner join clientorder co on o.id=co.id where co.useremail = p_email;
    return v_orders;

end;
/
commit;