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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/sendEmailToClientWithInvoice.sql
 * Ficheiro:  sendEmailToClientWithInvoice.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: sendEmailToClientWithInvoice.sql
 */

create or replace function sendEmailToClientWithInvoice(p_orderID int) return sys_refcursor
    is
    v_return sys_refcursor;
begin
    open v_return for
        select o.id,
               co.nif,
               co.USEDCREDITS,
               o.ORDERDATE,
               p.DESIGNATION,
               o.TOTALWEIGHT,
               TOTALPRICE,
               DELIVERYFEE,
               PRODUCTSTOTALPRICE,
               TOTALWITHDISCOUNT,
               co.USEREMAIL
        from ORDERS o
                 inner join CLIENTORDER co on o.ID = co.ID
                 inner join PHARMACY p on o.REQUESTPHARMACY = p.ID
                 inner join INVOICE i on i.ORDERID = o.ID
        where o.id = p_orderID;

    return v_return;

end;
/
commit;