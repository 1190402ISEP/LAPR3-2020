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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getProductsList.sql
 * Ficheiro:  getProductsList.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getProductsList.sql
 */

create or replace function getProductsList return SYS_REFCURSOR
    is
    c_products SYS_REFCURSOR;
BEGIN
    open c_products
        for select BARCODE, name, PRICE, IVA
            from PRODUCT
            group by BARCODE, name, PRICE, iva;
    return c_products;

END;
/
commit;